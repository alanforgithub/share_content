package com.alan.visitecontentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	 Uri uri = Uri.parse("content://com.alan.provide");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Cursor cursor = getContentResolver().query(uri, null, null, null, null);
				
				if(null == cursor){
					Toast.makeText(getApplicationContext(), "cursor为空", 0).show();
				}
				
				while(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("name"));
					int age = cursor.getInt(cursor.getColumnIndex("age"));
					Log.i("android", "name = "+name+",age = "+age);
				}
				
			}
		});
    }

    
}
