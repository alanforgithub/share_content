package com.alan.androiddemo;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class DateTimerPicker extends Activity {
	private TimePicker timePicker;
	private DatePicker datePicker;
	private Calendar mCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_time);

		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		mCalendar = Calendar.getInstance();
		initTimePicker();
		initDatePicker();

	}

	private void initTimePicker() {
		timePicker.setIs24HourView(true); // 是否为24小时
		timePicker.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY)); // 设置当前小时
		timePicker.setCurrentMinute(mCalendar.get(Calendar.MINUTE)); // 设置当前分钟
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initDatePicker() {
		datePicker.init(mCalendar.get(Calendar.YEAR),
				mCalendar.get(Calendar.MONTH),
				mCalendar.get(Calendar.DAY_OF_MONTH),
				new OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub

					}
				});
		
	}

}
