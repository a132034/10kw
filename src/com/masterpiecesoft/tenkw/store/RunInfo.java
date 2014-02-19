package com.masterpiecesoft.tenkw.store;

public final class RunInfo {
	
	private boolean mIsRunning;
	private static int groupIndex ;
	private static int beforeStepNum;
	private static int tabIndex;
	
	public int getBeforeStepNum() {
		return beforeStepNum;
	}
	
	public void setBeforeStepNum(int beforeStepNum) {
		this.beforeStepNum = beforeStepNum;
	}
	public boolean getmIsRunning() {
		return mIsRunning;
	}
	public void setmIsRunning(boolean mIsRunning) {
		this.mIsRunning = mIsRunning;
	}

	public static int getGroupIndex() {
		return groupIndex;
	}

	public static void setGroupIndex(int groupIndex) {
		RunInfo.groupIndex = groupIndex;
	}

	public static int getTabIndex() {
		return tabIndex;
	}

	public static void setTabIndex(int tabIndex) {
		RunInfo.tabIndex = tabIndex;
	}
	
	

}
