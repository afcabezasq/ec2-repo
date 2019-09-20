package com.revature.daos.methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.daos.ManagerDAO;
import com.revature.daos.UserDAO;
import com.revature.model.User;
import com.revature.util.ConnectionDB;

public class UserDAOMethods implements UserDAO,ManagerDAO {
	
	
	

	
	@Override
	public int updateInfo(User newInfo) {
		
		String sql  =  	"update users set  userFirstName=?,"
										+ "userLastName=?,"
										+ "userEmail=?,"
										+ "userName=?,"
										+ "userPassword=?,"
										+ "numberRequestsDone=?,"
										+ "totalSpends=?"
										+ "where userId=?;"
										+"select * from users where userId=?;";
				
		try (Connection c = ConnectionDB.getConnection(); 
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, newInfo.getFirstName());
			ps.setString(2, newInfo.getLastName());
			ps.setString(3, newInfo.getEmail());
			ps.setString(4, newInfo.getUserName());
			ps.setString(5, newInfo.getPassword());
			ps.setInt(6, newInfo.getNumberRequestsDone());
			ps.setDouble(7, newInfo.getTotalSpends());
			ps.setDouble(8, newInfo.getId());
			ps.setDouble(9, newInfo.getId());
			
			ps.execute();
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return  0;
		}				
				
				
				
				
		
		
		return 1;
	}

	@Override
	public int createUser(User u) {
		
		String sql = "select * from addUser(?,?,?,?,?,?,?)";
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getUserName());
			ps.setString(5, u.getPassword());
			ps.setInt(6, u.getNumberRequestsDone());
			ps.setDouble(7, u.getTotalSpends());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			u.setId(rs.getInt("userId"));
			rs.close();
			
		} catch (SQLException e) {
					
					e.printStackTrace();
					return 0;
				}
		
		return 1;
	}

	@Override
	public int deleteUser(int id) {
		
		int rowsDeleted = 0;
		String sql = "delete from users where userId = ?";
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}
		

	@Override
	public User getUserById(int id) {
		
		String sql = "select * from Users where userId = ?";
		User u = null;
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1,id);			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
			
				
				id  =rs.getInt("userId");
				String firstName = rs.getString("userFirstName");
				String lastName = rs.getString("userLastName");
				String email = rs.getString("userEmail");
				String userName = rs.getString("userName");
				String password = rs.getString("userPassword");
				int numberRequestsDone = rs.getInt("numberRequestsDone");
				double totalSpends = rs.getDouble("totalSpends");
				
				
				u= new User(id, firstName, lastName, 
						 email,  userName, password,
						 numberRequestsDone, totalSpends);
			}
			rs.close();
			return u;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	

	@Override
	public User getManager() {
			
		return getUserById(0);
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select * from users order by userid";
		try (Connection c = ConnectionDB.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int userId= rs.getInt("userId");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String userEmail = rs.getString("userEmail");
				String userName = rs.getString("userName");
				String password = rs.getString("userPassword");
				int numberRequestsDone = rs.getInt("numberRequestsDone");
				double totalSpends = rs.getDouble("totalSpends");
				
				User u = new User(userId, userFirstName, userLastName, 
						userEmail,  userName, password,
						 numberRequestsDone, totalSpends);
				users.add(u);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return users;
	}

	
	@Override
	public User getUserByUsernameAndPassword(String usernameOrEmail, String password) {
		 
		List<User> users=getUsers();
		
		for(User u:users) {
			if(u.getUserName() != null) {
				if(u.getUserName().contentEquals(usernameOrEmail) || u.getEmail().equals(usernameOrEmail)) {
					
					if(u.getPassword().equals(password)) {
						return u;
					}
					
				}
			}		
			
		}
		return null;
		
		

	}

	@Override
	public String getUserRole(User u) {
		
		if(u.getId()>0) {
			return "general";
		}else {
			return "admin";
		}
	}

	

}
