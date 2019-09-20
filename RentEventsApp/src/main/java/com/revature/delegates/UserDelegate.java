package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.ManagerService;

public class UserDelegate {
	
	private ManagerService ms = new ManagerService();
	
	public void getUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		List<User> uds = ms.getAllUsers();
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(uds));
			
		}
		
		
		
	}
}
