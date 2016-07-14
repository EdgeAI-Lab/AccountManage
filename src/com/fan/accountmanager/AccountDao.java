package com.fan.accountmanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountDao {

	private MyDatabaseHelper databaseHelper;
	
	/*
	 * 
	 * 
	 * 
	 */
	public AccountDao(Context context) {
		
		databaseHelper = new MyDatabaseHelper(context);
		
	}
	
	
	/*
	 *  insert one entry into table
	 * 
	 */
	public void Add(Account account){
		
		SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("insert into accounts values(null,?,?,?)",new Object[]{account.getName(),account.getAccount(),account.getPassword()});
	}
	
	/*
	 * 
	 * delete one entry from table
	 * 
	 */
	
	public void Delete(String name){
		
		SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("delete from accounts where name=?",new String[]{name});
	}
	
	/*
	 * 
	 * update one entry in the table
	 * 
	 */
	
	public void Update(Account account){
//		System.out.println(account.getId());
		SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("update accounts set name=?,account=?,password=? where _id=?",new Object[]{account.getName(),account.getAccount(),account.getPassword(),account.getId()});	
	}
	
	/*
	 * 
	 * get all name
	 * 
	 * 
	 */
	public List<String> getAll(){
		
		List<String> nameList = new ArrayList<String>();
		
		SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select name from accounts", null);
		
		
		while(cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex("name"));
			nameList.add(name);
		}
		
		cursor.close();
		
		return nameList;
	}
	
	/*
	 * find one entry in the table by name
	 * 
	 */
	public Account Find(String name){
		
		SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select * from accounts where name=?", new String[]{name});
		
		Account mAccount = null;
		
		if(cursor.moveToNext())
		{
			String account  = cursor.getString(cursor.getColumnIndex("account"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			
			mAccount = new Account(name,account, password);
		}
		
		cursor.close();
		
		return mAccount;
	}
	
	
	public int GetId(String name)
	{

		SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select * from accounts where name=?", new String[]{name});
		
		int id = 0;
		if(cursor.moveToNext())
		{
			id = cursor.getInt(cursor.getColumnIndex("_id"));
		}
		
		cursor.close();
		
		return id;
	}
	

}
