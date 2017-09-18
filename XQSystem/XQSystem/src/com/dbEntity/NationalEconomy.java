package com.dbEntity;

/**
 * NationalEconomy entity. @author MyEclipse Persistence Tools
 */

public class NationalEconomy implements java.io.Serializable {

	// Fields

	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public NationalEconomy() {
	}

	/** minimal constructor */
	public NationalEconomy(String code) {
		this.code = code;
	}

	/** full constructor */
	public NationalEconomy(String code, String name) {
		this.code = code;
		this.name = name;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}