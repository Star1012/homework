package com.struts.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.DemandCollect;
import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.demand.ShowDemandManager;

public class ShowDemandList extends ActionSupport implements RequestAware {

	private Map<String,Object> map;
	private ShowDemandManager show;
	HttpServletRequest request = ServletActionContext.getRequest();
	String oname = (String) request.getSession().getAttribute("user");
	private String status;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("开始处理*******************************");
		List<DemandCollect> list = show.execute(this.getStatus(),oname);;
		map.put("demandList", list);
		this.setRequest(map);
		return SUCCESS;
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		map = arg0;
	}

	public ShowDemandManager getShow() {
		return show;
	}

	@Resource
	public void setShow(ShowDemandManager show) {
		this.show = show;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
