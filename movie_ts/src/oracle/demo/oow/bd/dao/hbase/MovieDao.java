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

import oracle.demo.oow.bd.to.CastMovieTO;
import oracle.demo.oow.bd.to.CastTO;
import oracle.demo.oow.bd.to.CrewTO;
import oracle.demo.oow.bd.to.GenreTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class MovieDao
{

	public void insertInfo(MovieTO movieTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("movie");
		List<Put> puts = new ArrayList<Put>();
		
		//电影的信息
		Put put1 = new Put(Bytes.toBytes(movieTO.getId()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("original_title"), Bytes.toBytes(movieTO.getTitle()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("overview"), Bytes.toBytes(movieTO.getOverview()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("poster_path"), Bytes.toBytes(movieTO.getPosterPath()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("release_date"), Bytes.toBytes(movieTO.getReleasedYear()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("vote_count"), Bytes.toBytes(movieTO.getVoteCount()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("runtime"), Bytes.toBytes(movieTO.getRunTime()));
		put1.addColumn(Bytes.toBytes("movie"), Bytes.toBytes("popularity"), Bytes.toBytes(movieTO.getPopularity()));
		
		puts.add(put1);
		
		//movie --> genre的映射
		for(GenreTO genreTO : movieTO.getGenres())
		{
			Put put2 = new Put(Bytes.toBytes(movieTO.getId() + "_" + genreTO.getId()));
			put2.addColumn(Bytes.toBytes("genre"), Bytes.toBytes("genre_id"), Bytes.toBytes(genreTO.getId()));
			put2.addColumn(Bytes.toBytes("genre"), Bytes.toBytes("genre_name"), Bytes.toBytes(genreTO.getName()));
			
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

	public void insertCast(CastTO castTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("movie");
		List<Put> puts = new ArrayList<Put>();
		
		//movie --> cast的映射
		for(CastMovieTO castMovieTO : castTO.getCastMovieList())
		{
			Put put = new Put(Bytes.toBytes(castMovieTO.getId() + "_" + castTO.getId()));
			put.addColumn(Bytes.toBytes("cast"), Bytes.toBytes("cast_id"), Bytes.toBytes(castTO.getId()));
			
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

	public void insertCrew(CrewTO crewTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("movie");
		List<Put> puts = new ArrayList<Put>();
		
		//movie --> crew的映射
		for(String str : crewTO.getMovieList())
		{
			Put put = new Put(Bytes.toBytes(str + "_" + crewTO.getId()));
			put.addColumn(Bytes.toBytes("crew"), Bytes.toBytes("crew_id"), Bytes.toBytes(crewTO.getId()));
			
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

	public MovieTO getMovieById(int movieId)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_MOVIE);
		Get get = new Get(Bytes.toBytes(movieId));
		MovieTO movieTO = new MovieTO();
		
		try
		{
			Result result = table.get(get);
			
			movieTO.setId(movieId);
			movieTO.setTitle(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_ORIGINAL_TITLE))));
			movieTO.setOverview(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_OVERVIEW))));
			movieTO.setPosterPath(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_POSTER_PATH))));
			movieTO.setDate(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_RELEASE_DATE))) + "");
			movieTO.setVoteCount(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_VOTE_COUNT))));
			movieTO.setRunTime(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_RUNTIME))));
			movieTO.setPopularity(Bytes.toDouble(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_POPULARITY))));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return movieTO;
	}

	public List<GenreTO> getGneresById(int movieId)
	{
		List<GenreTO> genreTOs = new ArrayList<>();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_MOVIE);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_GENRE));
		Filter filter = new PrefixFilter(Bytes.toBytes(movieId + "_"));
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
		
		GenreTO genreTO = new GenreTO();
		if(resultScanner != null)
		{
			Iterator<Result> iter = resultScanner.iterator();
			while(iter.hasNext())
			{
				Result result = iter.next();
				
				genreTO.setId(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_GENRE_ID))));
				genreTO.setName(Bytes.toString(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_GENRE), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_GENRE_NAME))));
				
				genreTOs.add(genreTO);
			}
		}
		
		return genreTOs;
	}
	
	public List<CrewTO> getCrewsById(int movieId)
	{
		List<CrewTO> crewTOs = new ArrayList<>();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_MOVIE);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_CREW));
		Filter filter = new PrefixFilter(Bytes.toBytes(movieId + "_"));
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
		
		CrewTO crewTO = new CrewTO();
		CrewDao crewDao = new CrewDao();
		if(resultScanner != null)
		{
			Iterator<Result> iter = resultScanner.iterator();
			if(iter.hasNext())
			{
				Result result = iter.next();
				
				crewTO = crewDao.getCrewById(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_CREW), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_CREW_ID))));
				
				crewTOs.add(crewTO);
			}
		}
		
		return crewTOs;
	}
	
	public List<CastTO> getCastsById(int movieId)
	{
		List<CastTO> castTOs = new ArrayList<>();
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_MOVIE);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_CAST));
		Filter filter = new PrefixFilter(Bytes.toBytes(movieId + "_"));
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
		
		CastTO castTO = new CastTO();
		CastDao castDao = new CastDao();
		if(resultScanner != null)
		{
			Iterator<Result> iter = resultScanner.iterator();
			if(iter.hasNext())
			{
				Result result = iter.next();
				
				castTO = castDao.getCastById(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_MOVIE_CAST), Bytes.toBytes(ConstantsHBase.QUALIFIER_MOVIE_CAST_ID))));
				
				castTOs.add(castTO);
			}
		}
		
		return castTOs;
	}
	
}
