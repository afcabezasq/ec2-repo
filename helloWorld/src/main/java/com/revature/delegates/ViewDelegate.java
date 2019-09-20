package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	
	public void ver(HttpServletRequest request, 
			HttpServletResponse response)throws IOException, ServletException {
		
		
		String path = request.getServletPath();
		switch(path) {
		case "/holi":
			request.getRequestDispatcher("/static/Views/Holi.html").forward(request, response);
			break;
		default:
			response.sendError(404, "static resource not found");
						
		}
		
	}


	
}
	


	
