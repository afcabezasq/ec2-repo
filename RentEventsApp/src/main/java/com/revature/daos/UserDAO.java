package com.revature.daos;

import com.revature.model.User;

public interface UserDAO {
	
	public User getUserById(int id);
	public User getUserByUsernameAndPassword(String usernameOrEmail, String password);
	public int updateInfo(User newInfo);
	public String getUserRole(User u);
	
	
	

}
