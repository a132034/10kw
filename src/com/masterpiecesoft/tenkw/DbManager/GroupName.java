package com.masterpiecesoft.tenkw.DbManager;

public class GroupName {
	
	private String UserPhone;
	private String UserName;
	private int Steps;
	private boolean Chief;
	
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getSteps() {
		return Steps;
	}
	public void setSteps(int steps) {
		Steps = steps;
	}
	public boolean isChief() {
		return Chief;
	}
	public void setChief(boolean chief) {
		Chief = chief;
	}
	@Override
	public String toString() {
		return "User [UserPhone=" + UserPhone + ", UserName=" + UserName 
				+ ", Steps=" + Steps + ", Chief=" + Chief + "]";
	}
}
