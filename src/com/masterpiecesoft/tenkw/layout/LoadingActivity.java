package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.etc.LoginStateSharedPreference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class LoadingActivity extends Activity{
	
	public static final int LOADING_FIRST_USE = 1;
	public static final int LOADING_LOGON = 2;
	public static final int LOADING_LOGOFF = 3;
	
	SQLiteDatabase db;  
	public int DB_MODE = Context.MODE_PRIVATE;    
	public String DB_NAME = "10kw_login.db"; // DB 생성시 이름 
    public String TABLE_NAME = "login"; // Table의 이름 
    LoginStateSharedPreference pref = new LoginStateSharedPreference(this);
	
    private boolean isLogin;
	private int use = 0, login_state = 0;
	
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		db = openOrCreateDatabase(DB_NAME, DB_MODE, null); // 있으면 열고, 없으면 생성                
	    Log.i("10kw", DB_NAME + " Database create"); // Log cat에 뿌릴 내용        
		
	    String sql = "create table " + TABLE_NAME + "(phone VARCHAR(15))";
	        
	    try{                    
            db.execSQL(sql);                    
            Log.i("10kw", TABLE_NAME + "Table create");             
            }catch(SQLException e){  }
	    
	    isLogin = pref.getValue(pref.PREF_INTRO_USER_PHONE, false);
	    if(isLogin)
	    	login_state = 1;
	    else
	    	login_state = 0;
	    
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			
			
			@Override
			public void run() {

				if(login_state == 0){ // log off
						Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
						intent.putExtra("login_state", LOADING_LOGOFF);
						startActivity(intent);
				}else{ // log in 
						Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
						intent.putExtra("login_state", LOADING_LOGON);
						//startActivity(intent);
				}
				
				
			}
		}, 2000);
		finish();
	}
}
