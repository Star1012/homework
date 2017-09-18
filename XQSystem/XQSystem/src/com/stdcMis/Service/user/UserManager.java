package com.stdcMis.Service.user;

import com.dbEntity.OperatingOrganization;

public interface UserManager {

	public abstract OperatingOrganization getOpogByName(String name);
}
