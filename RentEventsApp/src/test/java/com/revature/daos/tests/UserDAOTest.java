package com.revature.daos.tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.ManagerDAO;
import com.revature.daos.UserDAO;
import com.revature.daos.methods.UserDAOMethods;
import com.revature.model.User;

public class UserDAOTest {
	
	static ManagerDAO mdao = new UserDAOMethods();
	static UserDAO udao = new UserDAOMethods();
	static User u=new User(5,"User","User","user","user","456",10,300.30);
	
	
	
	@BeforeClass
	public static void setUp() {
		
		System.out.println("Test Start");
	}
	@Test
	public void testCreateUser() {
		
		assertEquals(1,mdao.createUser(u));
		
	}
	
	@Test 
	public void testGetUserById() {
		
		User victoria=new User(4,"Victoria","Quicano","dmavicky@yahoo.es","dmavicky","456",10,300.0);
		assertEquals(victoria,udao.getUserById(4));
	}
	
	@Test 
	public void testGetUsers() {
		List<User> users=mdao.getUsers();
		
		int size=users.size();
		for(int i=0;i<size;i++) {
			System.out.println(users.get(i));
		}
		System.out.println();
	}
	@Test
	public void testGetByUserAndPassword() {
		
		User victoria = new User(4,"Victoria","Quicano","dmavicky@yahoo.es","dmavicky","456",10,300.0);
		assertEquals(victoria,udao.getUserByUsernameAndPassword("dmavicky","456"));
	}
	@Test
	public void testGetByEmailAndPassword() {
		
		User victoria = new User(4,"Victoria","Quicano","dmavicky@yahoo.es","dmavicky","456",10,300.0);
		assertEquals(victoria,udao.getUserByUsernameAndPassword("dmavicky@yahoo.es","456"));
	}
	
	@Test
	public void testUpdateInfo() {
		User felipe = new User(1,"Felipe","Quicano","afcabezasq@unal.edu.co","felipe","456",10,500.3);
		assertEquals(1,udao.updateInfo(felipe));
	}
	
	@AfterClass
	public static void tearDown() {
		
		System.out.println("Test End");
		mdao.deleteUser(5);
	}
	
	

}
