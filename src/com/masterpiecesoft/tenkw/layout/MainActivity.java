package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.CreateTeamActivity;
import com.masterpiecesoft.tenkw.MainDonationFragment;
import com.masterpiecesoft.tenkw.MainMyFragment;
import com.masterpiecesoft.tenkw.MainSettingFragment;
import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.User;
import com.masterpiecesoft.tenkw.etc.MainFragment;
import com.masterpiecesoft.tenkw.etc.SlideListAdapter;
import com.masterpiecesoft.tenkw.pedometer.PedometerSettings;
import com.masterpiecesoft.tenkw.pedometer.StepService;
import com.masterpiecesoft.tenkw.pedometer.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// /////////////////////////////////
	String TAG = "TAG";

	// /////////////////////////////////
	public static final int FIRST_USE = 0;
	public static final int LOGON = 2;
	public static final int LOGOFF = 3;
	private int login_state = 0;

	// ////////////////////////////////
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, LoadingActivity.class));
		setContentView(R.layout.activity_drawer);

		// //
		// //
		Intent in = getIntent();
		login_state = in.getIntExtra("login_state", 0);

		if (login_state == FIRST_USE)
			startActivity(new Intent(this, LoadingActivity.class));

		mTitle = mDrawerTitle = "";

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		// 임의데이터

		SlideListAdapter personlaInfo = new SlideListAdapter(
				getApplicationContext());
		mDrawerList.setAdapter(personlaInfo);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				displayView(pos);

			}

		});
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		// enabling action bar app icon and behaving it as toggle button
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				actionBar.setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				actionBar.setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_actionbar, menu);
		return true;
	}// 메뉴 버튼 눌렀을 때 나오는게 메뉴 인줄 알았는데 액션바에서는 메뉴를 액션바 오른쪽에 나타내는 것 같음 따라서 xml 에 item
		// 추가하여 + 버튼 추가

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar actions click
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			break;
		case R.id.action_create:
			startActivity(new Intent(MainActivity.this,
					CreateTeamActivity.class));
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			// fragment = new MainFragment();
			break;
		case 1:
			fragment = new MainFragment();
			break;
		case 2:
			fragment = new MainMyFragment();
			break;
		case 3:
			fragment = new MainDonationFragment();
			break;
		case 4:
			fragment = new MainSettingFragment();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			// mDrawerList.setItemChecked(position, true);
			// mDrawerList.setSelection(position);
			// setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// //////////////////////////////////////////////////////////////////

}
