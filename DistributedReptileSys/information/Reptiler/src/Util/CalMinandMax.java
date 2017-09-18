package Util;

public class CalMinandMax {
	
	//获取一组数中最大和最小的数字
	public String[] getMinandMax(String[] str)
	{
		String[] s = new String[2];
		int max = 0,min = 1000000000;
		for(int i=0;i<str.length;i++)
		{
			System.out.println(str[i]);
			if(max<=Integer.parseInt(str[i]))
			{
				max = Integer.parseInt(str[i]);
			}
			
			if(min>=Integer.parseInt(str[i]))
			{
				min = Integer.parseInt(str[i]);
			}
		}
		s[0] = max+"";
		s[1] = min+"";
		return s;
	}

}
