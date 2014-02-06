package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainDummySectionFragment extends Fragment{

	public static final String ARG_SECTION_NUMBER = "section_number"; //섹션 넘버는 무엇인지?? db에 쓸꺼??? 
	
	public MainDummySectionFragment() { //Constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rView = inflater.inflate(R.layout.maindummy_fragment, container, false);
		// inflater 가 정확히 알지 못하지만 실행중인  Activity에 또다른 xml을 연결하기 위해선 필요한 작업 ..........?
		
		ImageButton mGroupBtn = (ImageButton)rView.findViewById(R.id.GroupBtn);
		mGroupBtn.setOnClickListener(new OnClickListener() {
		//fragment btn setting	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), TabActivity.class));
				// team layout(tab activity)
			}
		});
				
		// 몇번 째 아이템인지에 따라서 어떤 화면을 설정해줄건지 switch문 
		// DB cursor 에서 해당하는 position의 사진을 가져와야 한다.
		// 이걸 이런식으로 해도 되나?? 
		switch(getArguments().getInt(ARG_SECTION_NUMBER))
		{
		case 1:
			mGroupBtn.setImageResource(R.drawable.tapbar_1);
			return rView;
		case 2:
			mGroupBtn.setImageResource(R.drawable.tapbar_1_o);
			return rView;
		}
		return null;
	}
	
}
