package com.alan.demo;

import java.util.ArrayList;
import java.util.List;

import com.alan.demo.adapter.StaffListAdapter;
import com.alan.demo.db.StaffDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class QueryActivity extends Activity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_layout);
		initViews();
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.staffList);
		StaffListAdapter adapter = new StaffListAdapter(this, getStaffList());
		listView.setAdapter(adapter);
		
	}
	
	private List<Staff> getStaffList(){
		
		StaffDao dao = new StaffDao(this);
		return dao.queryStaffInfos();
	}

}
