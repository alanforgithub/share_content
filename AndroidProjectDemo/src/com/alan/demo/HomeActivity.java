package com.alan.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home_layout);

		initViews();

	}

	private void initViews() {

		Button logoutBtn = (Button) findViewById(R.id.logout);
		logoutBtn.setOnClickListener(this);

		Button addBtn = (Button) findViewById(R.id.add);
		addBtn.setOnClickListener(this);
		Button queryBtn = (Button) findViewById(R.id.query);
		queryBtn.setOnClickListener(this);
		Button deleteBtn = (Button) findViewById(R.id.delete);
		deleteBtn.setOnClickListener(this);
		Button updateBtn = (Button) findViewById(R.id.update);
		updateBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout: // 退出登录
			finish();
			break;
		case R.id.add:// 添加员工信息
			startActivity(new Intent(getApplicationContext(), AddActivity.class));
			break;
		case R.id.query: // 查询员工信息
			startActivity(new Intent(getApplicationContext(),
					QueryActivity.class));
			break;
		case R.id.delete: // 删除员工信息
			startActivity(new Intent(getApplicationContext(),
					DeleteActivity.class));
			break;
		case R.id.update: // 修改员工信息
			startActivity(new Intent(getApplicationContext(),
					ModifyActivity.class));
			break;

		default:
			break;
		}

	}

}
