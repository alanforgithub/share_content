package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.List;

import com.alan.androiddemo.adapter.MyBaseAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListViewActivity extends Activity {

	private ListView listView;
	private List<String> listData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_layout);

		listData = getData();
		
		listView = (ListView) findViewById(R.id.listView);

	//	initArrayAdapter();
		
		initBaseAdapter();
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(ListViewActivity.this, listData.get(position), Toast.LENGTH_SHORT).show();
				
			}
		});

	}

	private void initBaseAdapter() {
		MyBaseAdapter adapter = new MyBaseAdapter(listData, this);
		listView.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
		
		

	}

	/**
	 * ArrayAdapter
	 */
	private void initArrayAdapter() {
		//android.R.layout.simple_list_item_1
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_listview_layout, listData);
		listView.setAdapter(adapter);
	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {
			data.add("测试数据" + i);
		}
		return data;
	}

}
