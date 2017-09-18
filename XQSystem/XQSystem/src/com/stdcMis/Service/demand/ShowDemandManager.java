package com.stdcMis.Service.demand;

import java.util.List;
import java.util.Map;

import com.dbEntity.DemandCollect;
import com.dbEntity.SearchResult;

public interface ShowDemandManager {

	public abstract List<DemandCollect> listAllDemand(String name);
	
	public abstract List<DemandCollect> listUpdateList(String name);
	
	public abstract List<DemandCollect> listSubmitDemand();
	
	public abstract List<DemandCollect> listFormedDemand();
	
	public abstract void initParameter(Map<String,Object> map);
	
	public abstract List<SearchResult> listDemandByCondition();
	
	public abstract DemandCollect getDemandCollect(String id);
	
	public abstract List<DemandCollect> execute(String type,String name);
	
	public List<SearchResult> query(String sql);
	
}
