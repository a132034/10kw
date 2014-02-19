package com.masterpiecesoft.tenkw.DbManager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class androidDBmanager extends SQLiteOpenHelper{

	
	public static String DB_NAME = "10kw_login.db";

	public androidDBmanager(Context context) {
		super(context, DB_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE login (phone VARCHAR(15);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS login");
		onCreate(db);
	}
	
	public int Insert(SQLiteDatabase db, String data){
		try{
			db.execSQL("insert into " + "login" + "values('"+data+"');");
		}catch(SQLException e){
			return 1;
		}
		return 0;
	}
}
