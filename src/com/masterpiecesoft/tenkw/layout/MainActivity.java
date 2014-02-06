package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.etc.*;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.R.layout;
import com.masterpiecesoft.tenkw.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
 
	private ImageButton StartBtn; // start btn 
	private boolean mIsRunning;   // Running flag
	private ListView personal;    // ?  
	
	private DrawerLayout mDrawerLayout; //?
	private ActionBarDrawerToggle mDrawerToggle; //?
	
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager; 
	
	//for debug
	private static final String TAG = "10kw";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
