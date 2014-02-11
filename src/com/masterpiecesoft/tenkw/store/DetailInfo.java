package com.masterpiecesoft.tenkw.store;

public final class DetailInfo {
	
	private static int Height;
	private static int Weight;
	private static int totalStep;
	private static int tempNum;
	
	public static int getHeight() {
		return Height;
	}
	public static void setHeight(int height) {
		Height = height;
	}
	public static int getWeight() {
		return Weight;
	}
	public static void setWeight(int weight) {
		Weight = weight;
	}
	public static int getTotalStep() {
		return totalStep;
	}
	public static void setTotalStep(int totalStep) {
		DetailInfo.totalStep = totalStep;
	}
	public static int getTempNum() {
		return tempNum;
	}
	public static void setTempNum(int tempNum) {
		DetailInfo.tempNum = tempNum;
	}
}
