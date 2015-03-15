package com.alan.androiddemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button serviceBtn = (Button) findViewById(R.id.service);
        serviceBtn.setOnClickListener(this);
        
        Button layoutBtn = (Button) findViewById(R.id.layout);
        layoutBtn.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View view) {

		Intent intent = null;
		
		switch (view.getId()) {
		case R.id.service:
			 intent = new Intent(this,ServiceActiviy.class);
			break;
		case R.id.layout:
			 intent = new Intent(this,FiveLayoutActivity.class);
			break;

		default:
			break;
		}
		
		startActivity(intent);
		
	}
    
}
