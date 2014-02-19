package com.masterpiecesoft.tenkw.etc;


import java.util.ArrayList;
import java.util.List;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.User;
import com.masterpiecesoft.tenkw.store.TeamUserInfo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupRankAdapter extends BaseAdapter {

	private List<TeamUserInfo> userList;
	private Context context;
	
	public GroupRankAdapter (Context context , List<TeamUserInfo> teamUserList){
		this.userList = teamUserList;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return userList.size();
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
		
		ImageView imageView; 
		TextView UserName;
		TextView UserSubStep;
		
		if ( convertView == null ){
			 LayoutInflater mInflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			 convertView = mInflater.inflate(R.layout.list_rank_row, null);
		}
	
		UserName = (TextView)convertView.findViewById(R.id.member_name);
		UserSubStep = (TextView)convertView.findViewById(R.id.member_km);
		imageView = (ImageView)convertView.findViewById(R.id.member_img);
		
		TeamUserInfo temp = userList.get(position);
		
		UserName.setText(temp.getName());
		UserSubStep.setText(""+temp.getSubStep());
		
		if(userList.size()==4){
			switch (position){
			case 1:
				imageView.setBackgroundResource(R.drawable.untitled4);
				break;
			case 2:
				imageView.setBackgroundResource(R.drawable.untitled1);
				break;
			case 3:
				imageView.setBackgroundResource(R.drawable.untitled2);
				break;
			case 0:
				imageView.setBackgroundResource(R.drawable.untitled3);
				break;
			}
		}
	/*	*/
		return convertView;    
	}

	

}


