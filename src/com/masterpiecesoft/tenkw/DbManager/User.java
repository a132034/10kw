package com.masterpiecesoft.tenkw.DbManager;
/**
 * 
 * @author doyoung
 * User Class - User Info (My SQL Table)
 */
public class User {

	private String UserPhone;
	private String Email;
	private String UserName;
	private String UserPasswd;
	private int TotalStep;
	
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPasswd() {
		return UserPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		UserPasswd = userPasswd;
	}
	public int getTotalStep() {
		return TotalStep;
	}
	public void setTotalStep(int totalStep) {
		TotalStep = totalStep;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [UserPhone=" + UserPhone + ", Email=" + Email
				+ ", UserName=" + UserName + ", UserPasswd=" + UserPasswd + 
				", TotalStep=" + TotalStep + "]";
	}
	
	
}
