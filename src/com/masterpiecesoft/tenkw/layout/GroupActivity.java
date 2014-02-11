package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.etc.*;
import com.masterpiecesoft.tenkw.store.RunInfo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;

public class GroupActivity extends FragmentActivity implements ActionBar.TabListener{

	private TabPagerAdapter mTabPagerAdapter;
	private ViewPager mViewPager;
	private boolean mIsRunning;
	private RunInfo runInfo = new RunInfo();
	private ImageButton strBtn;
	private int groupID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_group);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);

		strBtn = (ImageButton)findViewById(R.id.main_start_btn);
		
		Intent intent = getIntent();
		groupID = intent.getIntExtra("GroupID", 1);
		Log.d("group","groupID     "+groupID);
		Log.d("group","getID     "+runInfo.getGroupID());
		if(/*mIsRunning &&*/ runInfo.getGroupID() == groupID ){
			strBtn.setBackgroundResource(R.drawable.btn_stop);
		}else{
			strBtn.setBackgroundResource(R.drawable.btn_start);
		}
		
		strBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(/*mIsRunning &&*/ runInfo.getGroupID() == groupID ){
					// ���� ���� �� �����Ŷ� stop ��Ű�� �ȴ�. 
					strBtn.setBackgroundResource(R.drawable.btn_start);
				}else{
					// �����ϱ� ���ؼ� ������ �̹Ƿ� ������ ���������� �� Ȯ���ϰ� ���� ��ų �� �ֵ��� �ؾ��Ѵ�. 
					strBtn.setBackgroundResource(R.drawable.btn_stop);
					runInfo.setGroupID(groupID);
				}
			}
		});
		
		mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
		
		mViewPager = (ViewPager) findViewById(R.id.group_pager);
		mViewPager.setAdapter(mTabPagerAdapter);
		
		//main �� ���������� pager �� �̿��ؼ� swipe , action bar �� tab ���� �����Ű�� ������ �Ʒ��� �Լ� �߰���
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				actionBar.setSelectedNavigationItem(pos);		// actionbar�� ���õ� �����۵� ����		
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
		});
		
		// add tab (&set icon) 
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.tapbar_1).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.tapbar_2).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.tapbar_3).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.tapbar_4).setTabListener(this));
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		int pos = tab.getPosition();
		switch(pos+1)
		{
		case 1:
			tab.setIcon(R.drawable.tapbar_1_o);
			break;
		case 2:
			tab.setIcon(R.drawable.tapbar_2_o);
			break;
		case 3:
			tab.setIcon(R.drawable.tapbar_3_o);
			break;
		case 4:
			tab.setIcon(R.drawable.tapbar_4_o);
			break;
		}
		mViewPager.setCurrentItem(pos);
	}// tab �� ���õǸ� pager �� position �� ���� , ���� switch ���� �ܼ��� ���� icon ��� ������ ���� �� 

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
		int pos = tab.getPosition();
		switch (pos+1){
		case 1:
			tab.setIcon(R.drawable.tapbar_1);
			break;
		case 2:
			tab.setIcon(R.drawable.tapbar_2);
			break;
		case 3:
			tab.setIcon(R.drawable.tapbar_3);
			break;
		case 4:
			tab.setIcon(R.drawable.tapbar_4);
			break;
		}
	}// ���� ������ �����Ǹ� ( �ٸ��� ���õǸ� ���õǾ� �ִ��� �����Ǵϱ� ) icon����� �ٽ� ���� 

}
