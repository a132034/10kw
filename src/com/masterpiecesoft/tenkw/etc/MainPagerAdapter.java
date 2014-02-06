package com.masterpiecesoft.tenkw.etc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MainPagerAdapter extends FragmentPagerAdapter{
	private int mMyGroupNum = 0;
	
	// pager adapter class ,swipe �ϸ鼭 ������ ��Ÿ���� �� ����
	 //�� ȭ�鿡 �ڿ� ������ ���̰� �ϴ� ���� ���� ���� ����.
	
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
		// view pager ��ġ�� fragment layout �� ������. 
		// ��� ° item ���� position �� ������ �ָ鼭 fragment ����???? �ǰ� �ϴ� ��
	}

	@Override
	public int getCount() {
		// swipe �ϴ� �� DB�� ���� ��⿡ <���� ���Ե� �׷��>? �� DB���� �о�ͼ� return ����� �Ѵ�.
		setmMyGroupNum(2);
		
		
		return getmMyGroupNum();
	}
	
	
}


	