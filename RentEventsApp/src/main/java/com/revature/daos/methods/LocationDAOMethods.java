package com.revature.daos.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.daos.LocationDAO;
import com.revature.model.Location;
import com.revature.util.ConnectionDB;

public class LocationDAOMethods implements LocationDAO{
	private static Logger log = Logger.getRootLogger();
	

	@Override
	public Location getLocationById(int id) {
		String sql = "select * from eventLocation where eventLocationId = ?";
		Location l = null;
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1,id);			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				id  =rs.getInt("eventLocationId");
				String locationName = rs.getString("locationName");
				String locationAddres = rs.getString("locationAddres");
				int showType = rs.getInt("showType");
				int maxCapacity  = rs.getInt("maxCapacity");
				int avaiability= rs.getInt("avaiability");
				double price = rs.getDouble("price");
				l= new Location(
						id, 
						locationName, 
						locationAddres, 
						showType, 
						maxCapacity, 
						avaiability, 
						price);
			}
			rs.close();
			return l;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Location> getLocations() {
		
		String sql = "select * from eventLocation";
		List<Location> locations = new ArrayList<>();
		try (Connection c = ConnectionDB.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				int eventLocationId = rs.getInt("eventLocationId");
				String locationName = rs.getString("locationName");
				String locationAddres = rs.getString("locationAddres");
				int showType = rs.getInt("showType");
				int maxCapacity = rs.getInt("maxCapacity");
				int avaiability = rs.getInt("avaiability");
				double price = rs.getDouble("price");
				
				Location l = new Location(
						eventLocationId, 
						locationName, 
						locationAddres,
						showType, 
						maxCapacity,  
						avaiability, 
						price);
				
				locations.add(l);
				
				
			}
		
			return locations;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public int createLocation(Location l) {
		String sql = "select * from addLocation(?,?,?,?,?,?)";
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, l.getName());
			ps.setString(2, l.getAddress());
			ps.setInt(3, l.getShowType());
			ps.setInt(4, l.getCapacity());
			ps.setInt(5, l.getAvaiability());
			ps.setDouble(6, l.getPrice());
			ResultSet rs = ps.executeQuery();
			rs.next();
			l.setId(rs.getInt("eventLocationId"));
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return 0;
		}
		log.info("Succesful Location Creation");
		return 1;
	}

	@Override
	public int deleteLocation(String name) {
		int rowsDeleted = 0;
		String sql = "delete from eventLocation where  locationName= ?";
		
		try(Connection c = ConnectionDB.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, name);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}
		
	

}







