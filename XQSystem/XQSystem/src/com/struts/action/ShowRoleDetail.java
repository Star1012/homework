package com.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dbEntity.Permission;
import com.opensymphony.xwork2.ActionSupport;
import com.stdcMis.Service.system.AuthorityManager;

public class ShowRoleDetail extends ActionSupport{
	
	private String role;
	private AuthorityManager am;

	public void getPermission() {
		// TODO Auto-generated method stub
		Permission p = am.getRole(this.getRole());
		JSONObject permission = new JSONObject(); 
		permission.put("manage",p.getManage());
		permission.put("addManage",p.getAddDemand());
		permission.put("formManage",p.getFormManage());
		permission.put("departManage",p.getDepartManage());
		permission.put("sqMangae",p.getSqMangae());
		permission.put("demandManage",p.getDemandManage());
		permission.put("pmManage",p.getPmManage());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write(permission.toString());
			writer.flush();  
			writer.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AuthorityManager getAm() {
		return am;
	}

	@Resource(name="authortyManager")
	public void setAm(AuthorityManager am) {
		this.am = am;
	}
}
