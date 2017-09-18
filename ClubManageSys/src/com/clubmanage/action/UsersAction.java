package com.clubmanage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.clubmanage.model.Users;
import com.clubmanage.service.UsersManage;
import com.clubmanage.vo.UsersInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("usersAction")
public class UsersAction extends ActionSupport implements ModelDriven
{
	
	private static final long serialVersionUID = 1L;
	
	private Users user;
	private List<Users> users;
	private UsersInfo usersInfo;
	private UsersManage usersManage;
	
	public String Create()
	{
		Users u  = new Users();
		u.setUsername(usersInfo.getUsername());
		u.setName(usersInfo.getName());
		u.setPassword(usersInfo.getPassword());
		u.setSuoshu(usersInfo.getSuoshu());
		u.setJuese("普通");
		if(usersManage.userExists(u))
		{
			return "failed";
		}
		else
		{
			usersManage.addUser(u);
			return "success";
		}
	}
	
	/*
	public String Retrieve()
	{
		return "success";
	}
	*/
	
	public String Logining()
	{
		Users u = new Users();
		u.setUsername(usersInfo.getUsername());
		u.setPassword(usersInfo.getPassword());
		
		Users um = new Users();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(usersManage.userLogin(u))
		{
			um = usersManage.loadUserByUsername(u.getUsername());
			request.getSession().setAttribute("userKey", um);
			return "success";
		}
		else
		{
			return "failed";
		}
	}
	
	public String Update()
	{
		Users u = new Users();
		u.setUsername(usersInfo.getUsername());
		u.setJuese(usersInfo.getJuese());
		u.setName(usersInfo.getName());
		u.setPassword(usersInfo.getPassword());
		u.setSuoshu(usersInfo.getSuoshu());
		usersManage.updateUser(u);
		return "success";
	}
	
	public String Delete()
	{
		usersManage.deleteUser(usersInfo.getUsername());
		return "success";
	}
	
	public Users getUser()
	{
		return user;
	}
	public void setUser(Users user)
	{
		this.user = user;
	}
	public List<Users> getUsers()
	{
		return users;
	}
	public void setUsers(List<Users> users)
	{
		this.users = users;
	}
	public UsersInfo getUsersInfo()
	{
		return usersInfo;
	}
	@Resource
	public void setUsersInfo(UsersInfo usersInfo)
	{
		this.usersInfo = usersInfo;
	}
	public Object getModel()
	{
		return this.usersInfo;
	}
	public UsersManage getUsersManage()
	{
		return usersManage;
	}
	@Resource
	public void setUsersManage(UsersManage usersManage)
	{
		this.usersManage = usersManage;
	}
}
