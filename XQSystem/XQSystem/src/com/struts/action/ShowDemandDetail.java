package com.struts.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.demand.ShowDemandManager;
import com.stdcMis.Service.user.UserManager;

public class ShowDemandDetail extends ActionSupport implements RequestAware {

	private Map<String,Object> demand;
	private UserManager um;
	private ShowDemandManager show;
	private String id;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DemandCollect dc = show.getDemandCollect(this.getId());
		String name = dc.getOperatingOrganization().getOName();
		OperatingOrganization opog = um.getOpogByName(name);
		demand.put("demand", dc);
		demand.put("opog", opog);
		this.setRequest(demand);
		return "success";
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		demand = arg0;
	}

	public ShowDemandManager getShow() {
		return show;
	}

	@Resource
	public void setShow(ShowDemandManager show) {
		this.show = show;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserManager getUm() {
		return um;
	}

	@Resource
	public void setUm(UserManager um) {
		this.um = um;
	}


}
