package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class galleryActivity extends Activity {
	private Gallery mGallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery_layout);
		
		mGallery = (Gallery) findViewById(R.id.gallery_view);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_listview_layout, getData());

		mGallery.setAdapter(adapter);
		
	}
	
	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {
			data.add("测试数据" + i);
		}
		return data;
	}

}
