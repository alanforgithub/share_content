package com.alan.fileoperation.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "mysqlite.db";
	public static int DB_VERSION = 1;

	/**
	 * 
	 * @param context
	 * @param name
	 *            数据库名称
	 * @param factory
	 *            游标对象的工厂，如果null则默认
	 * @param version
	 *            数据库版本信息，从1开始
	 */
	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 数据库创建时执行
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("android", "oncreate");
		db.execSQL("CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) , age INTEGER)");

	}

	/**
	 * 数据库更新时执行
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		Log.i("android", "onUpgrade");
		db.execSQL("ALTER TABLE person ADD age INTEGER");

	}

}
