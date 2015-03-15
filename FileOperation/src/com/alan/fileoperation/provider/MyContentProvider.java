package com.alan.fileoperation.provider;

import com.alan.fileoperation.db.PersonDaoForProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	Uri uri = Uri.parse("content://com.alan.provider/person");
	private PersonDaoForProvider mPersionDao;
	private final String tableName = "person";
	
	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		
		return mPersionDao.delete(tableName, arg1, arg2);
	}

	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		mPersionDao.insert(tableName, values);
		return uri;
	}

	@Override
	public boolean onCreate() {
		mPersionDao = new PersonDaoForProvider(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		return mPersionDao.queryAll(tableName, arg1, arg2, arg3, arg4, null, null);
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		
		mPersionDao.update(tableName, arg1, arg2, arg3);
		
		return 0;
	}
	
	@Override
	public String getType(Uri arg0) {
		return null;
	}

}
