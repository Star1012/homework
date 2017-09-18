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
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import oracle.demo.oow.bd.to.CrewTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class CrewDao
{
	
	//修改crew表中的crew和movie列族
	public void insert(CrewTO crewTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("crew");
		
		if(table != null)
		{
			List<Put> puts = new ArrayList<Put>();
			
			//职务的信息
			Put put1 = new Put(Bytes.toBytes(crewTO.getId()));
			put1.addColumn(Bytes.toBytes("crew"), Bytes.toBytes("name"), Bytes.toBytes(crewTO.getName()));
			put1.addColumn(Bytes.toBytes("crew"), Bytes.toBytes("job"), Bytes.toBytes(crewTO.getJob()));
			
			puts.add(put1);
			
			//crew --> movie的映射
			for(String str : crewTO.getMovieList())
			{
				Put put2 = new Put(Bytes.toBytes(crewTO.getId() + "_" + str));
				put2.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("movie_id"), Bytes.toBytes(str));
				
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

	public CrewTO getCrewById(int crewId)
	{
		CrewTO crewTO = new CrewTO();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_CREW);
		Get get = new Get(Bytes.toBytes(crewId));
		
		try
		{
			Result result = table.get(get);
			
			crewTO.setId(crewId);
			crewTO.setJob(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_CREW_CREW), Bytes.toBytes(ConstantsHBase.QUALIFIER_CREW_JOB))));
			crewTO.setName(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_CREW_CREW), Bytes.toBytes(ConstantsHBase.QUALIFIER_CREW_NAME))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return crewTO;
	}
	
	public List<MovieTO> getMoviesByCrew(int crewId)
	{
		List<MovieTO> movieTOs = new ArrayList<>();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_CREW);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_CREW_MOVIE));
		Filter filter = new PrefixFilter(Bytes.toBytes(crewId + "_"));
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
				
				movieTO = movieDao.getMovieById(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_CREW_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_CREW_MOVIE_ID)))));
				
				movieTOs.add(movieTO);
			}
		}
		
		return movieTOs;
	}
	
}
