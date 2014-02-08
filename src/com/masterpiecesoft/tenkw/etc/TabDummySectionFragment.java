package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TabDummySectionFragment extends Fragment{

	public static final String ARG_SECTION_NUMBER = "section_number";

	public TabDummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView ;
		// 여기가 실질적으로 해주어야 하는 부분이다. 근데 엄청 오래걸릴 것 같은 느낌 이미지 처리 해본적도 없고 그래프도 있으니 고민 무지 많이 됨 .... ㅜ.ㅜ 
		switch(getArguments().getInt(ARG_SECTION_NUMBER)){
		case 1:
			rootView = inflater.inflate(R.layout.activity_group_main,
					container, false);
			return groupMainPage(rootView);	
		case 2:
			rootView = inflater.inflate(R.layout.activity_added_friends,
					container, false);
			return addedFriendPage(rootView);
		case 3:
			rootView = inflater.inflate(R.layout.activity_graph,
					container, false);
			return graphPage(rootView);
		case 4:
			rootView = inflater.inflate(R.layout.activity_calendar,
					container, false);
			return calendarPage(rootView);
		}
		return null;
	}
	
	private View groupMainPage(View view){
		return view;
	}
	
	private View addedFriendPage(View view){
		ListView rankList = (ListView)view.findViewById(R.id.add_friends_list);
		GroupRankAdapter adapter = new GroupRankAdapter(getActivity(),R.layout.list_rank_row, Cursor c);
		rankList.setAdapter(adapter);
		return view;
	}
	
	private View graphPage(View view){
		ListView record = (ListView)view.findViewById(R.id.graph_list);
		GroupRecordAdapter adapter = new GroupRecordAdapter(getActivity(),R.layout.list_record_row, Cursor c);
		record.setAdapter(adapter);
		return view;
	}
	
	private View calendarPage(View view){
		return view;
	}
}
