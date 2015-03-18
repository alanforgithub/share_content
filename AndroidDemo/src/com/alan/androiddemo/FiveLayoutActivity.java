package com.alan.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FiveLayoutActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);

		Button mLinearLayoutBtn = (Button) findViewById(R.id.layout_Linear);
		mLinearLayoutBtn.setOnClickListener(this);
		Button mRelativeLayoutBtn = (Button) findViewById(R.id.layout_Relative);
		mRelativeLayoutBtn.setOnClickListener(this);
		Button mFrameLayoutBtn = (Button) findViewById(R.id.layout_Frame);
		mFrameLayoutBtn.setOnClickListener(this);
		Button mTableLayoutBtn = (Button) findViewById(R.id.layout_Table);
		mTableLayoutBtn.setOnClickListener(this);
		Button mAbsoluteLayoutBtn = (Button) findViewById(R.id.layout_Absolute);
		mAbsoluteLayoutBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {

		Intent intent = null;

		switch (view.getId()) {
		case R.id.layout_Linear:
			intent = new Intent(FiveLayoutActivity.this,
					LinearLayoutActivity.class);
			break;
		case R.id.layout_Relative:
			intent = new Intent(FiveLayoutActivity.this,
					RelativeLayoutActivity.class);
			break;
		case R.id.layout_Frame:
			intent = new Intent(FiveLayoutActivity.this,
					FrameLayoutActivity.class);
			break;
		case R.id.layout_Table:
			intent = new Intent(FiveLayoutActivity.this,
					TableLayoutActivity.class);
			break;
		case R.id.layout_Absolute:
			break;

		default:
			break;
		}

		startActivity(intent);

	}

}
