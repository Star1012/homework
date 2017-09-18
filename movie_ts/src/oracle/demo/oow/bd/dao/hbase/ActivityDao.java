package oracle.demo.oow.bd.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.demo.oow.bd.pojo.ActivityType;
import oracle.demo.oow.bd.pojo.RatingType;

import oracle.demo.oow.bd.to.ActivityTO;
import oracle.demo.oow.bd.to.MovieTO;

import oracle.demo.oow.bd.util.FileWriterUtil;
import oracle.demo.oow.bd.util.hbase.ConstantsHBase;
import oracle.demo.oow.bd.util.hbase.HBaseDB;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

public class ActivityDao
{
	
    public void insertCustomerActivity(ActivityTO activityTO) {
        int custId = 0;
        int movieId = 0;
        ActivityType activityType = null;
        String jsonTxt = null;

        if (activityTO != null) {
            jsonTxt = activityTO.getJsonTxt();
            System.out.println("User Activity| " + jsonTxt);
            
            /**
             * This system out should write the content to the application log
             * file.
             */
            FileWriterUtil.writeOnFile(activityTO.getActivityJsonOriginal().toString());
            
            custId = activityTO.getCustId();
            movieId = activityTO.getMovieId();

            if (custId > 0 && movieId > 0) {
                activityType = activityTO.getActivity();
                
                HBaseDB db = HBaseDB.getInstance();
                
                Long id = db.getId(ConstantsHBase.TABLE_GID, ConstantsHBase.FAMILY_GID_GID, ConstantsHBase.QUALIFIER_GID_ACTIVITY_ID);
                
                Table table = db.getTable(ConstantsHBase.TABLE_ACTIVITY);
                Put put = new Put(Bytes.toBytes(id));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_MOVIE_ID), Bytes.toBytes(activityTO.getMovieId()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_ACTIVITY), Bytes.toBytes(activityTO.getActivity().getValue()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_GENRE_ID), Bytes.toBytes(activityTO.getGenreId()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_POSITION), Bytes.toBytes(activityTO.getPosition()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_PRICE), Bytes.toBytes(activityTO.getPrice()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_RATING), Bytes.toBytes(activityTO.getRating().getValue()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_RECOMMENDED), Bytes.toBytes(activityTO.isRecommended().getValue()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_TIME), Bytes.toBytes(activityTO.getTimeStamp()));
                put.addColumn(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_USER_ID), Bytes.toBytes(activityTO.getCustId()));
                
                try
                {
                	table.put(put);
                	table.close();
                }
                catch(IOException e)
                {
                	e.printStackTrace();
                }
            }
        }
    }
    
    public List<MovieTO> getCustomerBrowseList(int custId)
    {
    	List<MovieTO> movieTOs = new ArrayList<>();
    	HBaseDB hBaseDB = HBaseDB.getInstance();
    	Table table = hBaseDB.getTable(ConstantsHBase.TABLE_ACTIVITY);
    	Scan scan = new Scan();
    	Filter filter1 = new SingleColumnValueFilter(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY),
    			Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_USER_ID), CompareOp.EQUAL, Bytes.toBytes(custId));
    	Filter filter2 = new SingleColumnValueFilter(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY),
    			Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_ACTIVITY), CompareOp.EQUAL, Bytes.toBytes(5));
    	FilterList filterList = new FilterList(filter1, filter2);
    	scan.setFilter(filterList);
    	
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
    	List<Integer> movieIds = new ArrayList<>();
    	if(resultScanner != null)
    	{
    		Iterator<Result> iter = resultScanner.iterator();
    		while(iter.hasNext())
    		{
    			Result result = iter.next();
    			
    			int movieId = Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_MOVIE_ID)));
    			if(!movieIds.contains(movieId))
    			{
    				movieIds.add(movieId);
        			movieTO = movieDao.getMovieById(movieId);
        			movieTOs.add(movieTO);
    			}
    		}
    	}
    	
    	return movieTOs;
    }
    
    public ActivityTO getMovieRating(int userId, int movieId)
    {
    	ActivityTO activityTO = new ActivityTO();
    	HBaseDB hBaseDB = HBaseDB.getInstance();
    	Table table = hBaseDB.getTable(ConstantsHBase.TABLE_ACTIVITY);
    	Scan scan = new Scan();
    	Filter filter1 = new SingleColumnValueFilter(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY),
    			Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_USER_ID), CompareOp.EQUAL, Bytes.toBytes(userId));
    	Filter filter2 = new SingleColumnValueFilter(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY),
    			Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_MOVIE_ID), CompareOp.EQUAL, Bytes.toBytes(movieId));
    	Filter filter3 = new SingleColumnValueFilter(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY),
    			Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_ACTIVITY), CompareOp.EQUAL, Bytes.toBytes(1));
    	FilterList filterList = new FilterList(filter1, filter2, filter3);
    	scan.setFilter(filterList);
    	
    	ResultScanner resultScanner = null;
    	try
		{
			resultScanner = table.getScanner(scan);
		}
    	catch (IOException e)
		{
			e.printStackTrace();
		}
    	
    	if(resultScanner != null)
    	{
    		Iterator<Result> iter = resultScanner.iterator();
    		while(iter.hasNext())
    		{
    			Result result = iter.next();
    			
    			activityTO.setRating(RatingType.getType(Bytes.toInt(result.getValue(Bytes.toBytes(ConstantsHBase.FAMILY_ACTIVITY_ACTIVITY), Bytes.toBytes(ConstantsHBase.QUALIFIER_ACTIVITY_RATING)))));
    		}
    	}
    	
    	return activityTO;
    }
    
}
