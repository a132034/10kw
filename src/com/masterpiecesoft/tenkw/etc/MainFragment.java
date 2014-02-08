package com.masterpiecesoft.tenkw.etc;



import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.layout.GroupActivity;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainFragment extends Fragment{

	private ImageButton strBtn;
	private boolean mIsRunning ;
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	
public MainFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
		mSectionsPagerAdapter = new MainPagerAdapter(getFragmentManager()); 

		mViewPager = (ViewPager)rootView.findViewById(R.id.main_image_layout); 
		mViewPager.setAdapter(mSectionsPagerAdapter); 

		return rootView;
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
					startActivity(new Intent(getActivity(),GroupActivity.class));
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
