package Util;

import java.util.regex.Pattern;

public class Validater {
	
	public boolean isUrl(String url)
	{
		Pattern exp =Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", Pattern.CASE_INSENSITIVE);
		return exp.matcher(url).matches();
	}
}
