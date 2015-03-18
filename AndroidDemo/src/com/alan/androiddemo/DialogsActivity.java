package com.alan.androiddemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class DialogsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogs);

		findViewById(R.id.dialog1).setOnClickListener(this);
		findViewById(R.id.dialog2).setOnClickListener(this);
		findViewById(R.id.dialog3).setOnClickListener(this);
		findViewById(R.id.dialog4).setOnClickListener(this);
		findViewById(R.id.dialog5).setOnClickListener(this);
		findViewById(R.id.dialog6).setOnClickListener(this);
		findViewById(R.id.dialog7).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog1:
			noBtnDialog();
			break;
		case R.id.dialog2:
			oneBtnDialog();
			break;
		case R.id.dialog3:
			twoBtnDialog();
			break;
		case R.id.dialog4:
			listDialog();
			break;
		case R.id.dialog5:
			multipleDialog();
			break;
		case R.id.dialog6:
			sigleChoiceDialog();
			break;
		case R.id.dialog7:
			customDialog();
			break;

		default:
			break;
		}

	}

	/**
	 * 自定义dialog
	 */
	private void customDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_custom_dialog, null);
		builder.setView(view);
		AlertDialog alertDialog = builder.create();

		alertDialog.show();
	}

	private void noBtnDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("您有一条消息");
		AlertDialog alertDialog = builder.create();

		alertDialog.show();
	}

	private void oneBtnDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("您有一条新消息");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		builder.create().show();
	}

	private void twoBtnDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("您有一条新消息");
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});

		builder.setNeutralButton("清除", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});

		builder.create().show();
	}

	private String[] colors = new String[] { "白色", "红色", "黄色" };
	private boolean[] isChoice = new boolean[] { false, true, false };

	private void listDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setItems(colors, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});
		builder.create().show();
	}

	private void multipleDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMultiChoiceItems(colors, isChoice,
				new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {

						Log.i("android", "item = " + colors[which]
								+ ",isChecked = " + isChecked);

					}
				});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		builder.create().show();
	}

	private void sigleChoiceDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setSingleChoiceItems(colors, 2,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.i("android", "colors = " + colors[which]);

					}
				});
		builder.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				Log.i("android", "cancel");
			}
		});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});

		builder.create().show();

	}

}
