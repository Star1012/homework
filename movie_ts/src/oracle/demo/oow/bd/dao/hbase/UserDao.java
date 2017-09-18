package oracle.demo.oow.bd.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

import oracle.demo.oow.bd.to.CustomerTO;
import oracle.demo.oow.bd.to.MovieTO;
import oracle.demo.oow.bd.util.StringUtil;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

public class UserDao
{
	private int MOVIE_MAX_COUNT = 25;
	
	//修改user中的info和id列族
	public void insert(CustomerTO customerTO)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("user");
		
		if(table!=null)
		{
			Put put1 = new Put(Bytes.toBytes(customerTO.getUserName()));
			//username --> id的映射
			put1.addColumn(Bytes.toBytes("id"), Bytes.toBytes("id"), Bytes.toBytes(customerTO.getId()));
			//用户的基本信息
			Put put2 = new Put(Bytes.toBytes(customerTO.getId()));
			put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("email"), Bytes.toBytes(customerTO.getEmail()));
			put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(customerTO.getName()));
			put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("username"), Bytes.toBytes(customerTO.getUserName()));
			put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("password"), Bytes.toBytes(customerTO.getPassword()));
			
			List<Put> puts = new ArrayList<>();
			puts.add(put1);
			puts.add(put2);
			
			try
			{
				table.put(puts);
				table.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public CustomerTO getCustomerByCredential(String username, String password)
	{
		CustomerTO customerTO = null;
		
		//首先通过username查询id
		int id= getIdByUserName(username);
		
		//根据id查询基本信息
		if(id > 0)
		{
			customerTO = getInfoById(id);
			if(customerTO != null)
			{
				if(!customerTO.getPassword().equals(password))
				{
					//验证通过
					customerTO = null;
				}
			}
		}
		
		return customerTO;
	}

	private CustomerTO getInfoById(int id)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("user");
		Get get = new Get(Bytes.toBytes(id));
		CustomerTO customerTO = new CustomerTO();
		try
		{
			Result result = table.get(get);
			
			customerTO.setEmail(Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("email"))));
			customerTO.setId(id);
			customerTO.setName(Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name"))));
			customerTO.setPassword(Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("password"))));
			customerTO.setUserName(Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("username"))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return customerTO;
	}

	//private
	public int getIdByUserName(String username)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable("user");
		Get get = new Get(Bytes.toBytes(username));
		int id = 0;
		try
		{
			Result result = table.get(get);
			id = Bytes.toInt(result.getValue(Bytes.toBytes("id"), Bytes.toBytes("id")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
	public List<MovieTO> getMovies4CustomerByGenre(int custId, int genreId)
	{
		HBaseDB hBaseDB = HBaseDB.getInstance();
		Table table = hBaseDB.getTable(ConstantsHBase.TABLE_GENRE);
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(ConstantsHBase.FAMILY_GENRE_MOVIE));
		Filter filter1 = new PrefixFilter(Bytes.toBytes(genreId + "_"));
		Filter filter2 = new PageFilter(MOVIE_MAX_COUNT);
		FilterList filterList = new FilterList(filter1, filter2);
		scan.setFilter(filterList);
		
		ResultScanner resultScanner = null;
		try
		{
			resultScanner = table.getScanner(scan);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		List<MovieTO> movieTOs = new ArrayList<>();
		MovieTO movieTO = null;
		if(resultScanner != null)
		{
			Iterator<Result> iter = resultScanner.iterator();
			MovieDao movieDao = new MovieDao();
			while(iter.hasNext())
			{
				Result result = iter.next();
				if(result != null && !result.isEmpty())
				{
					int movieId = Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_GENRE_MOVIE), Bytes.toBytes(ConstantsHBase.QUALIFIER_GENRE_MOVIE_ID)));
					movieTO = movieDao.getMovieById(movieId);
					
					if(StringUtil.isNotEmpty(movieTO.getPosterPath()))
					{
						movieTO.setOrder(100);
					}
					else
					{
						movieTO.setOrder(0);
					}
					
					movieTOs.add(movieTO);
				}
			}
		}
		Collections.sort(movieTOs);
		
		return movieTOs;
	}
	
}
