package oracle.demo.oow.bd.dao.hbase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import oracle.demo.oow.bd.to.GenreTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.DBUtil;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class CustomerRatingDao
{
	
	public void insertCustomerRating(int userId, int movieId, int score)
	{
		MovieDao movieDao = new MovieDao();
		List<GenreTO> genres = movieDao.getGneresById(movieId);
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_USER);
		
		List<Put> puts = new ArrayList<>();
		for(GenreTO genreTO : genres)
		{
			Put put = new Put(Bytes.toBytes(userId + "_" + genreTO.getId()));
			put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_USER_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_USER_GENRE_ID), Bytes.toBytes(genreTO.getId()));
			put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_USER_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_USER_GENRE_NAME), Bytes.toBytes(genreTO.getName()));
			put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_USER_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_USER_SCORE), Bytes.toBytes(score));
			
			puts.add(put);
		}
		
		try
		{
			table.put(puts);
			table.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<MovieTO> getMoviesByMood(int custId) throws IOException{
		List<MovieTO> movieTOList = new ArrayList<>();
		String get = null;
		PreparedStatement stmt =null;
		Connection conn = DBUtil.getConn();
		
		get = "SELECT movieId FROM cust_rating WHERE USERID = ? ORDER BY RATING DESC";
		List<String> movieIdList = new ArrayList<>();
		try {
			if(conn!=null){
				stmt = conn.prepareStatement(get);
				stmt.setInt(1, custId);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					movieIdList.add(rs.getString(1));
				}
				System.out.println(movieIdList);
				if(movieIdList.size()!=0){
					Iterator<String> iter = movieIdList.iterator();
					MovieDao movieDao = new MovieDao();
					while(iter.hasNext()){
						MovieTO movieTO = movieDao.getMovieById(Integer.parseInt(iter.next()));
						if(movieTO!=null){
							movieTOList.add(movieTO);
						}
					}
					if(movieTOList.size()==0){
						movieTOList = null;
					}
				}
				else
				{
					movieTOList = null;
				}
				DBUtil.close(rs, stmt, conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return movieTOList;
	}
	
}
