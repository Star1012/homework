package com.stdcMis.Service.demandImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.stdcMis.Dao.DBDao;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.demand.AddAndSaveDemandManager;

@Component("addAndSaveDemandManager")
public class AddAndSaveDemandManagerImpl implements AddAndSaveDemandManager {

	private DBDao demcDao;
	private OpogDao opogDao;
	
	
	@Override
	public void initStatus(DemandCollect deco, Object add, Object save) {
		// TODO Auto-generated method stub
		if(save!=null && save.toString().equals("保存"))
		{
			deco.setDStatus(0);
		}
		if(add!=null && add.toString().equals("提交"))
		{
			deco.setDStatus(1);
		}
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.stdcMis.Service.demand.AddDemandManager#collectMessage(java.lang.String[])
	 */
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

	/*
	 * (non-Javadoc)
	 * @see com.stdcMis.Service.demand.AddDemandManager#setID()
	 */
	@Override
	public String setID() {
		// TODO Auto-generated method stub
		return demcDao.setID();
	}

	/*
	 * (non-Javadoc)
	 * @see com.stdcMis.Service.demand.AddDemandManager#getOpogByName(java.lang.String)
	 */
	@Override
	public OperatingOrganization getOpogByName(String name) {
		// TODO Auto-generated method stub
		return opogDao.get(name);
	}


	/*
	 * (non-Javadoc)
	 * @see com.stdcMis.Service.demand.AddDemandManager#execute()
	 */
	@Override
	public String execute(DemandCollect deco) {
		// TODO Auto-generated method stub
		if(demcDao.save(deco))
		{
			return "success";
		}
		return "error";
	}
	
	public DBDao getDemcDao() {
		return demcDao;
	}

	@Resource
	public void setDemcDao(DBDao demcDao) {
		this.demcDao = demcDao;
	}

	public OpogDao getOpogDao() {
		return opogDao;
	}
    
	@Resource
	public void setOpogDao(OpogDao opogDao) {
		this.opogDao = opogDao;
	}

	


}
