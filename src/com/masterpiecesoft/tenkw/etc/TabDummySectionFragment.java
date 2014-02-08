package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabDummySectionFragment extends Fragment{

	public static final String ARG_SECTION_NUMBER = "section_number";

	public TabDummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView ;
		// ���Ⱑ ���������� ���־�� �ϴ� �κ��̴�. �ٵ� ��û �����ɸ� �� ���� ���� �̹��� ó�� �غ����� ���� �׷����� ������ ��� ���� ���� �� .... ��.�� 
		switch(getArguments().getInt(ARG_SECTION_NUMBER))
		{
		case 1:
			rootView = inflater.inflate(R.layout.activity_personal_info,
					container, false);
			//TextView detailTxt = (TextView) rootView.findViewById(R.id.detail_txt);
			//detailTxt.setText("detail view");
			return rootView;
			
		case 2:
			rootView = inflater.inflate(R.layout.activity_added_friends,
					container, false);
			//TextView addedTxt = (TextView) rootView.findViewById(R.id.added_txt);
			//addedTxt.setText("added friends");
			return rootView;
			
		case 3:
			rootView = inflater.inflate(R.layout.activity_graph,
					container, false);
			//TextView graphTxt = (TextView) rootView.findViewById(R.id.graph_txt);
			//graphTxt.setText("graph");
			return rootView;
			
		case 4:
			rootView = inflater.inflate(R.layout.activity_calendar,
					container, false);
			//TextView calendarTxt = (TextView) rootView.findViewById(R.id.calendar_txt);
			//calendarTxt.setText("calendar");
			return rootView;
		}

		return null;
	}
}
