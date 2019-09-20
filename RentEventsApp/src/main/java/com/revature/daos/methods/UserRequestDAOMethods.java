package com.revature.daos.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.daos.UserRequestDAO;
import com.revature.model.UserRequest;
import com.revature.util.ConnectionDB;

public class UserRequestDAOMethods implements UserRequestDAO {
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public UserRequest getUserRequestById(int id) {
		
		String sql = "select * from requests where requestId = ?";
		UserRequest sr = null;
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1,id);			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int requestId = rs.getInt("requestId");
				int uwmrequest = rs.getInt("uwmrequest");
				int requestType = rs.getInt("requestType");
				Date requestDate = rs.getDate("requestDate");
				int showType = rs.getInt("showType");
				int requestState = rs.getInt("requestState");
				int peopleExpected = rs.getInt("peopleExpected");
				int requestLocation = rs.getInt("requestLocation");
							
				sr= new UserRequest(
						requestId, 
						uwmrequest, 
						requestType, 
						requestDate, 
						showType, 
						requestState,
						peopleExpected, 
						requestLocation);
			}
			rs.close();
			log.info("Request getted succesfully");
			return sr;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	@Override
	public List<UserRequest> getUserRequests() {
		List<UserRequest> srqts = new ArrayList<>();
		String sql = "select * from requests";
		try (Connection c = ConnectionDB.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int requestId = rs.getInt("requestId");
				int uwmrequest = rs.getInt("uwmrequest");
				int requestType = rs.getInt("requestType");
				Date requestDate =  rs.getDate("requestDate");
				int showType = rs.getInt("showType");
				int requestState = rs.getInt("requestState");
				int peopleExpected = rs.getInt("peopleExpected");
				int requestLocation = rs.getInt("requestLocation"); 
			
				UserRequest sr = new UserRequest(
						requestId, 
						uwmrequest, 
						requestType , 
						requestDate, 
						showType, 
						requestState,
						peopleExpected, 
						requestLocation);
				
				srqts.add(sr);
				
				
			
			}
			log.info("getSurveyRequests getted succesfully");
			return srqts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<UserRequest> getUserResquestsByUserId(int userId) {
		List<UserRequest> srqts = new ArrayList<>();
		String sql = "select * from requests where uwmrequest ="+userId;
		try (Connection c = ConnectionDB.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int requestId = rs.getInt("requestId");
				int uwmrequest = rs.getInt("uwmrequest");
				int requestType = rs.getInt("requestType");
				Date requestDate =  rs.getDate("requestDate");
				int showType = rs.getInt("showType");
				int requestState = rs.getInt("requestState");
				int peopleExpected = rs.getInt("peopleExpected");
				int requestLocation = rs.getInt("requestLocation"); 
			
				UserRequest sr = new UserRequest(
						requestId, 
						uwmrequest, 
						requestType , 
						requestDate, 
						showType, 
						requestState,
						peopleExpected, 
						requestLocation);
				
				srqts.add(sr);
				
				
			
			}
			log.info("getSurveyResquestsByUserId getted succesfully");
			return srqts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int updateUserRequest(UserRequest sr) {
		

			
		String sql = "update requests set requestType=?," 
									+ "requestDate=?,"
									+ "showType=?,"
									+ "requestState=?,"
									+ "peopleExpected=?"
									+ "where requestId=?;"
									+ "select * from requests where requestId=?;";
		
				
		try (Connection c = ConnectionDB.getConnection(); 
				PreparedStatement ps = c.prepareStatement(sql)){
			
			
			ps.setInt(1, sr.getRequestType());
			ps.setDate(2, (java.sql.Date) sr.getDateRequested());
			ps.setInt(3, sr.getShowType());
			ps.setInt(4, sr.getAproval());
			ps.setInt(5, sr.getPeopleExpected());
			
			ps.setDouble(6, sr.getId());
			ps.setDouble(7, sr.getId());
			
			ps.execute();
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return  0;
		}
		
		return 1;
	}
	

	@Override
	public int createUserRequest(UserRequest sr) {
		
		String sql = "select *  from addRequest(?,?,?,?,?,?,?)";
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1,sr.getUserId());
			ps.setInt(2,sr.getRequestType());
			ps.setDate(3,sr.dateReturnSQL(sr.getDateRequested()));
			ps.setInt(4, sr.getShowType());
			ps.setInt(5, sr.getAproval());
			ps.setInt(6, sr.getPeopleExpected());
			ps.setInt(7, sr.getLocationRequested());
			
			ps.execute();
			
			
		} catch (SQLException e) {
			log.error("the request wasn't generated");
			e.printStackTrace();
		}
		log.info("request succesfully created");
		return 1;
	}

	@Override
	public int deleteUserRequestById(int id) {
		int rowsDeleted = 0;
		String sql = "delete from requests where requestId=?";
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rowsDeleted = ps.executeUpdate();
		} catch (SQLException e) {
			log.error("There was an error trying to delete survey");
			e.printStackTrace();
		}
		log.info(rowsDeleted);
		return rowsDeleted;
	}
	
	

}
