package com.masterpiecesoft.tenkw.DbManager;

public class Group {
	
	private String TeamName;
	private String ChiefPhone;
	private String Nation;
	private String ImgSrc;
	private int Members;
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public String getChiefPhone() {
		return ChiefPhone;
	}
	public void setChiefPhone(String chiefPhone) {
		ChiefPhone = chiefPhone;
	}
	public String getNation() {
		return Nation;
	}
	public void setNation(String nation) {
		Nation = nation;
	}
	public String getImgSrc() {
		return ImgSrc;
	}
	public void setImgSrc(String imgSrc) {
		ImgSrc = imgSrc;
	}
	public int getMembers() {
		return Members;
	}
	public void setMembers(int members) {
		Members = members;
	}
	
	@Override
	public String toString() {
		return "User [TeanName=" + TeamName + ", ChiefPhone=" + ChiefPhone
				+ ", Nation=" + Nation + ", ImgSrc=" + ImgSrc + 
				", Members=" + Members + "]";
		
	}
}
