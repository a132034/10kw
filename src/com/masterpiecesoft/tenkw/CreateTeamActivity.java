package com.masterpiecesoft.tenkw;

import java.util.List;

import com.masterpiecesoft.tenkw.layout.GroupActivity;
import com.masterpiecesoft.tenkw.store.RunInfo;
import com.masterpiecesoft.tenkw.store.Team;
import com.masterpiecesoft.tenkw.store.UserInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CreateTeamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_team);

		ImageButton doneButton = (ImageButton)findViewById(R.id.imageButton_done);
		
		doneButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TextView title = (TextView)findViewById(R.id.editText1);
				String titleString = (String)title.getText().toString();
				
				if(titleString != null ){
					UserInfo userInfo = new UserInfo();
					//List<Team> team = userInfo.getTeamList();
					UserInfo.getTeamList().add(new Team(titleString));
					RunInfo.setTabIndex(UserInfo.getTeamList().size()-1);
					startActivity(new Intent(CreateTeamActivity.this,GroupActivity.class));
					finish();
				}
			}
			
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_team, menu);
		return true;
	}

}
