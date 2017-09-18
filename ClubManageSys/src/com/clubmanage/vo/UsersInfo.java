package com.clubmanage.vo;

import org.springframework.stereotype.Component;

@Component("usersInfo")
public class UsersInfo
{
	
	private String username;
	private String name;
	private String password;
	private String cpassword;
	private String suoshu;
	private String juese;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getCpassword()
	{
		return cpassword;
	}
	public void setCpassword(String cpassword)
	{
		this.cpassword = cpassword;
	}
	public String getSuoshu()
	{
		return suoshu;
	}
	public void setSuoshu(String suoshu)
	{
		this.suoshu = suoshu;
	}
	public String getJuese()
	{
		return juese;
	}
	public void setJuese(String juese)
	{
		this.juese = juese;
	}
	
}
