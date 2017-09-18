package com.stdcMis.Service.demand;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;

public interface AddAndSaveDemandManager {

	public abstract void initStatus(DemandCollect deco,Object add,Object save);
	public abstract String collectMessage(String[] str);
	public abstract String setID();
	public abstract OperatingOrganization getOpogByName(String name);
	public abstract String execute(DemandCollect deco);
}
