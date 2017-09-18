package com.struts.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class RoleBroswerAction extends ActionSupport {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private String Oname;

	public String getOname() {
		return Oname;
	}
	public void setOname(String oname) {
		Oname = oname;
	}
	
	
	
	
}
