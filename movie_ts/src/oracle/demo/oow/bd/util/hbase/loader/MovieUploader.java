package oracle.demo.oow.bd.util.hbase.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import oracle.demo.oow.bd.constant.Constant;
import oracle.demo.oow.bd.dao.hbase.MovieDao;
import oracle.demo.oow.bd.to.CastTO;
import oracle.demo.oow.bd.to.CrewTO;
import oracle.demo.oow.bd.to.MovieTO;

/**
 * 导入电影相关数据，在movie-info中
 * @author Wenc
 *
 */
public class MovieUploader
{
	
	public static void main(String[] args)
	{
		MovieUploader movieUploader = new MovieUploader();
		try
		{
			movieUploader.uploadMovieInfo();
			movieUploader.uploadMovieCast();
			movieUploader.uploadMovieCrew();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadMovieInfo() throws IOException {
		FileReader fr = null;
		MovieDao movieDao = new MovieDao();
		try {
			fr = new FileReader(Constant.WIKI_MOVIE_INFO_FILE_NAME);
			BufferedReader br = new BufferedReader(fr);
			String jsonTxt = null;
			MovieTO movieTO = null;
			int count = 1;
			
			//Each line in the file is the JSON string
			
			//Construct MovieTO from JSON object
			while ((jsonTxt = br.readLine()) != null) {
				
				try {
				    movieTO = new MovieTO(jsonTxt.trim());
				}
				catch (Exception e) {
				    System.out.println("ERROR: Not able to parse the json string: \t" + jsonTxt);
				}
				
				if (movieTO != null && !movieTO.isAdult()) {
				    /**
					 * Save the movie into the kv-store or rdbms
					 */
				    movieDao.insertInfo(movieTO);
				    System.out.println(count++ + " " + movieTO.getMovieJsonTxt());
				} //EOF if
			} //EOF while
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fr.close();
		}	
	}
	
	public void uploadMovieCast() throws IOException {
        FileReader fr = null;
        MovieDao movieDao = new MovieDao();
        try {
            fr = new FileReader(Constant.WIKI_MOVIE_CAST_FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String jsonTxt = null;
            CastTO castTO = null;
            int count = 1;

            //Each line in the file is the JSON string

            //Construct MovieTO from JSON object
            while ((jsonTxt = br.readLine()) != null) {
                try {
                    castTO = new CastTO(jsonTxt.trim());
                } catch (Exception e) {
                    System.out.println("ERROR: Not able to parse the json string: \t" +
                                       jsonTxt);
                }

                if (castTO != null) {
                    /**
                     * Save the movie into the kv-store
                     */
                    movieDao.insertCast(castTO);
                	System.out.println(count++ + " " + castTO.getJsonTxt());
                } //EOF if

            } //EOF while
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }
    }
	
	public void uploadMovieCrew() throws IOException {
        FileReader fr = null;
        MovieDao movieDao = new MovieDao();
        try {
            fr = new FileReader(Constant.WIKI_MOVIE_CREW_FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String jsonTxt = null;
            CrewTO crewTO = null;
            int count = 1;

            //Each line in the file is the JSON string

            //Construct MovieTO from JSON object
            while ((jsonTxt = br.readLine()) != null) {
                try {
                    crewTO = new CrewTO(jsonTxt.trim());
                } catch (Exception e) {
                    System.out.println("ERROR: Not able to parse the json string: \t" +
                                       jsonTxt);
                }

                if (crewTO != null) {
                    /**
                     * Save the movie into the kv-store
                     */
                    movieDao.insertCrew(crewTO);
                	System.out.println(count++ + " " + crewTO.getJsonTxt());
                } //EOF if

            } //EOF while
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }
    }
}
