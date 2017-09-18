package com.struts.action;

import javax.annotation.Resource;

import com.dbEntity.Permission;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stdcMis.Service.system.AuthorityManager;
import com.stdcMis.Vo.PermissionInfo;

public class AddRole extends ActionSupport implements ModelDriven{

	private PermissionInfo info = new PermissionInfo();
	private Permission permission = new Permission();
	private AuthorityManager am;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.print(info.getRole());
		System.out.println(info.isAddManage());
		System.out.println(info.isDemandManage());
		System.out.println(info.isDepartManage());
		System.out.println(info.isFormManage());
		System.out.println(info.isManage());
		System.out.println(info.isPmManage());
		permission.setRole(info.getRole());
		permission.setAddDemand(info.isAddManage());
		
		permission.setDemandManage(info.isDemandManage());
		permission.setFormManage(info.isFormManage());
		permission.setDepartManage(info.isDepartManage());
		permission.setPmManage(info.isPmManage());
		permission.setSqMangae(info.isSqMangae());
		permission.setManage(info.isManage());
		return am.execute(permission);
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return info;
	}

	public AuthorityManager getAm() {
		return am;
	}

	@Resource(name="authorityManager")
	public void setAm(AuthorityManager am) {
		this.am = am;
	}
	
	
}
