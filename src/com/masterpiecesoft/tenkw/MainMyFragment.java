package com.masterpiecesoft.tenkw;

import com.masterpiecesoft.tenkw.store.UserInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainMyFragment extends Fragment  {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_main_my, container,
				false);
		
		final TextView textName = (TextView)rootView.findViewById(R.id.Name);
		final EditText editName = (EditText)rootView.findViewById(R.id.EditName);
		
		final TextView textHeight = (TextView)rootView.findViewById(R.id.Height);
		final EditText editHeight = (EditText)rootView.findViewById(R.id.EditHeight);
		
		final TextView textWeight = (TextView)rootView.findViewById(R.id.Weight);
		final EditText editWeight = (EditText)rootView.findViewById(R.id.EditWeight);
		//visible == 0  , invisible == 1
		
		final ImageButton button = (ImageButton)rootView.findViewById(R.id.myinfoBtn);
		
		if(textWeight.getVisibility()==0){
			button.setBackgroundResource(R.drawable.btn_signup2);
		}else{
			button.setBackgroundResource(R.drawable.btn_done2);
		}
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//텍스트가 보일 때 누르면 수정으로 바껴야지 
				if(textWeight.getVisibility()==View.VISIBLE /*text invisible*/){
					// 수정창 나옴 
					textWeight.setVisibility(View.GONE);
					textName.setVisibility(View.GONE);
					textHeight.setVisibility(View.GONE);
					editWeight.setVisibility(View.VISIBLE);
					editName.setVisibility(View.VISIBLE);
					editHeight.setVisibility(View.VISIBLE);
					
					textWeight.setText(UserInfo.getWeight());
					textName.setText(UserInfo.getName());
					textHeight.setText(UserInfo.getHeight());
					
					button.setBackgroundResource(R.drawable.btn_done);
				}else{
					// 수정 완료 // text visible , 수정창으로 변환 
					textWeight.setVisibility(View.VISIBLE);
					textName.setVisibility(View.VISIBLE);
					textHeight.setVisibility(View.VISIBLE);
					editWeight.setVisibility(View.GONE);
					editName.setVisibility(View.GONE);
					editHeight.setVisibility(View.GONE);
					
					UserInfo.setHeight(editHeight.getText().toString());
					UserInfo.setWeight(editWeight.getText().toString());
					UserInfo.setName(editWeight.getText().toString());

						textWeight.setText(UserInfo.getWeight());
						textName.setText(UserInfo.getName());
						textHeight.setText(UserInfo.getHeight());
					
					if(editWeight.getText()==null || editName.getText()==null || editHeight.getText()==null)
						Toast.makeText(getActivity(), "빈칸은 원래 값이 유지됩니다", Toast.LENGTH_LONG);
					button.setBackgroundResource(R.drawable.btn_signup2);
				}
			}
		});
		
		
				return rootView;
				}
}
