package oracle.demo.oow.bd.util.hbase.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import oracle.demo.oow.bd.constant.Constant;
import oracle.demo.oow.bd.dao.hbase.CastDao;
import oracle.demo.oow.bd.to.CastTO;

/**
 * 导入角色相关数据，在movie-cast中
 * @author Wenc
 *
 */
public class CastUploader
{
	
	public static void main(String[] args)
	{
		CastUploader castUploader = new CastUploader();
		try
		{
			castUploader.uploadMovieCast();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void uploadMovieCast() throws IOException {
        FileReader fr = null;
        CastDao castDao = new CastDao();
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
                    castDao.insert(castTO);
                	System.out.println(count++ + " " + castTO.getJsonTxt());
                } //EOF if

            } //EOF while
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }
    } //uploadMovies
	
}
