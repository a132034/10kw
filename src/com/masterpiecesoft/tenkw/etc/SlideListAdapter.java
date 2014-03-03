package com.masterpiecesoft.tenkw.etc;

import com.masterpiecesoft.tenkw.R;
import com.masterpiecesoft.tenkw.DbManager.User;
import com.masterpiecesoft.tenkw.store.Team;
import com.masterpiecesoft.tenkw.store.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideListAdapter extends BaseAdapter{
	
	private Context context;
	private User person;
	
	public SlideListAdapter(Context context ){
		this.context = context;
//		this.person = person;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        ImageView imgIcon ;
        TextView txtTitle;

        switch (position){
        case 0:
        	convertView = mInflater.inflate(R.layout.list_image_row, null);
        	imgIcon= (ImageView) convertView.findViewById(R.id.personal_img);
        	 txtTitle = (TextView) convertView.findViewById(R.id.personal_list_txt);
             txtTitle.setText(UserInfo.getName());
        	break;
        case 1 :
        	convertView = mInflater.inflate(R.layout.list_slide_row, null);
        	imgIcon= (ImageView) convertView.findViewById(R.id.personal_img);
        	imgIcon.setImageResource(R.drawable.icon_home);
        	txtTitle = (TextView) convertView.findViewById(R.id.personal_list_txt);
        	txtTitle.setText("HOME");
        	break;
        case 2 :
        	convertView = mInflater.inflate(R.layout.list_slide_row, null);
        	imgIcon= (ImageView) convertView.findViewById(R.id.personal_img);
        	imgIcon.setImageResource(R.drawable.icon_my);
        	txtTitle = (TextView) convertView.findViewById(R.id.personal_list_txt);
        	txtTitle.setText("MY");
        	break;
        case 3 :
        	convertView = mInflater.inflate(R.layout.list_slide_row, null);
        	imgIcon= (ImageView) convertView.findViewById(R.id.personal_img);
        	imgIcon.setImageResource(R.drawable.icon_donation);
        	txtTitle = (TextView) convertView.findViewById(R.id.personal_list_txt);
        	txtTitle.setText("DONATION");
        	break;
        case 4 :
        	convertView = mInflater.inflate(R.layout.list_slide_row, null);
        	imgIcon= (ImageView) convertView.findViewById(R.id.personal_img);
        	imgIcon.setImageResource(R.drawable.icon_setting);
        	txtTitle = (TextView) convertView.findViewById(R.id.personal_list_txt);
        	txtTitle.setText("SETTING");
        	break;
        case 5 :
        	convertView = mInflater.inflate(R.layout.list_slide_info, null);
        	TextView distanceTxt = (TextView)convertView.findViewById(R.id.slideList_km);
        	distanceTxt.setText("0");
        	TextView kcalTxt = (TextView)convertView.findViewById(R.id.slideList_km);
        	kcalTxt.setText("0");
        	TextView stepTxt = (TextView)convertView.findViewById(R.id.slideList_km);
        	stepTxt.setText(""+UserInfo.getUserTotalStep());
        	
        	break;
        	default:
        		break;
        }
        
        convertView.setBackgroundResource(R.drawable.personal_list_bg);
        return convertView;
	}
}