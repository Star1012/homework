package com.struts.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.Subject;

import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.DemandCollect;
import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.demand.GetSubandNaeManager;
import com.stdcMis.Service.demand.ShowDemandManager;

public class ShowUpdateDemand extends ActionSupport implements RequestAware {

	private String id;
	private Map<String,Object> demand;
	private ShowDemandManager show;
	private GetSubandNaeManager getSN;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DemandCollect dc = show.getDemandCollect(this.getId());
		List firstSub = getSN.getFirstSubject();
		List firstNae = getSN.getFirstIndustry();
		demand.put("demandCollect",dc);
		demand.put("firstSubject", firstSub);
		demand.put("firstNationalEconomy", firstNae);
		return SUCCESS;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		demand = arg0;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ShowDemandManager getShow() {
		return show;
	}

	@Resource
	public void setShow(ShowDemandManager show) {
		this.show = show;
	}

	public GetSubandNaeManager getGetSN() {
		return getSN;
	}

	@Resource
	public void setGetSN(GetSubandNaeManager getSN) {
		this.getSN = getSN;
	}
	
}
