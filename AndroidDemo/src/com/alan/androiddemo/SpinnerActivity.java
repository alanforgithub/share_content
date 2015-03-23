package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity {

	private Spinner mSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spinner_layout);

		mSpinner = (Spinner) findViewById(R.id.spinnerView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getData());

		mSpinner.setAdapter(adapter);

	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {
			data.add("测试数据" + i);
		}
		return data;
	}

}
