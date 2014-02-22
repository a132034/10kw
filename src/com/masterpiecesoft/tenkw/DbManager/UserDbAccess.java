package com.masterpiecesoft.tenkw.DbManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

public class UserDbAccess {

		DBConnection db = new DBConnection();
		User user = new User();
		
		public User getUser(User _user){
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectDiskReads().detectDiskWrites().detectNetwork()
			.penaltyLog().build());
			
			StringBuilder sb = new StringBuilder();
			try{
				URL url = new URL("http://117.16.47.4/db.10kw/connectionDB.php?"
						+ "userphone=" + URLEncoder.encode(_user.getUserPhone(), "UTF-8")
						+ "&userpasswd=" + URLEncoder.encode(_user.getUserPasswd(), "UTF-8"));
			
			HttpURLConnection conn = db.getConnection(url);
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = br.readLine();
				sb.append(line);
				br.close();
				Log.d("UserDbAccess", "getUserInfo");
				
			}else{
				Log.e("UserDbAccess", "getUserInfo-Connection Error");
			}
			conn.disconnect();
					
			}catch(Exception e){
				Log.e("UserDbAccess", "getUserInfo - try Error" + e);
			}
			
		
			if(!(sb.toString().equalsIgnoreCase("false"))){
				String jsonString = sb.toString();
				try {
					JSONObject jo = new JSONObject(jsonString);
				
				user.setUserPhone(jo.getString("userphone"));
				user.setEmail(jo.getString("email"));
				user.setUserPasswd(jo.getString("userpasswd"));
				
				Log.d("UserDbAccess", "setUser");
				return user;
				} catch (JSONException e){
					Log.e("UserDbAccess", "jsonError" + e);
					return null;
			    }
			}else{
				Log.d("UserDbAccess", "notFoundUser");
				return null;
				
			}
		}
		
		public int AddUser(User _user) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StringBuilder sb = new StringBuilder();
			try {
				// url 주소 + 입력값
				Log.e("db connection", "db ㅅㅂ...");
				URL url = new URL("http://117.16.47.4/db.10kw/connectionDB.php?"
						+ "userphone=" + URLEncoder.encode(_user.getUserPhone(), "UTF-8")
						+ "&email=" + URLEncoder.encode(_user.getEmail(), "UTF-8")
						+ "&username=" + URLEncoder.encode(_user.getUserName(), "UTF-8")
						+ "&userpasswd=" + URLEncoder.encode(_user.getUserPasswd(), "UTF-8"));
				Log.e("db connection", "db what the fuck!!");
				HttpURLConnection conn = db.getConnection(url);

				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							conn.getInputStream()));
					String line = br.readLine();
					sb.append(line);
					br.close();
					conn.disconnect();
					if(sb.toString().equals("true")){
						Log.d("UserDbAccess", "addUserInfo");
						return 1;
					}
					else
						return 0;
				} else {
					conn.disconnect();
					Log.e("UserDbAccess", "getUserInfo-Connection Error in AddUser Func()");
					return 0;
				}
			} catch (Exception e) {
				Log.e("UserDbAccess", "getUserInfo - try Error in AddUser Func()" + e);
				return 0;
			}
		}
		public int LoginUser(User _user){
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
					detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
			StringBuilder sb = new StringBuilder();
			try {
				// url 주소 + 입력값
				Log.e("db connection", "db ㅅㅂ...");
				URL url = new URL("http://117.16.47.4/db.10kw/login.php?"
						+ "userphone=" + URLEncoder.encode(_user.getUserPhone(), "UTF-8")
						+ "&userpasswd=" + URLEncoder.encode(_user.getUserPasswd(), "UTF-8"));
				Log.e("10kw", "login php failed");
				HttpURLConnection conn = db.getConnection(url);

				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							conn.getInputStream()));
					String line = br.readLine();
					sb.append(line);
					br.close();
					conn.disconnect();
					if(sb.toString().equals("true")){
						Log.d("10kw", "addUserInfo");
						return 1;
					}
					else
						return 0;
				} else {
					conn.disconnect();
					Log.e("10kw", "login_user-Connection Error in AddUser Func()");
					return 0;
				}
			} catch (Exception e) {
				Log.e("10kw", "login_user - try Error in AddUser Func()" + e);
				return 0;
			}
			
		}
		public int CreateTeam(User ..._user){
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
					detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
			StringBuilder sb = new StringBuilder();
			try{
				for(User us : _user){
					
				}
			}catch(Exception e){
				Log.e("UserDbAccess", "getUserInfo - try Error in CreateTeam Func()" + e);
				return 0;
			}
			return 0;
		}
}
