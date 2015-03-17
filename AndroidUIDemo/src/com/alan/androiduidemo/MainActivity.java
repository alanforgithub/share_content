package com.alan.androiduidemo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	private static final String TAG = "android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i(TAG, "onCreate");
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,TextActivity.class);
				startActivity(intent);
				
//				AlertDialog.Builder builder = new Builder(MainActivity.this);
//			 builder.setMessage("确认退出吗？");
//			  builder.setTitle("提示");
//			 builder.setPositiveButton("确认", new OnClickListener() {
//			　　   @Override
//			　　   public void onClick(DialogInterface dialog, int which) {
//			　　    dialog.dismiss();
//			　　    MainActivity.this.finish();
//			　　   }
//			　　  });
//			　　  builder.setNegativeButton("取消", new OnClickListener() {
//			　　   @Override
//			　　   public void onClick(DialogInterface dialog, int which) {
//			　　    dialog.dismiss();
//			　　   }
//			　　  });
//			　　  builder.create().show();
//			　　 
//				
				
			}
		});
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e(TAG, "onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e(TAG, "onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}

}
