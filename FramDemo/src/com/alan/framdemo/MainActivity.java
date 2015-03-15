package com.alan.framdemo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private AnimationDrawable animation;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		image = (ImageView) findViewById(R.id.image);
		image.setBackgroundResource(R.anim.frame);

		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (animation == null) {
					animation = (AnimationDrawable) image.getBackground();
					
				} else {
					animation.stop(); // 停止
				}

				animation.start(); // 开启动画

			}
		});

	}

}
