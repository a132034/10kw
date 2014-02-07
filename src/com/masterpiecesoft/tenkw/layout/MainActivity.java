package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.etc.*;
import com.masterpiecesoft.tenkw.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
 
	private ImageButton StartBtn; // start btn 
	private boolean mIsRunning;   // Running flag
	private ListView personal;    // 사이드바 예정 
	
	private DrawerLayout mDrawerLayout; //사이드바 예정
	private ActionBarDrawerToggle mDrawerToggle; //사이드바 예정
	
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager; 
	
	//for debug
	private static final String TAG = "10kw";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// mainActivity가 실행되면 startActivity를 통해 LoadingActivity가 시작됨
		startActivity(new Intent(this, LoadingActivity.class));
		
		// activity 와 layout.xml 을 연결시켜 준다. 
		setContentView(R.layout.activity_main);
		final ActionBar actionBar = getActionBar();// actionBar 를 가져옴 
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); //actionBar 3 가지 모드중 기본 ( title 만 보이는 것) 선택

		mSectionsPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()); // MainImage Swipe 를 돕는 Adapter
		
		mViewPager = (ViewPager)findViewById(R.id.Group_ImgBt);// layout 의 swipe 위치(pager 로 범위 지정)를 java 에서 다룰수 있게?연결
		mViewPager.setAdapter(mSectionsPagerAdapter); //pager 에 adapter 연결 
		
		StartBtn = (ImageButton)findViewById(R.id.Running_Start_Btn);
		StartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mIsRunning){ // if start -> stop % stop -> start
					mIsRunning = false;
				}else{
					mIsRunning = true;
					Toast.makeText(getApplicationContext(), "start~", Toast.LENGTH_SHORT).show(); //for debug
					//startService(new Intent(CopyOfMainActivity.this,StepService.class)); - 아마 만보기? 
				}
				
			}
		});
	
		mDrawerLayout = (DrawerLayout)findViewById(R.id.Main_Layout);
		// drawer layout 은  slide menu 를 보다 쉽게 이용하기 위해서 사용 근데 다시 애니메이션으로 바꿀까 고민중
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.btn_addgroup, R.string.drawer_open, R.string.drawer_close){

			@Override
			public void onDrawerClosed(View drawerView) {
				
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				
				super.onDrawerOpened(drawerView);
			}
			
		};
		// 토글은 액션바 왼쪽에 현재 < 나와있는 것, < 모양을 다른 이미지로 바꾸고 그 버튼을 통해 drawer layout 제어하려 했으나 실패 
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		actionBar.setDisplayHomeAsUpEnabled(true);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}// 메뉴 버튼 눌렀을 때 나오는게 메뉴 인줄 알았는데 액션바에서는 메뉴를 액션바 오른쪽에 나타내는 것 같음 따라서 xml 에 item 추가하여 + 버튼 추가 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) // xml 로 정의한 버튼을 제어하는 곳  case 를 사용하여 누른 item 에 대한 일을 설정 , startactivity 는 activity 이동 앞에 loading 과 마찬가지
		{
		case R.id.action_create:
			startActivity(new Intent(this, CreateGroupActivity.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
