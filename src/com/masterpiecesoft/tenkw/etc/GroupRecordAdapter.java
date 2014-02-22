package com.masterpiecesoft.tenkw.etc;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.store.TeamUserInfo;

public class GroupRecordAdapter extends BaseAdapter {
	
	private List<TeamUserInfo> userList;
	private Context context ;
	
	public GroupRecordAdapter (Context context , List<TeamUserInfo> teamUserList){
		this.userList = teamUserList;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		TextView Dday;
		TextView KM;
		
		if (convertView == null){
			LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			 convertView = mInflater.inflate(R.layout.list_record_row, null);
		}
		
		Dday = (TextView)convertView.findViewById(R.id.record_date);
		KM = (TextView)convertView.findViewById(R.id.record_km);
		
		switch (position){
		case 0: // today
			Dday.setText("D day");
			KM.setText(""+userList.get(1).getSubStep()+"KM");
			break;
		case 1: // today
			Dday.setText("D - 1");
			KM.setText(""+userList.get(1).getData().D[0]+"KM");
			break;
		case 2: // today
			Dday.setText("D - 2");
			KM.setText(""+userList.get(1).getData().D[1]+"KM");
			break;
		case 3: // today
			Dday.setText("D - 3");
			KM.setText(""+userList.get(1).getData().D[2]+"KM");
			break;
		}
		return convertView;
	}
	
}
