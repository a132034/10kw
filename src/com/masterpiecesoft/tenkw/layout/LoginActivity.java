package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.User;
import com.masterpiecesoft.tenkw.DbManager.UserDbAccess;
import com.masterpiecesoft.tenkw.etc.LoginStateSharedPreference;
import com.masterpiecesoft.tenkw.store.UserInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity{

	Toast tst = null; 
	
	private String user_phone = null;
	private String user_pw = null;
	private User user = new User();
	private UserDbAccess userdb = new UserDbAccess();
	
	LoginStateSharedPreference pref = new LoginStateSharedPreference(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);//연결

		//연결2
		final EditText phone = (EditText)findViewById(R.id.login_phone_editText);
		final EditText passwd = (EditText)findViewById(R.id.login_pw_editText);
		final ImageButton login = (ImageButton)findViewById(R.id.login_btn);
		final ImageButton join = (ImageButton)findViewById(R.id.join_btn);
		
		join.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, JoinActivity.class));
				finish();
			}
		});//Join page
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				user_phone = phone.getText().toString();
				user_pw = passwd.getText().toString();

				if(!(user_phone.isEmpty()) && !(user_pw.isEmpty())){
					user.setUserPhone(user_phone);
					user.setUserPasswd(user_pw);
					
					if(userdb.LoginUser(user) == 1){ //login 성공 
						UserInfo userInfo = new UserInfo();
						userInfo.setName(user.getUserName());
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						intent.putExtra("login_state",2);
						intent.putExtra("login_phone", true);
						pref.put(pref.PREF_INTRO_USER_PHONE, true);
						startActivity(intent);	
						finish();
					}else{ //login 실패
					    AlertDialog.Builder ab = null;
					    ab = new AlertDialog.Builder(LoginActivity.this);
					    ab.setMessage(Html.fromHtml("<b><font color=#ff00ff>Log in Fail</font></b><br>Retry,please"));
					    ab.setPositiveButton(android.R.string.no, null);
					    ab.setTitle("로그인 실패");
					    ab.show();
					}
					
					
				}else{
					tst = Toast.makeText(getApplicationContext(),"정보를 입력해주세요", Toast.LENGTH_SHORT);
					tst.setGravity(Gravity.CENTER, 22, 44);
					tst.show();
				}
				
				
				
			}
		});//Login
	}
	
}
