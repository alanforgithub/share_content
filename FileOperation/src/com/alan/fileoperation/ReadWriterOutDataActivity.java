package com.alan.fileoperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReadWriterOutDataActivity extends Activity {
	
	private EditText editText;
	private static final String fileName = "test.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_writer_out_data);
		
		
		editText = (EditText) findViewById(R.id.editText);
		Button saveBtn = (Button) findViewById(R.id.save_data);
		Button readBtn = (Button) findViewById(R.id.read_data);
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
					Toast.makeText(getApplicationContext(), "当前系统不具备sdcard目录", 0).show();
					return;
				}
				
				File sdCard = Environment.getExternalStorageDirectory();
				
				File myFile = new File(sdCard, fileName);
			
				try {
					FileOutputStream fos = new FileOutputStream(myFile);
					
					fos.write(editText.getText().toString().getBytes());
					fos.close();
					Toast.makeText(getApplicationContext(), "写入完成", 0).show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		readBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					
					File sdCard = Environment.getExternalStorageDirectory();
					File myFile = new File(sdCard,fileName);
					FileInputStream fis = new FileInputStream(myFile);
					InputStreamReader isr = new InputStreamReader(fis);
					
					char[] input = new char[fis.available()];
					
					isr.read(input);
					isr.close();
					fis.close();
					
					Toast.makeText(getApplicationContext(), ""+String.valueOf(input), 0).show();
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		});

		
		
		
	}

}
