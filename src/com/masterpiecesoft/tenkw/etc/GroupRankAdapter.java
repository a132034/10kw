package com.masterpiecesoft.tenkw.etc;


import com.masterpiecesoft.tenkw.R;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupRankAdapter extends ResourceCursorAdapter {

	public GroupRankAdapter(Context context, int layout, Cursor c) {
		super(context, layout, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent){
		
		View view = super.newView(context, cursor, parent);
		
		GroupRankItemCache cache = new GroupRankItemCache();
		cache.nameView = (TextView)view.findViewById(R.id.member_name);
		cache.kmView = (TextView)view.findViewById(R.id.member_km);
		cache.imageView = (ImageView)view.findViewById(R.id.member_img);
		
		view.setTag(cache);
		return view;
	}
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		float stepSize = 0.2f;
		GroupRankItemCache cache = (GroupRankItemCache)view.getTag();
		TextView nameView = cache.nameView;
		TextView kmView = cache.kmView;
		ImageView imageView = cache.imageView;
		
		int nameidx = cursor.getColumnIndex("username");
		String name = cursor.getString(nameidx);
		nameView.setText(name);
		
		int kmidx = cursor.getColumnIndex("total");
		int km = cursor.getInt(kmidx);
		kmView.setText(""+km*stepSize);
		
		//사진 생략했음
	}
	
	public final class GroupRankItemCache {
		ImageView imageView;
		TextView nameView;
		TextView kmView;
	}

}


