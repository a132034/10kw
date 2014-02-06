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

	public static final String ARG_SECTION_NUMBER = "section_number"; //���� �ѹ��� ��������?? db�� ����??? 
	
	public MainDummySectionFragment() { //Constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rView = inflater.inflate(R.layout.maindummy_fragment, container, false);
		// inflater �� ��Ȯ�� ���� �������� ��������  Activity�� �Ǵٸ� xml�� �����ϱ� ���ؼ� �ʿ��� �۾� ..........?
		
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
				
		// ��� ° ������������ ���� � ȭ���� �������ٰ��� switch�� 
		// DB cursor ���� �ش��ϴ� position�� ������ �����;� �Ѵ�.
		// �̰� �̷������� �ص� �ǳ�?? 
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
