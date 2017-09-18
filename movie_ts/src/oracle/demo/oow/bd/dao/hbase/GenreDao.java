package oracle.demo.oow.bd.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import oracle.demo.oow.bd.to.GenreMovieTO;
import oracle.demo.oow.bd.to.GenreTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class GenreDao
{
	
	//修改genre表中的genre和movie列族
	public void insert(MovieTO movieTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("genre");
		
		if(table != null)
		{
			List<Put> puts = new ArrayList<Put>();
			
			for(GenreTO genreTO : movieTO.getGenres())
			{
				//分类的信息
				Put put1 = new Put(Bytes.toBytes(genreTO.getId()));
				put1.addColumn(Bytes.toBytes("genre"), Bytes.toBytes("name"), Bytes.toBytes(genreTO.getName()));
				
				puts.add(put1);
				
				//genre --> movie的映射
				Put put2 = new Put(Bytes.toBytes(genreTO.getId() + "_" +movieTO.getId()));
				put2.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("movie_id"), Bytes.toBytes(movieTO.getId()));
				
				puts.add(put2);
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
	}
	
	public List<GenreMovieTO> getMovies4Customer(int custId, int movieMaxCount, int genreMaxCount)
	{
		List<GenreMovieTO> genreTOs = new ArrayList<GenreMovieTO>();
		Scan scan = new Scan();
		
		Filter filter = new PageFilter(genreMaxCount);
		scan.setFilter(filter);
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_GENRE_GENRE));
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_GENRE);
		
		//全表扫描
		try
		{
			ResultScanner resultScanner = table.getScanner(scan);
			Iterator<Result> iter = resultScanner.iterator();
			GenreTO genreTO = null;
			while(iter.hasNext())
			{
				genreTO = new GenreTO();
				Result result = iter.next();
				genreTO.setId(Bytes.toInt(result.getRow()));
				genreTO.setName(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_GENRE_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_GENRE_NAME))));
				GenreMovieTO genreMovieTO = new GenreMovieTO();
				genreMovieTO.setGenreTO(genreTO);
				genreTOs.add(genreMovieTO);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return genreTOs;
	}
	
}
