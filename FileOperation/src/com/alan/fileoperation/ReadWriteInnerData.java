package com.alan.fileoperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReadWriteInnerData extends Activity {
	
	private EditText editText;
	private static final String fileName = "test";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_writer_inner_data);
		
		editText = (EditText) findViewById(R.id.editText);
		Button saveBtn = (Button) findViewById(R.id.save_data);
		Button readBtn = (Button) findViewById(R.id.read_data);
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				try {
					FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
					
					fos.write(editText.getText().toString().getBytes());
					
					fos.close();
				
					Toast.makeText(getApplicationContext(), "写入完成", 0).show();
				
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (Exception e) {
				}
				
			}
		});
		
		readBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					FileInputStream fis = openFileInput(fileName);
					
					InputStreamReader is = new InputStreamReader(fis, "UTF-8");
					
					char input[] = new char[fis.available()];
					is.read(input);
					is.close();
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
