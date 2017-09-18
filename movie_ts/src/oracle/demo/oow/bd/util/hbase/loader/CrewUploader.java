package oracle.demo.oow.bd.util.hbase.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import oracle.demo.oow.bd.constant.Constant;
import oracle.demo.oow.bd.dao.hbase.CrewDao;
import oracle.demo.oow.bd.to.CrewTO;

/**
 * 导入职务相关数据，在movie-crew中
 * @author Wenc
 *
 */
public class CrewUploader
{
	
	public static void main(String[] args)
	{
		CrewUploader crewUploader = new CrewUploader();
		try
		{
			crewUploader.uploadMovieCrew();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadMovieCrew() throws IOException {
        FileReader fr = null;
        CrewDao crewDao = new CrewDao();
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
                    crewDao.insert(crewTO);
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
