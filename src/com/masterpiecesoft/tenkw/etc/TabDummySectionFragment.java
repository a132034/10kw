package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabDummySectionFragment extends Fragment{
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public TabDummySectionFragment(){ // constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rView;
		// ���Ⱑ ���������� ���־�� �ϴ� �κ��̴�. �ٵ� ��û �����ɸ� �� ���� ���� �̹��� ó�� �غ����� ���� �׷����� ������ ��� ���� ���� �� .... ��.��  main�������� �ǹ� 
		switch(getArguments().getInt(ARG_SECTION_NUMBER))
		{
		case 1:
			rView = inflater.inflate(R.layout.activity_personal_info,
					container, false);
			TextView detailTxt = (TextView) rView.findViewById(R.id.detail_txt);
			detailTxt.setText("detail view");
			return rView;
			
		case 2:
			rView = inflater.inflate(R.layout.activity_added_friends,
					container, false);
			TextView addedTxt = (TextView) rView.findViewById(R.id.added_txt);
			addedTxt.setText("added friends");
			return rView;
			
		case 3:
			rView = inflater.inflate(R.layout.activity_graph,
					container, false);
			TextView graphTxt = (TextView) rView.findViewById(R.id.graph_txt);
			graphTxt.setText("graph");
			return rView;
			
		case 4:
			rView = inflater.inflate(R.layout.activity_calendar,
					container, false);
			TextView calendarTxt = (TextView) rView.findViewById(R.id.calendar_txt);
			calendarTxt.setText("calendar");
			return rView;
		}
		
		
		
		return null;
	}
	
	
	
}
