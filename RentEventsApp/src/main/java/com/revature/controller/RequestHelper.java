package com.revature.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.delegates.AuthorizationDelegate;
import com.revature.delegates.UserRequestDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	
	private static Logger log = Logger.getRootLogger();
	
	private ViewDelegate viewDelegate = new ViewDelegate();
	private AuthorizationDelegate authDelegate = new AuthorizationDelegate();
	private UserDelegate ud = new UserDelegate();
	private UserRequestDelegate srd = new UserRequestDelegate();

	public void processGet(HttpServletRequest request, 
			HttpServletResponse response)throws IOException, ServletException {
		
		String path = request.getServletPath();
		if(path.startsWith("/api/")) {
			
			
			// we will authenticate the token here
//			if(!authDelegate.isAuthorized(request)) {
//				
//				log.info("not authorized");
//				response.sendError(401);
//				return;
//			}
			
			log.info("you got in");
//			
			String record = path.substring(5);
			switch(record) {
			
			case "users":
				ud.getUsers(request, response);
				break;
			case "requests":
				srd.getUsers(request, response);
				break;
			default:
				log.error("Error 404, send");
				response.sendError(404, "Request Record(s) Not Found");
			}
			
			
		} else {
			viewDelegate.returnView(request, response);
		}
	}

	public void processPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			log.info("authDelegate()");
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}

}
