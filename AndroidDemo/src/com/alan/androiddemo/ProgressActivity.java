package com.alan.androiddemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ProgressActivity extends Activity {

	private int progress = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);

		ProgressBar circleProgress = (ProgressBar) findViewById(R.id.progressBar1);
		lineProgress = (ProgressBar) findViewById(R.id.progressBar2);
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

		lineProgress.setMax(100);
		lineProgress.setProgress(progress);
		handler.sendEmptyMessageDelayed(100, 30);

		seekBar.setMax(100);
		seekBar.setProgress(progress);
		seekBar.setThumb(getResources().getDrawable(R.drawable.ic_launcher));

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
		
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setTitle("加载中");
		

		findViewById(R.id.progressDialog).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						
						mProgressDialog.show();
					}
				});

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			progress += 1;
			lineProgress.setProgress(progress);

			if (progress < 100) {
				handler.sendEmptyMessageDelayed(100, 30);
			}
		}
	};

	private ProgressBar lineProgress;

	private ProgressDialog mProgressDialog;
}
