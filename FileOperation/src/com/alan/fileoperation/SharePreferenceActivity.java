package com.alan.fileoperation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SharePreferenceActivity extends Activity {

	private EditText name;
	private EditText email;
	private EditText number;
	private SharedPreferences share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharepreference);
		
		//得到sharePreferences对象
		share = getSharedPreferences("share",  Context.MODE_PRIVATE);
		name = (EditText) findViewById(R.id.name);
		number = (EditText) findViewById(R.id.number);
		email = (EditText) findViewById(R.id.email);
		
		name.setText(share.getString("name", ""));
		number.setText(share.getString("number", ""));
		email.setText(share.getString("email", ""));
		
		
		
		
		Button saveBtn = (Button) findViewById(R.id.button);
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				saveData();
			}
		});
	}
	
	/**
	 * 保存数据
	 */
	private void saveData(){
		String nameStr = name.getText().toString();
		String numberStr = number.getText().toString();
		String emailStr = email.getText().toString();
		
		Editor editor = share.edit();   //得到编辑器
		editor.putString("name", nameStr);   //存储数据
		editor.putString("number", numberStr);
		editor.putString("email", emailStr);
		
		editor.commit();   //提交修改		
		
	}
	
}
