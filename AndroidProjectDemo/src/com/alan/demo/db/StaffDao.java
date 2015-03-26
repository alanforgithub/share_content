package com.alan.demo.db;

import java.util.ArrayList;
import java.util.List;

import com.alan.demo.Staff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StaffDao {

	private MyDataBaseHelper mHelper;

	public StaffDao(Context context) {
		mHelper = new MyDataBaseHelper(context);
	}

	/**
	 * 插入员工信息
	 * 
	 * @param staff
	 */
	public long insetStaffInfo(Staff staff) {

		if (null == staff) {
			return -1;
		}

		SQLiteDatabase db = mHelper.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("name", staff.getName());
		values.put("age", staff.getAge());

		long result = db.insert(MyDataBaseHelper.tableName, "id", values);
		db.close();
		return result;
	}

	/**
	 * 查询所有的员工信息
	 * 
	 * @return
	 */
	public List<Staff> queryStaffInfos() {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		Cursor cursor = db.query(MyDataBaseHelper.tableName, null, null, null,
				null, null, null);

		List<Staff> staffList = new ArrayList<Staff>();

		while (cursor.moveToNext()) {
			Staff staff = new Staff(cursor.getString(cursor
					.getColumnIndex("name")), cursor.getInt(cursor
					.getColumnIndex("age")));
			staffList.add(staff);
		}

		cursor.close();
		db.close();

		return staffList;

	}

	/**
	 * 根据名称删除员工
	 * 
	 * @param staff
	 * @return
	 */
	public int deleteStaffInfo(Staff staff) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		int result = db.delete(MyDataBaseHelper.tableName, "name = ?",
				new String[] { staff.getName() });
		db.close();
		return result;
	}

	/**
	 * 根据名称修改员工信息
	 * 
	 * @param staff
	 * @return
	 */
	public int modifyStaffInfo(Staff staff) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", staff.getName());
		values.put("age", staff.getAge());
		int result = db.update(MyDataBaseHelper.tableName, values, "name = ?",
				new String[] { staff.getOrginName() });
		db.close();
		return result;
	}

}
