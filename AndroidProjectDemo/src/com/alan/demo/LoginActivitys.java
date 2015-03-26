package com.alan.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivitys extends Activity {

	private Button loginBtn;
	private EditText userNameEdit;
	private EditText userPasswordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_layout);

		initViews();

	}

	private void initViews() {

		loginBtn = (Button) findViewById(R.id.login);
		userNameEdit = (EditText) findViewById(R.id.user_name);
		userPasswordEdit = (EditText) findViewById(R.id.user_password);
		
		UserInfo userInfo = Utils.getUserInfo(getApplicationContext());
		if(null != userInfo.getUserName() && null != userInfo.getUserPassword()){
			userNameEdit.setText(userInfo.getUserName());
			userPasswordEdit.setText(userInfo.getUserPassword());
//			startActivity(new Intent(LoginActivitys.this,
//					HomeActivity.class));
//			return;
		}

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String name = userNameEdit.getText().toString();
				String password = userPasswordEdit.getText().toString();

				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
					Toast.makeText(LoginActivitys.this, "用户名或密码为空",
							Toast.LENGTH_SHORT).show();
					return;
				}

				UserInfo userInfo = new UserInfo(name, password);
				Utils.saveUserInfo(userInfo, getApplicationContext());

				startActivity(new Intent(LoginActivitys.this,
						HomeActivity.class));

			}
		});

	}
}
