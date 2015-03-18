package com.alan.androiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class FrameLayoutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_layout);
		
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("android", "button1");
				
			}
		});
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("android", "button2");
				
			}
		});
		
	}

}
