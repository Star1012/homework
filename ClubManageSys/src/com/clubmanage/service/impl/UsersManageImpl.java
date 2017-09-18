package com.clubmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.clubmanage.dao.UsersDao;
import com.clubmanage.model.Users;
import com.clubmanage.service.UsersManage;

@Component("usersManage")
public class UsersManageImpl implements UsersManage
{

	private UsersDao usersDao;
	
	@Override
	public void addUser(Users user)
	{
		usersDao.saveUser(user);
	}

	@Override
	public void deleteUser(String username)
	{
		usersDao.deleteUserByUsername(username);
	}

	@Override
	public void updateUser(Users user)
	{
		usersDao.updateUser(user);
	}

	@Override
	public List<Users> listUsers()
	{
		return usersDao.getUsers();
	}

	@Override
	public boolean userExists(Users user)
	{
		return usersDao.checkUserExistsWithUsername(user.getUsername());
	}

	@Override
	public boolean userLogin(Users user)
	{
		return usersDao.checkUserByUsernamePassword(user);
	}

	@Override
	public Users loadUserByUsername(String username)
	{
		return usersDao.loadByUsername(username);
	}

	public UsersDao getUsersDao()
	{
		return usersDao;
	}

	@Resource
	public void setUsersDao(UsersDao usersDao)
	{
		this.usersDao = usersDao;
	}

}
