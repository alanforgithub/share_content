package com.alan.fileoperation.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDao {
	
	private MySQLiteHelper helper;
	
	
	private static final String tableName = "person";
	
	public PersonDao(Context context){
		helper = new MySQLiteHelper(context);
	}
	
	/**
	 * 插入数据
	 * @param p
	 * @return 刚插入数据的id
	 */
	public long insert(Person p){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", p.getName());    //key列名，value值
		values.put("age", p.getAge());
		//表名，列名（任意一列的列名，为了对应ContentValues没有值的情况），插入的数据
		long id = db.insert(tableName, "id", values);
		db.close();
		return id;
	}
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	public int delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		//表名，删除条件，条件对应的值
		int rows = db.delete(tableName, "name=?", new String[]{name+""});
		db.close();
		return rows;
	}
	
	/**
	 * 查询数据
	 * @param id
	 */
	public List<Person> query(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		//表名，列名(需要查询的列)，查询条件，条件对应的值，分组条件，分组条件对应的值，排序
		Cursor c = db.query(tableName, null, "name=?", new String[]{name+""}, null, null, null);
		List<Person> persons = new ArrayList<Person>();
		if(c.moveToNext()){
			Person p = new Person();
			p.setId(c.getInt(0));
			p.setName(c.getString(c.getColumnIndex("name")));
			p.setAge(c.getInt(c.getColumnIndex("age")));
			persons.add(p);
		}
		c.close();
		db.close();
		return persons;
		
		
		
		
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Person> queryAll() {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("person", null, null, null, null, null, null);
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("id"));
			String name = c.getString(c.getColumnIndex("name"));
			int age = c.getInt(c.getColumnIndex("age"));
			persons.add(new Person(id, name, age));
		}
		c.close();
		db.close();
		return persons;
	}
	
	
	/**
	 * 更新数据
	 * @param p
	 * @return
	 */
	public int update(Person p){
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", p.getName());
		values.put("age", p.getAge());
		//表名，更新的数据，更新条件，条件所对应的值
		int rows = db.update(tableName, values, "name=?", new String[]{p.getName()+""});
		db.close();
		return rows;
	}
}
