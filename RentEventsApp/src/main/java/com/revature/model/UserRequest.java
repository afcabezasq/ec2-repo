package com.revature.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class UserRequest implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	
	int id;
	int userId;
	int requestType; //canceled 0 
	Date dateRequested;
	int showType;
	int aproval; //requestState -1 rejected, 0 undefined, 1 aproved 
	int peopleExpected;
	int locationRequested;
	
	
	
	
	public java.sql.Date dateReturn(String d) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());
		Date date = null;
		try {
			date = format.parse(d);
			java.sql.Date sDate = new java.sql.Date(date.getTime());
			return sDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
				
	}
	
	public java.sql.Date dateReturnSQL(Date date) {
		
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
		
				
	}
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public UserRequest(int id, int userId, int requestType, Date dateRequested, int showType, int aproval,
			int peopleExpected, int locationRequested) {
		super();
		this.id = id;
		this.userId = userId;
		this.requestType = requestType;
		this.dateRequested = dateRequested;
		this.showType = showType;
		this.aproval = aproval;
		this.peopleExpected = peopleExpected;
		this.locationRequested = locationRequested;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public Date getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public int getAproval() {
		return aproval;
	}
	public void setAproval(int aproval) {
		this.aproval = aproval;
	}
	public int getPeopleExpected() {
		return peopleExpected;
	}
	public void setPeopleExpected(int peopleExpected) {
		this.peopleExpected = peopleExpected;
	}
	public int getLocationRequested() {
		return locationRequested;
	}
	public void setLocationRequested(int locationRequested) {
		this.locationRequested = locationRequested;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aproval;
		result = prime * result + ((dateRequested == null) ? 0 : dateRequested.hashCode());
		result = prime * result + id;
		result = prime * result + locationRequested;
		result = prime * result + peopleExpected;
		result = prime * result + requestType;
		result = prime * result + showType;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		if (aproval != other.aproval)
			return false;
		if (dateRequested == null) {
			if (other.dateRequested != null)
				return false;
		} else if (!dateRequested.equals(other.dateRequested))
			return false;
		if (id != other.id)
			return false;
		if (locationRequested != other.locationRequested)
			return false;
		if (peopleExpected != other.peopleExpected)
			return false;
		if (requestType != other.requestType)
			return false;
		if (showType != other.showType)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SurveyRequest [id=" + id + ", userId=" + userId + ", requestType=" + requestType + ", dateRequested="
				+ dateRequested + ", showType=" + showType + ", aproval=" + aproval + ", peopleExpected="
				+ peopleExpected + ", locationRequested=" + locationRequested + "]";
	}

	
	
	
	
	
}
