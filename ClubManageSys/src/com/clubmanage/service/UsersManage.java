package com.clubmanage.service;

import java.util.List;

import com.clubmanage.model.Users;

public interface UsersManage
{
	public void addUser(Users user);
	public void deleteUser(String username);
	public void updateUser(Users user);
	public List<Users> listUsers();
	public boolean userExists(Users user);
	public boolean userLogin(Users user);
	public Users loadUserByUsername(String username);
}
