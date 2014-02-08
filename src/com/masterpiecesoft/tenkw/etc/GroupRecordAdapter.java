package com.masterpiecesoft.tenkw.etc;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.masterpiecesoft.tenkw.R;

public class GroupRecordAdapter extends ResourceCursorAdapter {

	public GroupRecordAdapter(Context context, int layout, Cursor c) {
		super(context, layout, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent){
		
		View view = super.newView(context, cursor, parent);
		
		GroupRecordItemCache cache = new GroupRecordItemCache();
		cache.dateView = (TextView)view.findViewById(R.id.record_date);
		cache.kmView = (TextView)view.findViewById(R.id.record_km);
		
		view.setTag(cache);
		return view;
	}
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		float stepSize = 0.2f;
		GroupRecordItemCache cache = (GroupRecordItemCache)view.getTag();
		TextView dateView = cache.dateView;
		TextView kmView = cache.kmView;
		
		String date;
		switch(cursor.getPosition()){
		case 0:
			date = "Today";
			break;
		default:
			date = "D - "+cursor.getPosition();
			break;
		}
		dateView.setText(date);
		
		int kmidx = cursor.getColumnIndex("total");
		int km = cursor.getInt(kmidx);
		kmView.setText(""+km*stepSize);
		// 요런식으로 코딩함
	}
	
	public final class GroupRecordItemCache {

		TextView dateView;
		TextView kmView;
	}

}
