package com.clubmanage.dao;

import java.util.List;

import com.clubmanage.model.Users;

public interface UsersDao
{
	public void saveUser(Users user);
	public void deleteUserByUsername(String username);
	public void updateUser(Users user);
	public List<Users> getUsers();
	public boolean checkUserExistsWithUsername(String username);
	public boolean checkUserByUsernamePassword(Users user);
	public Users loadByUsername(String username);
}
