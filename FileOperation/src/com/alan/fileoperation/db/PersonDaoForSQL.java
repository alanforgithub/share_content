package com.alan.fileoperation.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoForSQL {

	private MySQLiteHelper helper;

	public PersonDaoForSQL(Context context) {
		helper = new MySQLiteHelper(context);
	}

	public void insert(Person p) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("INSERT INTO person(name, age) VALUES(?,?)",
				new Object[] { p.getName(), p.getAge() });
		db.close();
	}

	public void delete(int id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("DELETE FROM person WHERE id=?", new Object[] { id });
		db.close();
	}

	public void update(Person p) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("UPDATE person SET name=?, age=? WHERE id=?",
				new Object[] { p.getName(), p.getAge(), p.getId() });
		db.close();
	}

	public Person query(int id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT name, age FROM person WHERE id=?",
				new String[] { id + "" }); // 查询, 得到游标(结果集)
		Person p = null;
		if (c.moveToNext()) { // 把游标向后移动一位, 判断是否包含数据
			String name = c.getString(c.getColumnIndex("name")); // 先根据列名获取索引,
																	// 再根据索引获取数据
			int age = c.getInt(1); // 直接根据索引获取数据
			p = new Person(id, name, age);
		}
		c.close();
		db.close();
		return p;
	}

	public List<Person> queryAll() {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT id, name, age FROM person", null);
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

	public List<Person> queryPage(int pageNum, int pageSize) {
		int offset = (pageNum - 1) * pageSize;
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"SELECT id, name, age FROM person LIMIT ?,?", new String[] {
						offset + "", pageSize + "" });
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

}
