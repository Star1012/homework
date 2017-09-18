package com.struts.action;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.system.AuthorityManager;

public class DeleteRoles extends ActionSupport {

	private String[] roles;
	private AuthorityManager am;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return am.execute(this.getRoles());
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public AuthorityManager getAm() {
		return am;
	}

	@Resource(name = "authorityManager")
	public void setAm(AuthorityManager am) {
		this.am = am;
	}


}
