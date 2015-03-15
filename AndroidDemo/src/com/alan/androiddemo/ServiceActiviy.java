package com.alan.androiddemo;

import com.alan.androiddemo.service.ServiceDemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActiviy extends Activity implements OnClickListener ,ServiceConnection{

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		Button startService = (Button) findViewById(R.id.startService);
		startService.setOnClickListener(this);
		Button stopService = (Button) findViewById(R.id.stopService);
		stopService.setOnClickListener(this);
		Button bindService = (Button) findViewById(R.id.bindService);
		bindService.setOnClickListener(this);
		Button unBindService = (Button) findViewById(R.id.unBindService);
		unBindService.setOnClickListener(this);
		
		Button getNumBtn = (Button) findViewById(R.id.getNum);
		getNumBtn.setOnClickListener(this);
		
		intent = new Intent(this,ServiceDemo.class);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.startService:
			startService(intent);
			break;
		case R.id.stopService:
			stopService(intent);
			break;
		case R.id.bindService:
			//第二个参数用于监听service的状态
			bindService(intent, this, Service.BIND_AUTO_CREATE);
			break;
		case R.id.unBindService:
			unbindService(this);
			break;
			
		case R.id.getNum:
			
			Log.i("android", "当前服务中的数字 = "+mServiceDemo.getNum());
			
			break;

		default:
			break;
		}
	}
	
	private ServiceDemo mServiceDemo = null;

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		// TODO Auto-generated method stub
		
		Log.i("android", "onServiceConnected");
		
		mServiceDemo = ((ServiceDemo.MineServiceBinder)binder).getService();
		
	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {// 在service崩溃的时候调用
		// TODO Auto-generated method stub
		Log.i("android", "onServiceDisconnected");

		
	}
}
