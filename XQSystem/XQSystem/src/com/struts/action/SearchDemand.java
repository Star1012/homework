package com.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.SearchResult;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stdcMis.Service.demand.ShowDemandManager;
import com.stdcMis.Vo.SearchInfo;

public class SearchDemand extends ActionSupport implements RequestAware,ModelDriven {

	private Map<String,Object> demand;
	private Map<String,Object> map = new HashMap();
	SearchInfo info = new SearchInfo();
	private ShowDemandManager show;
	
	public void setMap()
	{
		map.put("Status",info.getStatus());
		
		map.put("Oname",info.getOname());
		map.put("OnSearchType",info.getOnSearchType());
		map.put("Tmd",info.getTmd());
		map.put("TmSearchType",info.getTmSearchType());
		map.put("Area", info.getArea());
		map.put("ArSearchType",info.getArSearchType());
		map.put("Lperson", info.getLperson());
		map.put("LpSearchType",info.getLpSearchType());
		map.put("Contacts", info.getContacts());
		map.put("CoSearchType",info.getCoSearchType());
		map.put("Attribute", info.getAttribute());
		map.put("AtSearchType",info.getAtSearchType());
		
		map.put("Dname", info.getDname());
		map.put("DnSearchType",info.getDnSearchType());
		map.put("Key", info.getKey());
		map.put("KeSearchType",info.getKeSearchType());
		map.put("Investment", info.getInvestment());
		map.put("InvSearchType",info.getInvSearchType());
		map.put("Solution", info.getSolution());
		map.put("SoSearchType",info.getSoSearchType());
		map.put("Type", info.getType());
		map.put("TySearchType",info.getTySearchType());
		map.put("Subject", info.getSubject());
		map.put("SuSearchType",info.getSuSearchType());
		map.put("Services", info.getServics());
		map.put("SeSearchType",info.getSeSearchType());
		map.put("Industry", info.getIndustry());
		map.put("IndSearchType",info.getIndSearchType());
		
		map.put("SstartTime",info.getSstartTime());
		map.put("EstartTime",info.getEstartTime());
		map.put("SendTime",info.getSendTime());
		map.put("EendTime",info.getEendTime());
		
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.setMap();
		show.initParameter(map);
		List<SearchResult> list = show.listDemandByCondition();
		demand.put("demandList",list);
		this.setRequest(demand);
		return super.execute();
	}

	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		demand = arg0;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return info;
	}

	public SearchInfo getInfo() {
		return info;
	}

	public void setInfo(SearchInfo info) {
		this.info = info;
	}

	public ShowDemandManager getShow() {
		return show;
	}

	@Resource
	public void setShow(ShowDemandManager show) {
		this.show = show;
	}

	
}
