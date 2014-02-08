package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadingActivity extends Activity{
	
	public static final int LOADING_FIRST_USE = 1;
	public static final int LOADING_LOGON = 2;
	public static final int LOADING_LOGOFF = 3;
	
	private int /*use = 0,*/ login_stat = 0;
	
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
//				if(use == 0){
//					// 처음 사용자 - 설명? 무언가 들어갈 것? 
//				}else{
					if(login_stat == 0){ // log off
						Intent intent = new Intent(LoadingActivity.this, JoinActivity.class);
						intent.putExtra("state", LOADING_LOGOFF);
						startActivity(intent);
					}else{ // log in 
						Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
						intent.putExtra("state", LOADING_LOGON);
						startActivity(intent);
					}
				//}
				
			}
		}, 2000);
	
	
	}
}
