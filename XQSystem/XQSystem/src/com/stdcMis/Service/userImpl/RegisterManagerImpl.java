package com.stdcMis.Service.userImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dbEntity.OperatingOrganization;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.user.RegisterManager;

@Component
public class RegisterManagerImpl implements RegisterManager {

	private OpogDao opogDao;
	
	@Override
	public boolean register(OperatingOrganization opog) {
		// TODO Auto-generated method stub
		System.out.print(opog.getOName());
		return opogDao.save(opog);
	}

	@Override
	public String execute(OperatingOrganization opog) {
		// TODO Auto-generated method stub
		try{
			if(register(opog))
			{
				return "success";
			}
		}catch(Exception e)
		{
			System.out.print(e);
		}
		return "error";
		
	}
	
	public OpogDao getOpogDao() {
		return opogDao;
	}

	@Resource
	public void setOpogDao(OpogDao opogDao) {
		this.opogDao = opogDao;
	}

	

}
