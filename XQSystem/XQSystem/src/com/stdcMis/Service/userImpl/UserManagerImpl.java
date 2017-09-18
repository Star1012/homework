package com.stdcMis.Service.userImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dbEntity.OperatingOrganization;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.user.UserManager;

@Component("userManager")
public class UserManagerImpl implements UserManager {

	OpogDao od;
	
	@Override
	public OperatingOrganization getOpogByName(String name) {
		// TODO Auto-generated method stub
		return od.get(name);
	}

	public OpogDao getOd() {
		return od;
	}

	@Resource(name="opogDao")
	public void setOd(OpogDao od) {
		this.od = od;
	}

}
