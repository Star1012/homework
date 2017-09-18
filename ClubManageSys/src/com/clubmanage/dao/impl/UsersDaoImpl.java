package com.clubmanage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.clubmanage.dao.UsersDao;
import com.clubmanage.model.Users;

@Component("usersDao")
public class UsersDaoImpl implements UsersDao
{

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveUser(Users user)
	{
		hibernateTemplate.save(user);
	}

	@Override
	public void deleteUserByUsername(String username)
	{
		hibernateTemplate.delete(loadByUsername(username));
	}

	@Override
	public void updateUser(Users user)
	{
		hibernateTemplate.update(user);
	}

	@Override
	public List<Users> getUsers()
	{
		List<Users> users = hibernateTemplate.find("from Users");
		return users;
	}

	@Override
	public boolean checkUserExistsWithUsername(String username)
	{
		List<Users> users = hibernateTemplate.find("from Users u where u.username='" + username + "'");
		if(users.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserByUsernamePassword(Users user)
	{
		List<Users> users = hibernateTemplate.find("from Users u where u.username='" + user.getUsername() + "' and u.password='" + user.getPassword() + "'");
		if(users.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public Users loadByUsername(String username)
	{
		Users user = (Users) hibernateTemplate.load(Users.class, username);
		return user;
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

}
