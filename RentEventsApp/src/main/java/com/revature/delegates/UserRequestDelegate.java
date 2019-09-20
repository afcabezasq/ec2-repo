package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.UserRequest;
import com.revature.services.UserRequestService;

public class UserRequestDelegate {
	
	UserRequestService  srs = new UserRequestService();
	
	public void getUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		List<UserRequest> urs = srs.getAllRequests();
		for(UserRequest ur: urs) {
			System.out.println(ur);
		}
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(urs));
			
		}
	}
}
