package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;


public class TabDummySectionFragment extends Fragment{

	public static final String ARG_SECTION_NUMBER = "section_number";

	public TabDummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView ;
		// 食奄亜 叔霜旋生稽 背爽嬢醤 馬澗 採歳戚陥. 悦汽 畳短 神掘杏険 依 旭精 汗界 戚耕走 坦軒 背沙旋亀 蒸壱 益掘覗亀 赤生艦 壱肯 巷走 弦戚 喫 .... ぬ.ぬ 
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
		
		Cursor cursor = null ; 
		ListView rankList = (ListView)view.findViewById(R.id.add_friends_list);
		GroupRankAdapter adapter = new GroupRankAdapter(getActivity(),R.layout.list_rank_row, cursor);
		rankList.setAdapter(adapter);
		
		return view;
	}
	
	private View graphPage(View view){
		
		Cursor cursor = null ; 
		ListView record = (ListView)view.findViewById(R.id.graph_list);
		GroupRecordAdapter adapter = new GroupRecordAdapter(getActivity(),R.layout.list_record_row, cursor);
		record.setAdapter(adapter);
		
		// y max 澗 4 猿走幻 妊薄馬切 蟹寛辞 咽馬奄 ぞぞぞぞぞぞぞぞぞぞぞぞ 軒紫戚綜亜 照喫 訊 照鞠澗走 乞牽畏製 
				Line line = new Line();
				LinePoint p = new LinePoint();
				p.setX(0);
				p.setY(1);
				line.addPoint(p);
				
				p = new LinePoint();
				p.setX(1);
				p.setY(1);
				line.addPoint(p);
				
				line.setColor(Color.parseColor("#FFBB33"));
				
				LineGraph lineGraph = (LineGraph)view.findViewById(R.id.graph_graph);
				lineGraph.addLine(line);
				lineGraph.setRangeY(0,5);
				lineGraph.setLineToFill(0);
				
		return view;
	}
	
	private View calendarPage(View view){
		return view;
	}
}
