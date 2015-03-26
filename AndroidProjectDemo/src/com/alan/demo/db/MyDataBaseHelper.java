package com.alan.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

	public static final String dbName = "mysql.db";
	public static final int dbVersion = 1;
	public static final String tableName = "person";

	public MyDataBaseHelper(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+tableName+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name VARCHAR(20)," + "age INTEGER)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE person ADD age INTEGER");
	}

}
