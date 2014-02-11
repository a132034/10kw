package com.masterpiecesoft.tenkw.etc;

import name.bagi.levente.pedometer.PedometerSettings;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.layout.GroupActivity;
import com.masterpiecesoft.tenkw.store.RunInfo;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainFragment extends Fragment{

	private static ImageButton strBtn;
	private static boolean mIsRunning ;
	private SharedPreferences mSettings;
	private PedometerSettings mPedometerSettings;
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private static int groupID;
	static RunInfo runInfo = new RunInfo();
	
public MainFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
		mSectionsPagerAdapter = new MainPagerAdapter(getFragmentManager()); 

		mViewPager = (ViewPager)rootView.findViewById(R.id.main_image_layout); 
		mViewPager.setAdapter(mSectionsPagerAdapter); 
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				groupID = arg0;
				changeImage();
				Log.d("arg0","selected      "+arg0);
				
			}
		});
		
		strBtn = (ImageButton)rootView.findViewById(R.id.main_start_btn);
		changeImage();
		mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPedometerSettings = new PedometerSettings(mSettings);
		mIsRunning = mPedometerSettings.isServiceRunning();
		
		strBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RunInfo runInfo = new RunInfo();
				mIsRunning = mPedometerSettings.isServiceRunning();
				if(/*mIsRunning &&*/ runInfo.getGroupID() == groupID  ){
					// start 중일 때 stop 일때 누르는 것  그냥 종료 시키면 된다. 
					strBtn.setBackgroundResource(R.drawable.btn_start);
					// 종료 종료 종료 
				}else{
					// 다른 채팅방에서 동작중인지 확인하고 내부적인 것을 변경 한다. DB 저장 ............하 ㅜ.ㅜ 요게 문제네 
					strBtn.setBackgroundResource(R.drawable.btn_stop);
					runInfo.setGroupID(groupID);
					// 시작 시작 시작  groupID 바꿔주기 
				}
			}
		});

		return rootView;
}
	public static void changeImage(){
		if(/*mIsRunning &&*/ runInfo.getGroupID() == groupID  ){
//			// start 중일 때 stop 일때 누르는 것 그냥 종료 시키면 된다. 
			strBtn.setBackgroundResource(R.drawable.btn_stop);
//			// 종료 종료 종료 
		}else{
			strBtn.setBackgroundResource(R.drawable.btn_start);
//			// 시작 시작 시작  groupID 바꿔주기 
		}
	}
	public class MainPagerAdapter extends FragmentPagerAdapter{ 
		public MainPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new MainDummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(MainDummySectionFragment.ARG_SECTION_NUMBER, position+1);
			fragment.setArguments(args);
			return fragment;
		} 

		@Override
		public int getCount() {
			return 2;
		}
	}
	public static class MainDummySectionFragment extends Fragment{

		public static final String ARG_SECTION_NUMBER = "section_number";

		public MainDummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			ImageButton mainImage = (ImageButton)rootView.findViewById(R.id.main_image_btn);
			mainImage.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
//					Fragment fragment = new TestFragment();
//					FragmentManager fragmentManager = getFragmentManager();
//					fragmentManager.beginTransaction()
//							.replace(R.id.frame_container, fragment).commit();
					Intent intent = new Intent(getActivity(), GroupActivity.class);
					intent.putExtra("GroupID", groupID);
					Log.d("group","intent     "+groupID);
					startActivity(intent);
				}
			});
			switch(getArguments().getInt(ARG_SECTION_NUMBER))
			{
			case 1:
				mainImage.setImageResource(R.drawable.tapbar_1);
				return rootView;

			case 2:
				mainImage.setImageResource(R.drawable.tapbar_1_o);
				return rootView;
			}
			return null;
		}
	}
}
