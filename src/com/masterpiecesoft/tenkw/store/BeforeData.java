package com.masterpiecesoft.tenkw.store;

public class BeforeData {
	
	public int D[];
	
	public BeforeData(){
		D = new int[4];
		
		D[0] = 1 ;
		D[1] = 1 ;
		D[2] = 1 ;
		D[3] = 1 ;
	}
	
	public void storeData(int Dday){
		
		D[3] = D[2] ;
		D[2] = D[1] ;
		D[1] = D[0];
		D[0] = Dday;
	}

}
