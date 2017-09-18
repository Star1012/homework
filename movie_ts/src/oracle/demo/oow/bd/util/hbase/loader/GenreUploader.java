package oracle.demo.oow.bd.util.hbase.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import oracle.demo.oow.bd.constant.Constant;
import oracle.demo.oow.bd.constant.KeyConstant;
import oracle.demo.oow.bd.dao.hbase.GenreDao;
import oracle.demo.oow.bd.to.GenreTO;
import oracle.demo.oow.bd.to.MovieTO;

/**
 * 导入分类相关数据，在movie-info中
 * @author Wenc
 *
 */
public class GenreUploader
{
	
	public static void main(String[] args)
	{
		GenreUploader genreUploader = new GenreUploader();
		try
		{
			genreUploader.uploadMovieGenre();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadMovieGenre() throws IOException {
		FileReader fr = null;
		GenreDao genreDao = new GenreDao();
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
				    genreDao.insert(movieTO);
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
}
