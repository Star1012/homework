package Util;

import java.util.ArrayList;
import java.util.List;

public class getStr {
	
	//获取一串字符串中的所有数字
	public String getNumfromStr(String str)
	{
		str=str.trim();
		String str2="";
		if(str != null && !"".equals(str))
		{
			for(int i=0;i<str.length();i++)
			{
				if(str.charAt(i)>=48 && str.charAt(i)<=57)
				{
					str2+=str.charAt(i);
				}
			}
		}
		return str2;
	}
}
