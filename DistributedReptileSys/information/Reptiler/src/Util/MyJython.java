package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyJython {
	
	public void executePy(String pathName)
	{
		try{  
            System.out.println("start"); 
            String exeString = "python "+pathName;
            Process pr = Runtime.getRuntime().exec(exeString);  
            BufferedReader in = new BufferedReader(new  
                    InputStreamReader(pr.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                System.out.println(line);  
            }  
            in.close();  
            pr.waitFor();  
            System.out.println("end");  
		} catch (Exception e){  
                e.printStackTrace();  
        }  
	}
	
	public String executePyandRt(String pathName)
	{
		String line="";
		try{  
            System.out.println("start"); 
            String exeString = "python "+pathName;
            Process pr = Runtime.getRuntime().exec(exeString);  
            BufferedReader in = new BufferedReader(new  
                    InputStreamReader(pr.getInputStream()));   
            while (in.readLine()!= null) {
            	line+="&"+in.readLine();  
            }  
            System.out.println(line);
            in.close();  
            pr.waitFor();  
            System.out.println("end");  
		} catch (Exception e){  
                e.printStackTrace();  
        }  
		return line;
	}
}


