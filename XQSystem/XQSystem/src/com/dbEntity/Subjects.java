package com.dbEntity;

/**
 * Subjects entity. @author MyEclipse Persistence Tools
 */

public class Subjects implements java.io.Serializable {

	// Fields

	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public Subjects() {
	}

	/** minimal constructor */
	public Subjects(String code) {
		this.code = code;
	}

	/** full constructor */
	public Subjects(String code, String name) {
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