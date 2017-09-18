package com.struts.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dbEntity.OperatingOrganization;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.user.RegisterManager;
import com.stdcMis.Vo.UserInfo;

public class RegisterAction extends ActionSupport implements ModelDriven {


	private String role = "普通用户";
	private String message = ERROR;
	private OperatingOrganization opog = new OperatingOrganization();
	private UserInfo info = new UserInfo();
	private RegisterManager registerManager;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println(info.getUsername());
		opog.setOName(info.getUsername());
		System.out.println(info.getCode());
		opog.setOCode(info.getCode());
		System.out.println(info.getPassword());
		opog.setPwd(info.getPassword());
		System.out.println(info.getAddress());
		opog.setOAddress(info.getAddress());
		System.out.println(info.getArea());
		opog.setOArea(info.getArea());
		System.out.println(info.getUrl());
		opog.setOUrl(info.getUrl());
		System.out.println(info.getEmail());
		opog.setOEmail(info.getEmail());
		System.out.println(info.getLperson());
		opog.setOLperson(info.getLperson());
		System.out.println(info.getPostcode());
		opog.setOPostcode(info.getPostcode());
		System.out.println(info.getContacts());
		opog.setOContacts(info.getContacts());
		System.out.println(info.getPlphone());
		opog.setOPlPhone(info.getPlphone());
		System.out.println(info.getMbphone());
		opog.setOMbPhone(info.getMbphone());
		System.out.println(info.getFax());
		opog.setOFax(info.getFax());
		System.out.println(info.getAttribute());
		opog.setOAttribute(info.getAttribute());
		System.out.println(info.getTmd());
		opog.setOTmd(info.getTmd());
		System.out.println(info.getOverView());
		opog.setOOverView(info.getOverView());
		opog.setRole(role);
		opog.setDemandCollects(null);
		message = registerManager.execute(opog);
		System.out.print(message);
		return message;
	}

	
	public UserInfo getInfo() {
		return info;
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}


	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return info;
	}


	public RegisterManager getRegisterManager() {
		return registerManager;
	}

	@Resource
	public void setRegisterManager(RegisterManager registerManager) {
		this.registerManager = registerManager;
	}
	
	
	
}
