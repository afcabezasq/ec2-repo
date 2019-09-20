package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.methods.UserDAOMethods;
import com.revature.model.User;

public class AuthorizationDelegate {
	
	private static Logger log = Logger.getRootLogger();
	private UserDAO udao = new UserDAOMethods();
	
	public boolean isAuthorized(HttpServletRequest request) {
		
		
		String authToken = request.getHeader("Authorization");
		
		if(authToken!=null) {
			
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length==2) {
				
				String idStr = tokenArr[0];
				
				
				
				String userRoleStr = tokenArr[1];
				
				log.info(idStr+userRoleStr);
				if(idStr.matches("^\\d+$")) {
					
					User u = udao.getUserById(Integer.parseInt(idStr));
					if(u!=null && udao.getUserRole(u).equals(userRoleStr)) {
						
						return true;
						
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	
	public  void authenticate (HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		log.info("authenticate()");
		String usernameOrEmail = request.getParameter("username");
		String password = request.getParameter("password");
		User u = udao.getUserByUsernameAndPassword(usernameOrEmail, password);
		
		if(u!= null) {
			
			String token = u.getId()+":"+udao.getUserRole(u);
			log.info(u.getId()+udao.getUserRole(u));
			response.setStatus(200);
			response.setHeader("Authorization", token);
			log.info("authorized");
		}else {
			
			response.sendError(401);
		}
		
	}

}
