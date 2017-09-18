package com.stdcMis.Vo;

public class PermissionInfo {
	
	private String role;
	private boolean manage;
	private boolean addManage;
	private boolean formManage;
	private boolean departManage;
	private boolean SqMangae;
	private boolean demandManage;
	private boolean pmManage;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isManage() {
		return manage;
	}
	public void setManage(boolean manage) {
		this.manage = manage;
	}
	public boolean isAddManage() {
		return addManage;
	}
	public void setAddManage(boolean addManage) {
		this.addManage = addManage;
	}
	public boolean isFormManage() {
		return formManage;
	}
	public void setFormManage(boolean formManage) {
		this.formManage = formManage;
	}
	public boolean isDepartManage() {
		return departManage;
	}
	public void setDepartManage(boolean departManage) {
		this.departManage = departManage;
	}
	public boolean isSqMangae() {
		return SqMangae;
	}
	public void setSqMangae(boolean sqMangae) {
		SqMangae = sqMangae;
	}
	public boolean isDemandManage() {
		return demandManage;
	}
	public void setDemandManage(boolean demandManage) {
		this.demandManage = demandManage;
	}
	public boolean isPmManage() {
		return pmManage;
	}
	public void setPmManage(boolean pmManage) {
		this.pmManage = pmManage;
	}
	
	
}
