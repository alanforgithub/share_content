package com.alan.demo;

import com.alan.demo.db.StaffDao;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	private EditText staffNameEdit;
	private EditText staffAgeEdit;
	private Button insertBtn;
	private StaffDao mStaffDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_layout);

		initViews();

	}

	private void initViews() {
		staffNameEdit = (EditText) findViewById(R.id.staff_name);
		staffAgeEdit = (EditText) findViewById(R.id.staff_age);
		insertBtn = (Button) findViewById(R.id.insertBtn);

		mStaffDao = new StaffDao(this);

		insertBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = staffNameEdit.getText().toString();
				String age = staffAgeEdit.getText().toString();

				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)) {
					Toast.makeText(getApplicationContext(), "员工姓名或者员工年龄为空",
							Toast.LENGTH_SHORT).show();
					return;
				}

				long result = mStaffDao.insetStaffInfo(new Staff(name, Integer
						.parseInt(age)));
				if (result != -1) {
					Toast.makeText(getApplicationContext(), "插入成功",
							Toast.LENGTH_SHORT).show();
					staffNameEdit.setText(null);
					staffAgeEdit.setText(null);
				} else {
					Toast.makeText(getApplicationContext(), "插入失败",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

}
