package com.alan.androiddemo;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

	private NotificationManager mNotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button serviceBtn = (Button) findViewById(R.id.service);
		serviceBtn.setOnClickListener(this);

		Button layoutBtn = (Button) findViewById(R.id.layout);
		layoutBtn.setOnClickListener(this);

		findViewById(R.id.notify).setOnClickListener(this);

		findViewById(R.id.toast).setOnClickListener(this);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	}

	@Override
	public void onClick(View view) {

		Intent intent = null;

		switch (view.getId()) {
		case R.id.service:
			intent = new Intent(this, ServiceActiviy.class);
			break;
		case R.id.layout:
			intent = new Intent(this, FiveLayoutActivity.class);
			break;
		case R.id.notify:
			notification();
			break;
		case R.id.toast:
			showToast();
			break;

		default:
			break;
		}

		if (null != intent) {
			startActivity(intent);
		}
	}

	/**
	 * 显示toast
	 */
	private void showToast() {
		if (null == mToast) {
			mToast = Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT);
		} else {
			mToast.setText("toast");
		}
		
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
		
	}

	private int id = 1;
	private Toast mToast;

	private void notification() {

		android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(
				MainActivity.this);

		builder.setTicker("雾霾预警"); // 提示时但未下来时的显示
		builder.setContentTitle("注意");
		builder.setContentText("今天是严重雾霾" + id);

		builder.setSmallIcon(R.drawable.arrow);
		builder.setWhen(Calendar.getInstance().getTimeInMillis());

		Intent intentss = new Intent(MainActivity.this, ServiceActiviy.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				MainActivity.this, 100, intentss, 0);
		builder.setContentIntent(pendingIntent); // 设置Notification的点击事件

		id++;
		Notification notification = builder.build();
		mNotificationManager.notify(11000, notification); // 如果id一致就不会重复去显示

	}

}
