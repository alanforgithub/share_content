package com.alan.androiddemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alan.androiddemo.view.ScaleImage;

public class ScaleImageActivity extends Activity {
	private ImageView scaleImage;

	private float imgWidth = 0;
	private float imgHeight = 0;
	private float degree = 0;
	private Matrix matrix = new Matrix();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scale_image);

		// 获取当前屏幕的尺寸，并设置图片放大的最大尺寸，不能超过屏幕尺寸
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		scaleImage = (ImageView) findViewById(R.id.scaleImage);
		scaleImage.setImageResource(R.drawable.ic_launcher);

		imgWidth = scaleImage.getWidth();
		imgHeight = scaleImage.getHeight();

		findViewById(R.id.scaleBtn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				imgWidth += 3;
				imgHeight += 3;

				scaleImage.setLayoutParams(new LinearLayout.LayoutParams(
						(int) imgWidth, (int) imgHeight));

			}
		});

		findViewById(R.id.rotateBtn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取图片
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher);
				// 设置旋转的角度
				degree += 10;
				matrix.setRotate(degree);

				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
						bitmap.getHeight(), matrix, true);

				scaleImage.setImageBitmap(bitmap);

			}
		});

	}

}
