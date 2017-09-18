package com.stdcMis.Service.user;

import com.dbEntity.OperatingOrganization;

public interface RegisterManager {

	public boolean register(OperatingOrganization opog);
	public String execute(OperatingOrganization opog);
}
