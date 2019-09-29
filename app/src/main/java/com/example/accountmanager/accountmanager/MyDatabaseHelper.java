package com.example.accountmanager.accountmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public MyDatabaseHelper(Context context) {
		
		super(context, "accounts.db", null, 1);
		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table accounts ( _id integer primary key autoincrement, name varchar(30),account varchar(30), password varchar(30) )");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
