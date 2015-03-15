package com.alan.fileoperation.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoForProvider {

	private MySQLiteHelper helper;

	public PersonDaoForProvider(Context context) {
		helper = new MySQLiteHelper(context);
	}

	/**
	 * 插入数据
	 * 
	 * @param p
	 * @return 刚插入数据的id
	 */
	public long insert(String tableName, ContentValues values) {
		SQLiteDatabase db = helper.getWritableDatabase();
		// 表名，列名（任意一列的列名，为了对应ContentValues没有值的情况），插入的数据
		long id = db.insert(tableName, "id", values);
		db.close();
		return id;
	}

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String tableName, String where, String[] args) {
		SQLiteDatabase db = helper.getWritableDatabase();
		// 表名，删除条件，条件对应的值
		int rows = db.delete(tableName, where, args);
		db.close();
		return rows;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public Cursor queryAll(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		// 表名，列名(需要查询的列)，查询条件，条件对应的值，分组条件，分组条件对应的值，排序
		Cursor c = db.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy);
//		Person p = new Person();
//		while (c.moveToNext()) {
//			int id = c.getInt(c.getColumnIndex("id"));
//			String name = c.getString(c.getColumnIndex("name"));
//			int age = c.getInt(c.getColumnIndex("age"));
//			persons.add(new Person(id, name, age));
//		}
//		c.close();
//		db.close();
		return c;
	}

	/**
	 * 更新数据
	 * 
	 * @param p
	 * @return
	 */
	public int update(String tableName, ContentValues values, String where,
			String[] args) {
		SQLiteDatabase db = helper.getReadableDatabase();

		// 表名，更新的数据，更新条件，条件所对应的值
		int rows = db.update(tableName, values, where, args);
		db.close();
		return rows;
	}
}
