package com.masterpiecesoft.tenkw.store;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private List<TeamUserInfo> userList ;
	private String TeamTitle;
	private int userNum;
	private int TeamMissionStep;
	
	public Team(String TeamTitle){
		userList = new ArrayList<TeamUserInfo>();
		userList.add(new TeamUserInfo(UserInfo.getName(),0));
		this.TeamTitle = TeamTitle;
	}
	
	
	public List<TeamUserInfo> getUserList() {
		return userList;
	}
	
	public void setUserList(List<TeamUserInfo> userList) {
		this.userList = userList;
	}
	
	public String getTeamTitle() {
		return TeamTitle;
	}
	
	public void setTeamTitle(String teamTitle) {
		TeamTitle = teamTitle;
	}
	
	public int getTeamtotalStep() {
		int result = 0;
		TeamUserInfo tempUser;
		
		for(int i = 0 ; i < userList.size(); i++){
			tempUser = userList.get(i);
			result += tempUser.getSubStep();
		}
		return result;
	}

	public int getUserNum() {
		return userList.size();
	}
	
	public int getTeamMissionStep() {
		return getUserNum()*5;
	}

}
