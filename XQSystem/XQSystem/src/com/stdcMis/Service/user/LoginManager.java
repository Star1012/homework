package com.stdcMis.Service.user;

import com.dbEntity.OperatingOrganization;

public interface LoginManager {
	
	/*
	 * 判断用户名密码是否正确
	 */
	public abstract boolean isUser(OperatingOrganization opog) throws Exception;
	
	/*
	 * 获取用户权限
	 */
	public abstract void getPermission(OperatingOrganization opog) throws Exception;
	
	/*
	 * 添加新用户
	 */
	public abstract String execute(OperatingOrganization opog) throws Exception;
}
