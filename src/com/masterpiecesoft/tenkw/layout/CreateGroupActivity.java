package com.masterpiecesoft.tenkw.layout;

import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.masterpiecesoft.tenkw.R;


public class CreateGroupActivity extends Activity{
	
	private ImageButton leftBtn, rightBtn;
	private ListView contactList;
	private Cursor cursor;
	private static Uri contactURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	private final String SORTINGORDER = android.provider.ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	
	//검색할 컬럼
	static final String[] item = new String[] {
		ContactsContract.CommonDataKinds.Phone._ID,
		ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
		ContactsContract.CommonDataKinds.Phone.NUMBER,
		ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
		ContactsContract.CommonDataKinds.Phone.CONTACT_ID
	};
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_group);
		
		contactList = (ListView)findViewById(R.id.create_listView);
		
		leftBtn = (ImageButton)findViewById(R.id.create_left_btn);
		leftBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
			}//취소버튼
		});
		
		rightBtn = (ImageButton)findViewById(R.id.create_right_btn);
		rightBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CreateGroupActivity.this, GroupActivity.class));
			}// 확인 버튼 예정  ListView 의 Adapter 에서 선언한 checked[] 배열 이용해서 create table Db 가 실행되어야 한다. 레이아웃 깊이를 하나더 만들어야 할듯?
		});
		
		// 주소록 가져오기
		// DB접근 해서 정보 읽어옴 
		cursor = getContentResolver().query(contactURI, item, null, null, SORTINGORDER);
		// 현재 모든 전화번호부 정보를 가져옴 그러나 우리 DB와 연동시켜서 겹치는 것만 가져오게 해야한다. 
		// ListView Adapter , Cursor 사용에 편리한? 전용? ResourceCursorAdapter 상속받음 
		ContactListItemAdapter contactListAdapt = new ContactListItemAdapter(this, R.layout.list_create_group_row, cursor);
		contactList.setAdapter(contactListAdapt);
		
	}


	public final class ContactListItemAdapter extends ResourceCursorAdapter{

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View view = super.newView(context, cursor, parent);
			 
			// listView에 item 이 하나도 없을때? 그때 실행 되는 것 따라서 이때 layout 의 각각 요소들과 java 요소를 연결 시켜서 사용 
			// bind view 가 반복되면서 list 를 채움 
			// static 하나의 공간을 가지고 계속 사용 
			ContactListItemCache cache = new ContactListItemCache();
			cache.nameView = (TextView) view
					.findViewById(R.id.create_item_name_txt);
			cache.phoneView = (TextView) view
					.findViewById(R.id.create_item_phone_txt);
			cache.photoView = (ImageView) view
					.findViewById(R.id.create_item_image);
			
			cache.checkBox = (CheckBox)view.findViewById(R.id.create_item_checkbox);
			
			view.setTag(cache);
			return view;
		}

		private boolean[] checked;  // list 위아래로 움직일때 bindView가 계속 실행됨 따라서 check 박스가 유지되지 못함 그래서 checkBox를 기록할 공간 필요 
		@SuppressWarnings("deprecation")
		public ContactListItemAdapter(Context context, int layout, Cursor c) {
			super(context, layout, c);
			checked = new boolean[cursor.getCount()]; //몬지잘모루게땅
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			ContactListItemCache cache = (ContactListItemCache) view.getTag();
			TextView nameView = cache.nameView;
			TextView phoneView = cache.phoneView;
			ImageView photoView = cache.photoView;
			CheckBox checkBox = cache.checkBox;
			// contactListItemCache는 아래쪽에 선언되어 있음, List 한 행에 들어갈 정보 class
			final int position = cursor.getPosition(); // 현재 표시되는 db item 의 index 
			
			//이름
			int nameidx = cursor.getColumnIndex(Contacts.DISPLAY_NAME); // DB에서 이름이 있는게 몇번째 위치인지 검색
			String name = cursor.getString(nameidx); // 그 위치의 값을 가져옴 
			nameView.setText(name); // 이름 가져와서 text 설정 
			
			//전화번호
			int phoneidx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			String phone = cursor.getString(phoneidx);
			phoneView.setText(phone);
			
			// 사진  사진은 잘 모르겠음 위와 같은 흐름같긴 한데 잘 모르겠음 - 난 위에도 다 모르겠음.
			ContentResolver cr = getContentResolver();
			int contactId_idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
			Long contactId = cursor.getLong(contactId_idx);
			
			Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
			
			if(input != null){// DB에 있던 형식을 Bitmap 으로 바꿔서 ImageView에 표시
				Bitmap contactPhoto = BitmapFactory.decodeStream(input);
				photoView.setImageBitmap(contactPhoto);
			}
			
			//checkbox 클릭되면 배열에 그 위치에 대한 정보를 저장 하고 
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					checked[position] = isChecked;
				}
			});
			checkBox.setChecked(checked[position]); // 그 정보에 따라서 체크박스의 체크 유무 결정 
		}
	
	}


	final static class ContactListItemCache {
		public TextView nameView, phoneView;
		public ImageView photoView;
		public CheckBox checkBox ;
	}
}
