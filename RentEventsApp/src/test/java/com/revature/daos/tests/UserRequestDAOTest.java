package com.revature.daos.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.UserRequestDAO;
import com.revature.daos.methods.UserRequestDAOMethods;
import com.revature.model.UserRequest;

public class UserRequestDAOTest {
	
	static UserRequestDAO  srdao = new UserRequestDAOMethods();
	
	@BeforeClass
	public static void setUp() {
		
		System.out.println("Test Start");
	}
	@Test 
	public void testGetSR() {
		
		List<UserRequest> srs = srdao.getUserRequests();
		for(UserRequest sr : srs ) {
			System.out.println(sr);
		}
		
	}
	@Test 
	public void testGetSRByUserId() {
		System.out.println();
		List<UserRequest> srs = srdao.getUserResquestsByUserId(3);
		for(UserRequest sr : srs ) {
			System.out.println(sr);
		}
		
	}
	
	@Test 
	public void testGetSRById() {
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse("2019-01-08");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		UserRequest sr = new UserRequest(3,3,1,date,1,0,600,1);
		
		assertEquals(sr,srdao.getUserRequestById(sr.getId()));
		
	}
	@Test
	public void testUpdateUserRequest() {
		
		UserRequest  sr = new UserRequest();
		sr = new UserRequest(5,4,1,sr.dateReturn("2019-01-26"),1,0,800,3); 
		
		
		assertEquals(1,srdao.updateUserRequest(sr));

		
	}
	@Test
	public void testCreateSRequest() {
		UserRequest  sr = new UserRequest();
		sr = new UserRequest(5,4,1,sr.dateReturn("2030-01-26"),1,0,800,3);
		assertEquals(1,srdao.createUserRequest(sr));
		
	}
	
	@AfterClass
	public static void tearDown() {
		srdao.deleteUserRequestById(7);
		System.out.println("Test End");
		
		
	}
}
