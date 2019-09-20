package com.revature.daos.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.LocationDAO;
import com.revature.daos.methods.LocationDAOMethods;
import com.revature.model.Location;

public class LocationsDAOTest {
	
	static LocationDAO ldao = new LocationDAOMethods();
	
	
	@BeforeClass
	public static void setUp() {
		
		System.out.println("Test Start");
	}
	
	@Test 
	public void testGetUsers() {
		
		List<Location> locations = ldao.getLocations();
		
		
		for(Location l: locations) {
			System.out.println(l);
		}
		System.out.println();
	}
	
	@Test
	public void testGetLocaionById(){
		Location l =new Location(1,"IrishPub","2634 g ct. Reston",0 ,200,1,600.30);
		assertEquals(l,ldao.getLocationById(l.getId()));
			
	}
	
	@Test
	public void createLocation() {
		Location l = new Location(10," "," ",0 ,200,1,600.30);
		assertEquals(1,ldao.createLocation(l));
		
	}
	
	@AfterClass
	public static void tearDown() {
		
		System.out.println("Test End");
		ldao.deleteLocation(" ");
		
	}

}
