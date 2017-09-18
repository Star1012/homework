package com.stdcMis.Service.demandImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.stdcMis.Dao.DBDao;
import com.stdcMis.Service.demand.UpdateDemandManager;

@Component("updateDemandManager")
public class UpdateDemandManagerImpl implements UpdateDemandManager {

	DBDao dd;
	
	@Override
	public DemandCollect getDemandCollect(String id) {
		// TODO Auto-generated method stub
		return (DemandCollect)dd.get("com.dbEntity.DemandCollect",id);
	}
	
	@Override
	public String collectMessage(String[] str) {
		// TODO Auto-generated method stub
		String meg ="";
		for(int i=0;i<str.length;i++)
		{
			System.out.println("this is test"+str[i]);
			if(str[i]!=null && !str[i].equals("") && !str[i].equals("default"))
			{
				if(i>0)
				{
					meg+="、";
				}
				meg+=str[i];
			}
		}
		if(meg.equals(""))
		{
			meg=null;
		}
		return meg;
	}
	
	@Override
	public void initStatus(DemandCollect demc,String add) {
		// TODO Auto-generated method stub
		if(add.equals("修改并保存"))
		{
			demc.setDStatus(1);
		}
	}

	@Override
	public boolean update(DemandCollect demc)
	{
		return dd.update(demc);
	}

	
	@Override
	public String execute(DemandCollect demc) {
		// TODO Auto-generated method stub
		if(update(demc))
		{
			return "success";
		}
		return "error";
	}
	
	public DBDao getDd() {
		return dd;
	}

	@Resource(name="demcDao")
	public void setDd(DBDao dd) {
		this.dd = dd;
	}


}
