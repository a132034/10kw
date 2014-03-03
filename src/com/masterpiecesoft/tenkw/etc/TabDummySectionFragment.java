package com.masterpiecesoft.tenkw.etc;

import java.util.ArrayList;
import java.util.List;

import com.masterpiecesoft.tenkw.CreateTeamActivity;
import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.layout.CreateGroupActivity;
import com.masterpiecesoft.tenkw.store.RunInfo;
import com.masterpiecesoft.tenkw.store.TeamUserInfo;
import com.masterpiecesoft.tenkw.store.UserInfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

public class TabDummySectionFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private UserInfo userInfo = new UserInfo();
	private RunInfo runInfo = new RunInfo();

	public TabDummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		List<TeamUserInfo> teamUserInfo = (userInfo.getTeamList().get(runInfo
				.getTabIndex())).getUserList();

		View rootView;
		// 여기가 실질적으로 해주어야 하는 부분이다. 근데 엄청 오래걸릴 것 같은 느낌 이미지 처리 해본적도 없고 그래프도 있으니 고민
		// 무지 많이 됨 .... ㅜ.ㅜ
		switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
		case 1:
			rootView = inflater.inflate(R.layout.activity_group_main,
					container, false);
			return groupMainPage(rootView, teamUserInfo);
		case 2:
			if(teamUserInfo.size()!=1){
			rootView = inflater.inflate(R.layout.activity_added_friends,
					container, false);
			return addedFriendPage(rootView, teamUserInfo);}
			else{
				rootView = inflater.inflate(R.layout.invate_friends,container,false);
				return addFriendPlease(rootView, teamUserInfo);
			}
		case 3:
			if(teamUserInfo.size()!=1){
			rootView = inflater.inflate(R.layout.activity_graph, container,
					false);
			return graphPage(rootView, teamUserInfo);}else{
				rootView = inflater.inflate(R.layout.invate_friends,container,false);
				return addFriendPlease(rootView, teamUserInfo);
			}
		case 4:
			if(teamUserInfo.size()!=1){
			rootView = inflater.inflate(R.layout.activity_calendar, container,
					false);
			return calendarPage(rootView, teamUserInfo);}else{
				rootView = inflater.inflate(R.layout.invate_friends,container,false);
				return addFriendPlease(rootView, teamUserInfo);
			}
		}
		return null;
	}
	
	private View addFriendPlease(View view, List<TeamUserInfo> teamUserlist){
		
		ImageButton imgBtn = (ImageButton) view.findViewById(R.id.imageButton1);
		TextView txt = (TextView)view.findViewById(R.id.textView1);
		txt.setText(userInfo.getTeamList().get(runInfo.getTabIndex()).getTeamTitle());
		imgBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),CreateGroupActivity.class));
			}
			
		});
		return view ;
	}

	private View groupMainPage(View view, List<TeamUserInfo> teamUserlist) {

		ImageView imageView = (ImageView) view
				.findViewById(R.id.group_main_image);
		TextView title = (TextView) view.findViewById(R.id.group_main_title);
		TextView num = (TextView) view.findViewById(R.id.group_main_num);

		PieGraph pg1 = (PieGraph) view.findViewById(R.id.graph1);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#35aadc")); // 파랑
		slice.setValue(teamUserlist.get(0).getSubStep());
		slice.setTitle("ME");
		pg1.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#47494c")); // 회색
		slice.setValue(5);
		pg1.addSlice(slice);

		PieGraph pg2 = (PieGraph) view.findViewById(R.id.graph2);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#35aadc")); // 파랑
		slice.setTitle("TEAM");
		slice.setValue(UserInfo.getTeamList().get(RunInfo.getTabIndex())
				.getTeamtotalStep());
		pg2.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#47494c")); // 회색
		slice.setValue(5 * teamUserlist.size());
		pg2.addSlice(slice);

		switch (RunInfo.getTabIndex()) {
		case 0:
			imageView.setBackgroundResource(R.drawable.back4);
			title.setText(UserInfo.getTeamList().get(RunInfo.getTabIndex())
					.getTeamTitle());
			num.setText("" + teamUserlist.size() + " 명");
			break;
		case 1:
			imageView.setBackgroundResource(R.drawable.back3);
			title.setText(UserInfo.getTeamList().get(RunInfo.getTabIndex())
					.getTeamTitle());
			num.setText("" + teamUserlist.size() + " 명");
			break;
		default:
			imageView.setBackgroundResource(R.drawable.back2);
			title.setText(UserInfo.getTeamList().get(RunInfo.getTabIndex())
					.getTeamTitle());
			num.setText("" + teamUserlist.size() + " 명");
			break;
		}

		return view;
	}

	private View addedFriendPage(View view, List<TeamUserInfo> teamUserlist) {

		ListView rankList = (ListView) view.findViewById(R.id.add_friends_list);
		GroupRankAdapter adapter = new GroupRankAdapter(getActivity()
				.getApplicationContext(), teamUserlist);
		rankList.setAdapter(adapter);

		ArrayList<Bar> points = new ArrayList<Bar>();

		for (int i = 0; i < teamUserlist.size(); i++) {
			Bar d = new Bar();
			d.setColor(Color.parseColor("#99CC00"));
			d.setName(teamUserlist.get(i).getName());
			d.setValue(teamUserlist.get(i).getSubStep());
			// d.setValueString(stringList[i]);
			points.add(d);
		}

		TextView text = (TextView) view.findViewById(R.id.added_friend_my_txt);
		text.setText("" + teamUserlist.get(0).getSubStep());

		TextView totalText = (TextView) view
				.findViewById(R.id.added_friend_total_txt);
		totalText.setText("/ " + teamUserlist.size() * 5);

		TextView missionText = (TextView) view
				.findViewById(R.id.added_friend_now_txt);
		missionText.setText(""
				+ UserInfo.getTeamList().get(RunInfo.getTabIndex())
						.getTeamtotalStep());

		BarGraph g = (BarGraph) view.findViewById(R.id.added_horizontal_graph);
		g.setBars(points);


		return view;
	}

	private View graphPage(View view, List<TeamUserInfo> teamUserlist) {

		ListView record = (ListView) view.findViewById(R.id.graph_list);
		GroupRecordAdapter adapter = new GroupRecordAdapter(getActivity()
				.getApplicationContext(), teamUserlist);
		record.setAdapter(adapter);

		// y max 는 4 까지만 표현하자 나눠서 곱하기 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ 리사이즈가 안됨 왜 안되는지 모르겠음
		Line line = new Line();

		for (int i = 0; i < 3; i++) {
			LinePoint p = new LinePoint();
			p.setX(i);
			p.setY(teamUserlist.get(0).getData().D[i]);
			line.addPoint(p);
		}

		LinePoint p = new LinePoint();
		p.setX(4);
		p.setY(teamUserlist.get(0).getSubStep());
		line.addPoint(p);

		line.setColor(Color.parseColor("#FFBB33"));

		LineGraph lineGraph = (LineGraph) view.findViewById(R.id.graph_graph);
		lineGraph.addLine(line);
		lineGraph.setRangeY(0, 7);
		lineGraph.setLineToFill(0);

		return view;
	}

	private View calendarPage(View view, List<TeamUserInfo> teamUserlist) {
		return view;
	}
}
