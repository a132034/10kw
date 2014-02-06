package com.masterpiecesoft.tenkw.etc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MainPagerAdapter extends FragmentPagerAdapter{
	private int mMyGroupNum = 0;
	
	// pager adapter class ,swipe 하면서 아이템 나타나는 것 제어
	 //한 화면에 뒤에 다음께 보이게 하는 것은 아직 구현 못함.
	
	public int getmMyGroupNum() {
		return mMyGroupNum;
	}

	public void setmMyGroupNum(int mMyGroupNum) {
		this.mMyGroupNum = mMyGroupNum;
	}

	public MainPagerAdapter(FragmentManager fm){
		super(fm);
	}

	@Override
	public Fragment getItem(int pos) {
		Fragment fragment = new MainDummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(MainDummySectionFragment.ARG_SECTION_NUMBER, pos+1);
		fragment.setArguments(args);
		return fragment;
		// view pager 위치에 fragment layout 이 덧들어간다. 
		// 몇번 째 item 인지 position 을 전송해 주면서 fragment 실행???? 되게 하는 곳
	}

	@Override
	public int getCount() {
		// swipe 하는 그 DB의 갯수 요기에 <내가 포함된 그룹수>? 를 DB에서 읽어와서 return 해줘야 한다.
		setmMyGroupNum(2);
		
		
		return getmMyGroupNum();
	}
	
	
}


	