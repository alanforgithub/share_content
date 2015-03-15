package com.alan.androiddemo.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServiceDemo extends Service {

	/**
	 * 内部类，用于和外部数据交换
	 * @author luomengxin
	 *
	 */
	public class MineServiceBinder extends Binder{
		public ServiceDemo getService(){
			return ServiceDemo.this;
		}
	}
	
	/**
	 * 把number当前值提供给外界
	 * @return
	 */
	public int getNum(){
		return number;
	}
	
	private final MineServiceBinder serviceBinder = new MineServiceBinder();
	
	private int number = 0;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		Log.i("android", "onBind");
		
		return serviceBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("android", "onUnbind");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		Log.i("android", "onCreate");
		// TODO Auto-generated method stub
		super.onCreate();
		
		startTimer();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i("android", "onStart");
		super.onStart(intent, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("android", "onDestroy");
		super.onDestroy();
		stopTimer();
	}
	

	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	
	public void startTimer(){
		if(null == mTimer){
			mTimer = new Timer();
			mTimerTask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					number ++;
					Log.i("android", "number = "+number);
				}
			};
			mTimer.schedule(mTimerTask, 1000, 1000);
		}
	}
	
	public void stopTimer(){
		if(null != mTimer){
			mTimerTask.cancel();
			mTimer.cancel();
			mTimerTask = null;
			mTimer = null;
		}
	}
	
}
