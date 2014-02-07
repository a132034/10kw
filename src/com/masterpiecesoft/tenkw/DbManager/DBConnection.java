package com.masterpiecesoft.tenkw.DbManager;

import java.net.HttpURLConnection;
import java.net.URL;

/** 
 * Db Connection Class 
 * */
public class DBConnection {

	public HttpURLConnection getConnection(URL url){
		HttpURLConnection conn = null;
		
		try{
			conn = (HttpURLConnection) url.openConnection();
		}catch(Exception e){
		}
		
		if(conn != null){
			conn.setConnectTimeout(10000);
			conn.setUseCaches(false);
			return conn;
		}else{
			return null;
		}
		
	}
}
