package oracle.demo.oow.bd.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import oracle.demo.oow.bd.to.CastTO;
import oracle.demo.oow.bd.to.CastMovieTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class CastDao
{
	//修改cast表中的cast和movie列族
	public void insert(CastTO castTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("cast");
		
		if(table != null)
		{
			List<Put> puts = new ArrayList<Put>();
			
			//角色的名字
			Put put1 = new Put(Bytes.toBytes(castTO.getId()));
			put1.addColumn(Bytes.toBytes("cast"), Bytes.toBytes("name"), Bytes.toBytes(castTO.getName()));
			
			puts.add(put1);
			
			//cast --> movie的映射
			for(CastMovieTO castMovieTO : castTO.getCastMovieList())
			{
				Put put2 = new Put(Bytes.toBytes(castTO.getId() + "_" + castMovieTO.getId()));
				put2.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("movie_id"), Bytes.toBytes(castMovieTO.getId()));
				put2.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("character"), Bytes.toBytes(castMovieTO.getCharacter()));
				put2.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("order"), Bytes.toBytes(castMovieTO.getOrder()));
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

	public CastTO getCastById(int castId)
	{
		CastTO castTO = new CastTO();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_CAST);
		Get get = new Get(Bytes.toBytes(castId));
		
		try
		{
			Result result = table.get(get);
			
			castTO.setId(castId);
			castTO.setName(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_CAST_CAST), Bytes.toBytes(ConstantsHBase.QUALIFIER_CAST_NAME))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return castTO;
	}
	
	public List<MovieTO> getMoviesByCast(int castId)
	{
		List<MovieTO> movieTOs = new ArrayList<>();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_CAST);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_CAST_MOVIE));
		Filter filter = new PrefixFilter(Bytes.toBytes(castId + "_"));
		scan.setFilter(filter);
		
		ResultScanner resultScanner = null;
		try
		{
			resultScanner = table.getScanner(scan);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		MovieTO movieTO = null;
		MovieDao movieDao = new MovieDao();
		if(resultScanner != null)
		{
			Iterator<Result> iter = resultScanner.iterator();
			while(iter.hasNext())
			{
				Result result = iter.next();
				
				movieTO = movieDao.getMovieById(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_CAST_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_CAST_MOVIE_ID))));
				
				movieTOs.add(movieTO);
			}
		}
		
		return movieTOs;
	}
	
}
