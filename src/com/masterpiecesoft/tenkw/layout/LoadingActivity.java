package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadingActivity extends Activity{

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		Handler handler = new Handler(){
			public void handleMessage(Message msg){
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 2000); //2sec
	}
	
}
