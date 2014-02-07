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
	private ListView personal;    // ���̵�� ���� 
	
	private DrawerLayout mDrawerLayout; //���̵�� ����
	private ActionBarDrawerToggle mDrawerToggle; //���̵�� ����
	
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager; 
	
	//for debug
	private static final String TAG = "10kw";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// mainActivity�� ����Ǹ� startActivity�� ���� LoadingActivity�� ���۵�
		startActivity(new Intent(this, LoadingActivity.class));
		
		// activity �� layout.xml �� ������� �ش�. 
		setContentView(R.layout.activity_main);
		final ActionBar actionBar = getActionBar();// actionBar �� ������ 
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); //actionBar 3 ���� ����� �⺻ ( title �� ���̴� ��) ����

		mSectionsPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()); // MainImage Swipe �� ���� Adapter
		
		mViewPager = (ViewPager)findViewById(R.id.Group_ImgBt);// layout �� swipe ��ġ(pager �� ���� ����)�� java ���� �ٷ�� �ְ�?����
		mViewPager.setAdapter(mSectionsPagerAdapter); //pager �� adapter ���� 
		
		StartBtn = (ImageButton)findViewById(R.id.Running_Start_Btn);
		StartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mIsRunning){ // if start -> stop % stop -> start
					mIsRunning = false;
				}else{
					mIsRunning = true;
					Toast.makeText(getApplicationContext(), "start~", Toast.LENGTH_SHORT).show(); //for debug
					//startService(new Intent(CopyOfMainActivity.this,StepService.class)); - �Ƹ� ������? 
				}
				
			}
		});
	
		mDrawerLayout = (DrawerLayout)findViewById(R.id.Main_Layout);
		// drawer layout ��  slide menu �� ���� ���� �̿��ϱ� ���ؼ� ��� �ٵ� �ٽ� �ִϸ��̼����� �ٲܱ� �����
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
		// ����� �׼ǹ� ���ʿ� ���� < �����ִ� ��, < ����� �ٸ� �̹����� �ٲٰ� �� ��ư�� ���� drawer layout �����Ϸ� ������ ���� 
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		actionBar.setDisplayHomeAsUpEnabled(true);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}// �޴� ��ư ������ �� �����°� �޴� ���� �˾Ҵµ� �׼ǹٿ����� �޴��� �׼ǹ� �����ʿ� ��Ÿ���� �� ���� ���� xml �� item �߰��Ͽ� + ��ư �߰� 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) // xml �� ������ ��ư�� �����ϴ� ��  case �� ����Ͽ� ���� item �� ���� ���� ���� , startactivity �� activity �̵� �տ� loading �� ��������
		{
		case R.id.action_create:
			startActivity(new Intent(this, CreateGroupActivity.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
