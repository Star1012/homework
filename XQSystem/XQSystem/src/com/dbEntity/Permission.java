package com.dbEntity;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */

public class Permission implements java.io.Serializable {

	// Fields

	private String role;
	private Boolean manage;
	private Boolean addDemand;
	private Boolean demandManage;
	private Boolean pmManage;
	private Boolean formManage;
	private Boolean departManage;
	private Boolean sqMangae;

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** full constructor */
	public Permission(String role, Boolean manage, Boolean addDemand,
			Boolean demandManage, Boolean pmManage, Boolean formManage,
			Boolean departManage, Boolean sqMangae) {
		this.role = role;
		this.manage = manage;
		this.addDemand = addDemand;
		this.demandManage = demandManage;
		this.pmManage = pmManage;
		this.formManage = formManage;
		this.departManage = departManage;
		this.sqMangae = sqMangae;
	}

	// Property accessors

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getManage() {
		return this.manage;
	}

	public void setManage(Boolean manage) {
		this.manage = manage;
	}

	public Boolean getAddDemand() {
		return this.addDemand;
	}

	public void setAddDemand(Boolean addDemand) {
		this.addDemand = addDemand;
	}

	public Boolean getDemandManage() {
		return this.demandManage;
	}

	public void setDemandManage(Boolean demandManage) {
		this.demandManage = demandManage;
	}

	public Boolean getPmManage() {
		return this.pmManage;
	}

	public void setPmManage(Boolean pmManage) {
		this.pmManage = pmManage;
	}

	public Boolean getFormManage() {
		return this.formManage;
	}

	public void setFormManage(Boolean formManage) {
		this.formManage = formManage;
	}

	public Boolean getDepartManage() {
		return this.departManage;
	}

	public void setDepartManage(Boolean departManage) {
		this.departManage = departManage;
	}

	public Boolean getSqMangae() {
		return this.sqMangae;
	}

	public void setSqMangae(Boolean sqMangae) {
		this.sqMangae = sqMangae;
	}

}