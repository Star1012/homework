package com.stdcMis.Service.demand;

import com.dbEntity.DemandCollect;

public interface UpdateDemandManager {

	public abstract DemandCollect getDemandCollect(String id);
	
	public abstract String collectMessage(String[] str);
	
	public abstract void initStatus(DemandCollect demc,String add);
	
	public abstract boolean update(DemandCollect demc);
	
	public abstract String execute(DemandCollect demc);
}
