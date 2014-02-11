package com.masterpiecesoft.tenkw.layout;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.User;
import com.masterpiecesoft.tenkw.etc.MainFragment;
import com.masterpiecesoft.tenkw.etc.SlideListAdapter;

import name.bagi.levente.pedometer.Pedometer;
import name.bagi.levente.pedometer.PedometerSettings;
import name.bagi.levente.pedometer.StepService;
import name.bagi.levente.pedometer.Utils;
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
import android.content.Context;
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
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private User personal;
	private StepService mService;
	
	//pedometer
	private Utils mUtils;
	private SharedPreferences mSettings;
	private PedometerSettings mPedometerSettings;
	private boolean mIsRunning;
	
	private int mStepValue;
    private int mPaceValue;
    private float mDistanceValue;
    private float mSpeedValue;
    private int mCaloriesValue;
    private float mDesiredPaceOrSpeed;
    private int mMaintain;
    private boolean mIsMetric;
    private float mMaintainInc;
    private boolean mQuitting = false;
	

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		//startActivity(new Intent(this, LoadingActivity.class));

		mTitle = mDrawerTitle = getTitle();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		// 임의데이터
		personal = new User();
		personal.setUserName("이경미");

		SlideListAdapter personlaInfo = new SlideListAdapter(
				getApplicationContext(), personal);
		mDrawerList.setAdapter(personlaInfo);

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
			displayView(0);
		}
		
		mUtils = Utils.getInstance();
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
		switch (item.getItemId()) {
//		case R.id.action_settings:
//			break;
		case R.id.action_create:
			startActivity(new Intent(this, CreateGroupActivity.class));
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        mPedometerSettings = new PedometerSettings(mSettings);
        
        mIsRunning = mPedometerSettings.isServiceRunning();
		
		if(mIsRunning && mPedometerSettings.isNewStart()){
			//startStepService();
			//bindStepService();
		}else if(mIsRunning){
			//bindStepService();
		}
		
		mPedometerSettings.clearServiceRunning();
		
		// textView연결 및 
		
		displayDesiredPaceOrSpeed();
	}
	private void displayDesiredPaceOrSpeed() {
        if (mMaintain == PedometerSettings.M_PACE) {
       //     mDesiredPaceView.setText("" + (int)mDesiredPaceOrSpeed);
        }
        else {
      //      mDesiredPaceView.setText("" + mDesiredPaceOrSpeed);
        }
    }
    
	@Override
	protected void onPause(){
		if(mIsRunning){
			//unbindStepService();
		}
		if (mQuitting) {
            mPedometerSettings.saveServiceRunningWithNullTimestamp(mIsRunning);
        }
        else {
            mPedometerSettings.saveServiceRunningWithTimestamp(mIsRunning);
        }
		
		super.onPause();
		savePaceSetting();
	}
	
	private void savePaceSetting() {
        mPedometerSettings.savePaceOrSpeedSetting(mMaintain, mDesiredPaceOrSpeed);
    }
	

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new MainFragment();
			break;
		case 1:
			// fragment = new FindPeopleFragment();
			break;
		case 2:
			// fragment = new PhotosFragment();
			break;
		case 3:
			// fragment = new CommunityFragment();
			break;
		case 4:
			// fragment = new PagesFragment();
			break;
		case 5:
			// fragment = new WhatsHotFragment();
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

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

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
	
	 private ServiceConnection mConnection = new ServiceConnection() {
	        public void onServiceConnected(ComponentName className, IBinder service) {
	            mService = ((StepService.StepBinder)service).getService();

	            mService.registerCallback(mCallback);
	            mService.reloadSettings();
	            
	        }

	        public void onServiceDisconnected(ComponentName className) {
	            mService = null;
	        }
	    };
	    

	
    private void startStepService() {
        if (! mIsRunning) {
            mIsRunning = true;
            startService(new Intent(MainActivity.this,
                    StepService.class));
        }
    }
    
    private void bindStepService() {
        bindService(new Intent(MainActivity.this, 
                StepService.class), mConnection, Context.BIND_AUTO_CREATE + Context.BIND_DEBUG_UNBIND);
    }

    private void unbindStepService() {
        unbindService(mConnection);
    }
    
    private void stopStepService() {
        if (mService != null) {
            stopService(new Intent(MainActivity.this,
                  StepService.class));
        }
        mIsRunning = false;
    }
    

    private StepService.ICallback mCallback = new StepService.ICallback() {
        public void stepsChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(STEPS_MSG, value, 0));
        }
        public void paceChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(PACE_MSG, value, 0));
        }
        public void distanceChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(DISTANCE_MSG, (int)(value*1000), 0));
        }
        public void speedChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(SPEED_MSG, (int)(value*1000), 0));
        }
        public void caloriesChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(CALORIES_MSG, (int)(value), 0));
        }
    };
    

    private static final int STEPS_MSG = 1;
    private static final int PACE_MSG = 2;
    private static final int DISTANCE_MSG = 3;
    private static final int SPEED_MSG = 4;
    private static final int CALORIES_MSG = 5;
    
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEPS_MSG:
                    mStepValue = (int)msg.arg1;
                //    mStepValueView.setText("" + mStepValue);
                    break;
                case PACE_MSG:
                    mPaceValue = msg.arg1;
                    if (mPaceValue <= 0) { 
               //         mPaceValueView.setText("0");
                    }
                    else {
               //         mPaceValueView.setText("" + (int)mPaceValue);
                    }
                    break;
                case DISTANCE_MSG:
                    mDistanceValue = ((int)msg.arg1)/1000f;
                    if (mDistanceValue <= 0) { 
                //        mDistanceValueView.setText("0");
                    }
                    else {
             //           mDistanceValueView.setText(
               //                 ("" + (mDistanceValue + 0.000001f)).substring(0, 5)
                 //       );
                    }
                    break;
                case SPEED_MSG:
                    mSpeedValue = ((int)msg.arg1)/1000f;
                    if (mSpeedValue <= 0) { 
                   //     mSpeedValueView.setText("0");
                    }
                    else {
                    //    mSpeedValueView.setText(
                        //        ("" + (mSpeedValue + 0.000001f)).substring(0, 4)
                      //  );
                    }
                    break;
                case CALORIES_MSG:
                    mCaloriesValue = msg.arg1;
                    if (mCaloriesValue <= 0) { 
               //         mCaloriesValueView.setText("0");
                    }
                    else {
                 //       mCaloriesValueView.setText("" + (int)mCaloriesValue);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
        
    };

}
