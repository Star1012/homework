package com.struts.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Dao.NaeDao;
import com.stdcMis.Dao.SubDao;
import com.stdcMis.Service.demand.GetSubandNaeManager;

public class SetSubandNaeAction extends ActionSupport {

	private GetSubandNaeManager getSubandNaeManager;
	private String message = ERROR;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		message = getSubandNaeManager.execute();
		return message;
	}

	public GetSubandNaeManager getGetSubandNaeManager() {
		return getSubandNaeManager;
	}

	@Resource
	public void setGetSubandNaeManager(GetSubandNaeManager getSubandNaeManager) {
		this.getSubandNaeManager = getSubandNaeManager;
	}

	
}
