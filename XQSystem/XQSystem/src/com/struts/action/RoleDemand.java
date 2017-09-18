package com.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.Permission;
import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.system.AuthorityManager;

public class RoleDemand extends ActionSupport implements RequestAware {

	private AuthorityManager am;
	private Map<String,Object> role;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<Permission> list = am.listAllRole();
		System.out.println("bbbb");
		role.put("role",list);
		this.setRequest(role);
		return SUCCESS;
	}
	
	
	public AuthorityManager getAm() {
		return am;
	}

	@Resource(name="authorityManager")
	public void setAm(AuthorityManager am) {
		this.am = am;
	}


	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		role = arg0;
	}

}
