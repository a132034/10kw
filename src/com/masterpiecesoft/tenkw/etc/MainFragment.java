package com.masterpiecesoft.tenkw.etc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.Group;
import com.masterpiecesoft.tenkw.layout.GroupActivity;
import com.masterpiecesoft.tenkw.pedometer.PedometerSettings;
import com.masterpiecesoft.tenkw.pedometer.StepService;
import com.masterpiecesoft.tenkw.pedometer.Utils;
import com.masterpiecesoft.tenkw.store.RunInfo;
import com.masterpiecesoft.tenkw.store.Team;
import com.masterpiecesoft.tenkw.store.UserInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
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
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {
	
	private static ImageButton strBtn;
	private static boolean mIsRunning;
	private SharedPreferences mSettings;
	private PedometerSettings mPedometerSettings;
	private MainPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private static int groupIndex;
	static RunInfo runInfo = new RunInfo();
	private StepService mService;
	private List<Group> groupList = new ArrayList<Group>();
	private TextView mStepValueView;

	private TextView groupTitle;
	private TextView groupNum;
	private TextView percentText;
	private TextView walkNumber;

	private int mStepValue;
	private float mDesiredPaceOrSpeed;
	private int mMaintain;
	private boolean mQuitting = false;
	private Utils mUtils;
	private UserInfo userInfo = new UserInfo();
	String TAG = "TAG";

	public MainFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.i(TAG, "[ACTIVITY] onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "[ACTIVITY] onResume");
		super.onResume();

		getActivity().setTitle((CharSequence)"");

		mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mPedometerSettings = new PedometerSettings(mSettings);

		mUtils.setSpeak(mSettings.getBoolean("speak", false));

		// Read from preferences if the service was running on the last onPause
		mIsRunning = mPedometerSettings.isServiceRunning();
//////////////////////////////////////////////////////////////////
		if (!mIsRunning && mPedometerSettings.isNewStart()) {
			startStepService();
			bindStepService();
		} else if (mIsRunning) {
			bindStepService();
		}

		mPedometerSettings.clearServiceRunning();

		mMaintain = mPedometerSettings.getMaintainOption();
		if (mMaintain == PedometerSettings.M_PACE) {
            mDesiredPaceOrSpeed = (float)mPedometerSettings.getDesiredPace();
        }
        else 
        if (mMaintain == PedometerSettings.M_SPEED) {
            mDesiredPaceOrSpeed = mPedometerSettings.getDesiredSpeed();
        }
        if (mMaintain != PedometerSettings.M_NONE) {
        }
	}
/////////////////////////////////////////////////////////////////////////
    private void resetValues(boolean updateDisplay) {
//        if (mService != null && mIsRunning) {
            mService.resetValues();                    
//        }
//        else {
//            mStepValueView.setText("0");
//            SharedPreferences state = getActivity().getSharedPreferences("state", 0);
//            SharedPreferences.Editor stateEditor = state.edit();
//            if (updateDisplay) {
//                stateEditor.putInt("steps", 0);
//                stateEditor.putInt("pace", 0);
//                stateEditor.putFloat("distance", 0);
//                stateEditor.putFloat("speed", 0);
//                stateEditor.putFloat("calories", 0);
//                stateEditor.commit();
//            }
//        }
    }
    
    ////////////////////////////////////
	@Override
	public void onPause() {
		Log.i(TAG, "[ACTIVITY] onPause");
		if (mIsRunning) {
			unbindStepService();
		}
		if (mQuitting) {
			mPedometerSettings.saveServiceRunningWithNullTimestamp(mIsRunning);
		} else {
			mPedometerSettings.saveServiceRunningWithTimestamp(mIsRunning);
		}
		super.onPause();
		savePaceSetting();
	}

	private void savePaceSetting() {
		mPedometerSettings.savePaceOrSpeedSetting(mMaintain,
				mDesiredPaceOrSpeed);
	}

	// //////////////////////////////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_main, container,
				false);

		mUtils = Utils.getInstance();
//		mStepValue = 0 ; 
		
		mStepValueView = (TextView)rootView.findViewById(R.id.main_steps_temptxt);
		mSectionsPagerAdapter = new MainPagerAdapter(getFragmentManager());

		DecimalFormat df = new DecimalFormat("#.##");
		
		groupTitle = (TextView) rootView.findViewById(R.id.main_title_txt);
		groupNum = (TextView) rootView.findViewById(R.id.main_num_txt);
		percentText = (TextView) rootView.findViewById(R.id.main_percent_txt);
		walkNumber = (TextView)rootView.findViewById(R.id.main_km_txt);
		
		//초기 세팅 
		Team tempTeam = userInfo.getTeamList().get(0);
		groupTitle.setText(tempTeam.getTeamTitle());
		groupNum.setText(""+tempTeam.getUserNum()+"명");
		percentText.setText(""+df.format((float)tempTeam.getTeamtotalStep()*0.1/tempTeam.getUserNum()*5)+"%");
		walkNumber.setText(""+df.format(tempTeam.getTeamtotalStep()*0.1)+" / "+df.format(tempTeam.getUserNum()*5)+" KM");
		//
		mViewPager = (ViewPager) rootView.findViewById(R.id.main_image_layout);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onPageSelected(int index) {
//				// TODO Auto-generated method stub
				groupIndex = index;
				DecimalFormat df = new DecimalFormat("#.##");
				Team tempTeam = userInfo.getTeamList().get(index);
				groupTitle.setText(tempTeam.getTeamTitle());
				groupNum.setText(""+tempTeam.getUserNum()+"명");
				percentText.setText(""+df.format((float)tempTeam.getTeamtotalStep()/tempTeam.getUserNum()*5)+"%");
				walkNumber.setText(""+df.format(tempTeam.getTeamtotalStep())+" / "+df.format(tempTeam.getUserNum()*5)+" KM");
				changeImage();
			}
		});

		strBtn = (ImageButton) rootView.findViewById(R.id.main_start_btn);
		changeImage();
		mSettings = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		mPedometerSettings = new PedometerSettings(mSettings);
