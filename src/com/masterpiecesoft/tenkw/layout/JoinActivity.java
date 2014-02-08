package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class JoinActivity extends Activity{
	
	Toast tst = null; 
	
	String user_phone = null;
	String user_pw = null;
	String user_pw2 = null;
	String user_email = null;
	String user_name = null;
	User user = new User();
	UserDbAccess userdb = new UserDbAccess();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		
		final EditText phone = (EditText) findViewById(R.id.join_phone_editText);
		final EditText email = (EditText) findViewById(R.id.join_email_editText);
		final EditText username = (EditText) findViewById(R.id.join_name_editText);
		final EditText userpasswd = (EditText) findViewById(R.id.join_pw_editText);
		final EditText userpasswd2 = (EditText) findViewById(R.id.join_pw2_editText);
		final ImageButton ok_btn = (ImageButton) findViewById(R.id.join_ok_button);
		
		ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				user_phone = phone.getText().toString();
				user_email = email.getText().toString();
				user_name = username.getText().toString();
				user_pw = userpasswd.getText().toString();
				user_pw2 = userpasswd2.getText().toString();
				
				if(!user_pw.equals(user_pw2)){ // password 불일치
					tst = Toast.makeText(getApplicationContext(),
							"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT);
					tst.setGravity(Gravity.CENTER, 22, 44);
					tst.show();
				}else{ 
					user.setUserPhone(user_phone);
					user.setEmail(user_email);
					user.setUserName(user_name);
					user.setUserPasswd(user_pw);
					
					if (!(user_phone.isEmpty()) && !(user_pw.isEmpty()) && !(user_email.isEmpty() && !(user_name.isEmpty()))){ // can't empty
						if(userdb.AddUser(user) == 1){
							tst = Toast.makeText(getApplicationContext(), "id-generation success", Toast.LENGTH_SHORT);
							tst.setGravity(Gravity.CENTER, 22, 44);
							tst.show();
							
							startActivity(new Intent(JoinActivity.this, MainActivity.class)); //가입완료시
						}else{
							tst = Toast.makeText(getApplicationContext(),
									"중복된 ID가 있습니다", Toast.LENGTH_SHORT);
							tst.setGravity(Gravity.CENTER, 22, 44);
							tst.show();
						}
					}else{
						tst = Toast.makeText(getApplicationContext(),
								"빈칸을 채워주세요!", Toast.LENGTH_SHORT);
						tst.setGravity(Gravity.CENTER, 22, 44);
						tst.show();
					}
				}
			}
		});
	}
	
}
