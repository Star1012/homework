package com.struts.action;


import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.dbEntity.OperatingOrganization;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stdcMis.Service.user.LoginManager;
import com.stdcMis.Vo.UserInfo;

@Component("login")
@Scope("prototype")
public class LoginAction extends ActionSupport implements ModelDriven{

	String message = ERROR;
	private UserInfo info = new UserInfo();
	private LoginManager loginManager;
	
	
	
	@Override
	public String execute() throws Exception {
		
		OperatingOrganization opog = new OperatingOrganization();
		opog.setOName(info.getUsername());
		opog.setPwd(info.getPassword());
		message = loginManager.execute(opog);
		System.out.println(message);
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



	public LoginManager getLoginManager() {
		return loginManager;
	}


	@Resource(name="loginManager")
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	
	
}