//
		strBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RunInfo runInfo = new RunInfo();
//				mIsRunning = mPedometerSettings.isServiceRunning();
				if (mIsRunning && runInfo.getGroupIndex() == groupIndex) {
					// start 중일 때 stop 일때 누르는 것 그냥 종료 시키면 된다.
					strBtn.setBackgroundResource(R.drawable.btn_start);
					Team tempTeam = userInfo.getTeamList().get(runInfo.getGroupIndex());
					tempTeam.getUserList().get(0).addSubStep(mStepValue);
					////////////////////////////
					DecimalFormat df = new DecimalFormat("#.##");
					percentText.setText(""+df.format((float)tempTeam.getTeamtotalStep()/tempTeam.getUserNum()*5)+"%");
					walkNumber.setText(""+df.format(tempTeam.getTeamtotalStep())+" / "+df.format(tempTeam.getUserNum()*5)+" KM");
					////////////////////////////////
					
					unbindStepService();
					stopStepService();
					resetValues(true);
					// 종료 종료 종료
				} else {
					strBtn.setBackgroundResource(R.drawable.btn_stop);
					if (mIsRunning) {
						//방이바뀌면서 시작
						Team tempTeam = userInfo.getTeamList().get(runInfo.getGroupIndex());
						tempTeam.getUserList().get(0).addSubStep(mStepValue);
						resetValues(true);
						 
					} else if (mPedometerSettings.isNewStart()) {
						// 새로 시작
						Team tempTeam = userInfo.getTeamList().get(groupIndex);
						// mStepValue = tempTeam.getUserList().get(0).getSubStep();
						startStepService();
						bindStepService();
						
					} else {
						// 일시 정지 이다가 시작 할 때
						bindStepService();
					}
					runInfo.setGroupIndex(groupIndex);
					// 시작 시작 시작 groupID 바꿔주기
				}
			}
		});
		return rootView;
	}

	// /////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mService = ((StepService.StepBinder) service).getService();
			mService.registerCallback(mCallback);
			mService.reloadSettings();
		}

		public void onServiceDisconnected(ComponentName className) {
			mService = null;
		}
	};

	private void startStepService() {
		if (!mIsRunning) {
			mIsRunning = true;
			Intent intent = new Intent(getActivity(), StepService.class);
			getActivity().startService(intent);
		}
	}

	private void bindStepService() {
		Intent intent = new Intent(getActivity(), StepService.class);
		getActivity().bindService(
				intent,
				mConnection,
				getActivity().BIND_AUTO_CREATE
						+ getActivity().BIND_DEBUG_UNBIND);
	}

	private void unbindStepService() {
		getActivity().unbindService(mConnection);
	}

	private void stopStepService() {
		if (mService != null) {
			Intent intent = new Intent(getActivity(), StepService.class);
			getActivity().stopService(intent);
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
			mHandler.sendMessage(mHandler.obtainMessage(DISTANCE_MSG,
					(int) (value * 1000), 0));
		}

		public void speedChanged(float value) {
			mHandler.sendMessage(mHandler.obtainMessage(SPEED_MSG,
					(int) (value * 1000), 0));
		}

		public void caloriesChanged(float value) {
			mHandler.sendMessage(mHandler.obtainMessage(CALORIES_MSG,
					(int) (value), 0));
		}
	};

	private static final int STEPS_MSG = 1;
	private static final int PACE_MSG = 2;
	private static final int DISTANCE_MSG = 3;
	private static final int SPEED_MSG = 4;
	private static final int CALORIES_MSG = 5;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case STEPS_MSG:
				mStepValue = (int) msg.arg1;
				mStepValueView.setText("" + mStepValue);
				break;
			case PACE_MSG:
				break;
			case DISTANCE_MSG:
				break;
			case SPEED_MSG:
				break;
			case CALORIES_MSG:
				break;
			default:
				super.handleMessage(msg);
			}
		}

	};


	public static void changeImage() {
		if (mIsRunning && runInfo.getGroupIndex() == groupIndex) {
			Log.d("arg0","changeImage");
			strBtn.setBackgroundResource(R.drawable.btn_stop);
		} else {
			Log.d("arg0","changeImage");
			strBtn.setBackgroundResource(R.drawable.btn_start);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	public class MainPagerAdapter extends FragmentPagerAdapter {
		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new MainDummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(MainDummySectionFragment.ARG_SECTION_NUMBER,
					position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return userInfo.getTeamList().size();
		}
	}

	public static class MainDummySectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		public MainDummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			ImageButton mainImage = (ImageButton) rootView
					.findViewById(R.id.main_image_btn);
			mainImage.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),
							GroupActivity.class);
					runInfo.setTabIndex(groupIndex);
					intent.putExtra("mIsRunning", mIsRunning);
					Log.d("group", "intent     " + groupIndex);
					startActivity(intent);
				}
			});

			switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
			case 1:
				mainImage.setImageResource(R.drawable.btn_4);
				return rootView;

			case 2:
				mainImage.setImageResource(R.drawable.btn_3);
				return rootView;
				
			default :
				mainImage.setImageResource(R.drawable.btn_2);
				return rootView;
			}
		}
	}
}
