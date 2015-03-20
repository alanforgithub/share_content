package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_layout);

		listView = (ListView) findViewById(R.id.listView);

//		initArrayAdapter();
		
		initBaseAdapter();

	}

	private void initBaseAdapter() {
		
		

	}

	/**
	 * ArrayAdapter
	 */
	private void initArrayAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_listview_layout, getData());
		listView.setAdapter(adapter);
	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("测试数据" + i);
		}
		return data;
	}

}
