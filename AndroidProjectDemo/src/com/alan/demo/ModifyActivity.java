package com.alan.demo;

import java.util.ArrayList;
import java.util.List;

import com.alan.demo.adapter.StaffListAdapter;
import com.alan.demo.adapter.StaffListDeleteAdapter;
import com.alan.demo.adapter.StaffListModifyAdapter;
import com.alan.demo.db.StaffDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ModifyActivity extends Activity {
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
				showDialog(staff);
			}
		});

	}

	private void initListView() {
		StaffDao dao = new StaffDao(this);
		staffList = dao.queryStaffInfos();
		StaffListModifyAdapter adapter = new StaffListModifyAdapter(this,
				staffList);
		listView.setAdapter(adapter);
	}

	/**
	 * 删除信息
	 * 
	 * @param staff
	 */
	private void modifyStaffInfo(Staff staff) {
		StaffDao dao = new StaffDao(this);
		dao.modifyStaffInfo(staff);

		initListView();
	}

	private void showDialog(final Staff staff) {
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("填写修改信息");

		View view = LayoutInflater.from(ModifyActivity.this).inflate(
				R.layout.dialog_modify_staff_layout, null);

		final EditText modifyNameEdit = (EditText) view
				.findViewById(R.id.modify_staff_name);
		final EditText modifyAgeEdit = (EditText) view
				.findViewById(R.id.modify_staff_age);

		modifyNameEdit.setText(staff.getName());
		modifyAgeEdit.setText(String.valueOf(staff.getAge()));

		builder.setView(view);
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				String name = modifyNameEdit.getText().toString();
				String age = modifyAgeEdit.getText().toString();

				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)) {
					Toast.makeText(getApplicationContext(), "员工姓名或者员工年龄为空",
							Toast.LENGTH_SHORT).show();
					return;
				}

				Staff modifyStaff  = new Staff(name, Integer.parseInt(age));
				modifyStaff.setOrginName(staff.getName());
				modifyStaffInfo(modifyStaff);

			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

}
