package com.alan.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class CommonViewActivity extends Activity {
	private TextView text;
	private float textSize = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_view);

		text = (TextView) findViewById(R.id.text);

		textSize = text.getTextSize();

		findViewById(R.id.changeBig).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textSize += 3;
				text.setTextSize(textSize);
			}
		});
		findViewById(R.id.changeSmall).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						textSize -= 3;
						text.setTextSize(textSize);
					}
				});

		findViewById(R.id.ImageScale).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CommonViewActivity.this,
						ScaleImageActivity.class);
				startActivity(intent);

			}
		});
		
		Switch switchView = (Switch) findViewById(R.id.switch_view);
		switchView.setText("开关");
		switchView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
			}
		});
		
		findViewById(R.id.progressBar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CommonViewActivity.this,
						ProgressActivity.class);
				startActivity(intent);
				
			}
		});
		
		findViewById(R.id.timePicker).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CommonViewActivity.this,
						DateTimerPicker.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.listViewBtn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CommonViewActivity.this,
						ListViewActivity.class);
				startActivity(intent);
			}
		});

	}

}
