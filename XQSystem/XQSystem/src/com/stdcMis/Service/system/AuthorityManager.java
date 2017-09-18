package com.stdcMis.Service.system;

import java.util.List;

import com.dbEntity.Permission;

public interface AuthorityManager {

	public abstract List listAllRole();
	
	public abstract Permission getRole(String role);
	
	public abstract boolean checkRoleExsits(String role);
	
	public abstract boolean deleteRoles(String[] roles);
	
	public abstract boolean addRole(Permission permission);
	
	public abstract String execute(Permission permission);
	
	public abstract String execute(String[] roles);
	
}
