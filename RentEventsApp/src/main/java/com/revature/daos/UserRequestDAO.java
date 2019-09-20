package com.revature.daos;

import java.util.List;

import com.revature.model.UserRequest;

public interface UserRequestDAO {
	
	
	public UserRequest getUserRequestById(int id );
	public List<UserRequest> getUserRequests();
	public List<UserRequest> getUserResquestsByUserId(int userId);
	public int updateUserRequest(UserRequest sr);
	public int createUserRequest(UserRequest sr);
	public int deleteUserRequestById(int id);
	
}
