package com.alan.demo;

import java.util.ArrayList;
import java.util.List;

import com.alan.demo.adapter.StaffListDeleteAdapter;
import com.alan.demo.db.StaffDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DeleteActivity extends Activity {
	private ListView listView;
	private List<Staff> staffList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_layout);
		initViews();
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.staffList);
		initListView();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Staff staff = staffList.get(position);
				deleteStaffInfo(staff);
				initListView();
			}
		});

	}

	private void initListView() {
		StaffDao dao = new StaffDao(this);
		staffList = dao.queryStaffInfos();
		StaffListDeleteAdapter adapter = new StaffListDeleteAdapter(this,
				staffList);
		listView.setAdapter(adapter);
	}
	
	/**
	 * 删除信息
	 * @param staff
	 */
	private void deleteStaffInfo(Staff staff){
		StaffDao dao = new StaffDao(this);
		dao.deleteStaffInfo(staff);
	}
}
