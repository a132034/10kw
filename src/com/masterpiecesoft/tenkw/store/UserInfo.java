package com.masterpiecesoft.tenkw.store;

import java.util.ArrayList;
import java.util.List;

public final class UserInfo {
	
	private static String Name;
	private static int Height;
	private static int Weight;
	private static List<Team> teamList;
	

	public static String getName() {
		return Name;
	}

	public static void setName(String name) {
		Name = name;
	}
	
	public static List<Team> getTeamList() {
		return teamList;
	}
	
	public UserInfo (){
		if(teamList==null){
			teamList = new ArrayList<Team>();
			teamList.add(new Team("sGenClub"));
			teamList.add(new Team("10KW"));
			
			teamList.get(0).getUserList().add(new TeamUserInfo("¾È¼º¼ö",3));
			teamList.get(0).getUserList().add(new TeamUserInfo("±èÅÂ¿ø",2));
			teamList.get(0).getUserList().add(new TeamUserInfo("¹Úµµ¿µ",2));
			
			teamList.get(1).getUserList().add(new TeamUserInfo("±è¾Æ¹«°³",4));
			teamList.get(1).getUserList().add(new TeamUserInfo("È«±æµ¿",2));
			teamList.get(1).getUserList().add(new TeamUserInfo("ÃÖ¾¾",1));
			teamList.get(1).getUserList().add(new TeamUserInfo("¹Ú¾¾",0));
		}
	}
	
	public static int getHeight() {
		return Height;
	}
	public static void setHeight(int height) {
		Height = height;
	}
	public static int getWeight() {
		return Weight;
	}
	public static void setWeight(int weight) {
		Weight = weight;
	}
	
	
}
