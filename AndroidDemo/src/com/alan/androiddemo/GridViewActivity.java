package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridViewActivity extends Activity {
	
	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_gridview_layout);
		
		mGridView = (GridView) findViewById(R.id.gridView);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_listview_layout, getData());

		mGridView.setAdapter(adapter);
		
	
	}
	
	
	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {
			data.add("测试数据" + i);
		}
		return data;
	}

}
