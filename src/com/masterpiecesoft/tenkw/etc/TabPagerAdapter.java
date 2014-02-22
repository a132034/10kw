package com.masterpiecesoft.tenkw.etc;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabPagerAdapter extends FragmentPagerAdapter{

	private int index ;
	
	public TabPagerAdapter(FragmentManager fm , int index) {
		super(fm);
		this.index = index;
	}

	@Override
	public Fragment getItem(int pos) {
		Fragment fragment = new TabDummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(TabDummySectionFragment.ARG_SECTION_NUMBER, pos+1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int pos) {
		Locale l = Locale.getDefault();
		switch(pos)
		{
		case 0:
			return "Detail View".toUpperCase(l);
		case 1:
			return "Friends".toUpperCase(l);
		case 2:
			return "Graph".toUpperCase(l);
		case 3:
			return "Calendar".toUpperCase(l);
		}
		return null;
	}

}
