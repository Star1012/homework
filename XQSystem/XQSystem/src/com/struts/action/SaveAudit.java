package com.struts.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.demand.DemandAuditManager;

public class SaveAudit extends ActionSupport{
	
	private String status;
	private String form;
	private String idea;
	private String department;
	private String depart;
	private String id;
	
	private DemandAuditManager da;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getId());
		return da.execute(this.getStatus(), this.getForm(),this.getIdea(),
				this.getDepartment(),this.getDepart(),this.getId());
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DemandAuditManager getDa() {
		return da;
	}

	@Resource
	public void setDa(DemandAuditManager da) {
		this.da = da;
	}

}
