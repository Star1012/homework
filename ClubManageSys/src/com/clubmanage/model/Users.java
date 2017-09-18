package com.clubmanage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Users", schema = "dbo", catalog = "db_ClubActivity", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Users implements java.io.Serializable
{

	// Fields

	private String username;
	private String name;
	private String password;
	private String suoshu;
	private String juese;

	// Constructors

	/** default constructor */
	public Users()
	{
	}

	/** full constructor */
	public Users(String username, String name, String password, String suoshu,
			String juese)
	{
		this.username = username;
		this.name = name;
		this.password = password;
		this.suoshu = suoshu;
		this.juese = juese;
	}

	// Property accessors
	@Id
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "password", nullable = false)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "suoshu", nullable = false)
	public String getSuoshu()
	{
		return this.suoshu;
	}

	public void setSuoshu(String suoshu)
	{
		this.suoshu = suoshu;
	}

	@Column(name = "juese", nullable = false)
	public String getJuese()
	{
		return this.juese;
	}

	public void setJuese(String juese)
	{
		this.juese = juese;
	}

}