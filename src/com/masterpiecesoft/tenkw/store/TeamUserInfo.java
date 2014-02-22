package com.masterpiecesoft.tenkw.store;

public class TeamUserInfo {
	
	private String name;
	private int subStep;
	private int imageName;
	private BeforeData before ;
	
	public TeamUserInfo(String name, int step){
		this.name = name;
		this.subStep = step;
		before = new BeforeData();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSubStep() {
		return subStep;
	}

	public void addSubStep(float added) {
		this.subStep += added;
	}

	public void setSubStep(int subStep) {
		this.subStep = subStep;
	}

	public int getImageName() {
		return imageName;
	}

	public void setImageName(int imageName) {
		this.imageName = imageName;
	}
	
	public BeforeData getData(){
		return before;
	}
	
	public void saveData(){
		before.storeData(subStep);
	}

	
}
