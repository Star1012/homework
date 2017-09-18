package com.dbEntity;

public class SearchResult {
	
	private String id;
	private String oname;
	private String dname;
	private String startTime;
	private String endTime;
	private Integer status;
	
	
	
	public SearchResult(String id, String oname, String dname,
			String startTime, String endTime, Integer status) {
		super();
		this.id = id;
		this.oname = oname;
		this.dname = dname;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
