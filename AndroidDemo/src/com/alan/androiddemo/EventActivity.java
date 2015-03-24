package com.alan.androiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class EventActivity extends Activity implements OnClickListener {

	private ImageView imgView;
	private LinearLayout linearLayout;

	private float currentDistance;
	private float lastDistance = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_layout);

		imgView = (ImageView) findViewById(R.id.image_view);

		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		linearLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					Log.i("android", "down = x = " + event.getX() + ", rawX = "
							+ event.getRawX());

					break;
				case MotionEvent.ACTION_MOVE:
					Log.i("android",
							"move pointerCount = " + event.getPointerCount());

					if (event.getPointerCount() >= 2) {

						float offsetX = event.getX(0) - event.getX(1);
						float offsetY = event.getY(0) - event.getY(1);

						currentDistance = (float) Math.sqrt(offsetX * offsetX
								+ offsetY * offsetY);

						if (lastDistance < 0) {
							lastDistance = currentDistance;
						} else {
							LayoutParams param = (LayoutParams) imgView.getLayoutParams();
							
							if ((currentDistance - lastDistance) > 5   ) { //容错差值为5
								// 放大
								param.width = (int) (imgView.getWidth() * 1.1);
								param.height = (int) (imgView.getHeight() * 1.1);
								
							} else if ((lastDistance - currentDistance) > 5) {
								// 缩小
								param.width = (int) (imgView.getWidth() * 0.9);
								param.height = (int) (imgView.getHeight() * 0.9);
							}
							
							if(param.width <=10){
								param.width = 10;
							}

							if (param.height <=10){
								param.height = 10;
							}
							
							Log.i("android", "width = "+param.width+",,height = "+param.height);
							Log.i("android", "imgView width = "+imgView.getWidth()+",,imgView height = "+imgView.getHeight());
							
							imgView.setLayoutParams(param);
							
							
							lastDistance = currentDistance;
							
						}

					}

					// Log.i("android",
					// "move x1 = " + event.getX(0) + ", y1 = "
					// + event.getY(0) + ", x2 = "
					// + event.getX(1) + ", y2 = "
					// + event.getY(1));

					// LayoutParams param = (LayoutParams) imgView
					// .getLayoutParams();
					// param.leftMargin = (int) event.getX();
					// param.topMargin = (int) event.getY();
					// imgView.setLayoutParams(param);

					break;
				case MotionEvent.ACTION_UP:
					Log.i("android", "up");
					break;

				default:
					break;
				}

				return true;
			}
		});

	}

	@Override
	public void onClick(View v) {

	}

}
