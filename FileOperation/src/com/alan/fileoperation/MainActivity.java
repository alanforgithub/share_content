package com.alan.fileoperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private static final String TAG = "android"; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button assetsBtn = (Button) findViewById(R.id.read_assets_file);
        assetsBtn.setOnClickListener(this);
        
        Button rawBtn = (Button) findViewById(R.id.read_raw_file);
        rawBtn.setOnClickListener(this);
        
        Button innerBtn = (Button) findViewById(R.id.read_inner_file);
        innerBtn.setOnClickListener(this);
        
        Button outerBtn  = (Button) findViewById(R.id.read_outer_file);
        outerBtn.setOnClickListener(this);
        
        Button shareBtn  = (Button) findViewById(R.id.SharePreference);
        shareBtn.setOnClickListener(this);
        
        Button sqliteBtn = (Button) findViewById(R.id.sqliteBtn);
        sqliteBtn.setOnClickListener(this);
    }

    /**
     * ������assets������������������
     */
    private void readAssetsContent(){
    	try {
			InputStream is = getResources().getAssets().open("myAssetsFile.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);
			String line ="";
			while ((line = bfr.readLine()) != null) {
				Log.i(TAG, ""+line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void readRawContent(){
    	
    	
    	InputStream is = getResources().openRawResource(R.raw.my_raw_file);
    	
    	InputStreamReader isr = new InputStreamReader(is);
    	BufferedReader bfr = new BufferedReader(isr);
    	String line = "";
    	try {
			while ((line = bfr.readLine()) != null) {
				Log.i(TAG, line);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.read_assets_file:
			
			readAssetsContent();
			
			break;
			
		case R.id.read_raw_file:
			
			
			readRawContent();
			break;
			
		case R.id.read_inner_file:
			Intent intent = new Intent(this,ReadWriteInnerData.class);
			startActivity(intent);
			break;
		case R.id.read_outer_file:
			Intent intent1 = new Intent(this,ReadWriterOutDataActivity.class);
			startActivity(intent1);
			break;

		case R.id.SharePreference:
			
			Intent intent2 = new Intent(this,SharePreferenceActivity.class);
			startActivity(intent2);
			break;
			
		case R.id.sqliteBtn:
			Intent intent3 = new Intent(this,SQLiteActivity.class);
			startActivity(intent3);
			//this.overridePendingTransition(R.anim.rotate, R.anim.translate);
			break;
			
		default:
			break;
		}		
	}
}
