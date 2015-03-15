package com.alan.fileoperation;

import java.util.List;

import com.alan.fileoperation.db.Person;
import com.alan.fileoperation.db.PersonDao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SQLiteActivity extends Activity implements OnClickListener {

	private EditText nameEdit;
	private EditText ageEdit;
	private PersonDao mPersionDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);

		nameEdit = (EditText) findViewById(R.id.nameEdit);
		ageEdit = (EditText) findViewById(R.id.ageEdit);

		Button insertBtn = (Button) findViewById(R.id.insert);
		insertBtn.setOnClickListener(this);
		Button deleteBtn = (Button) findViewById(R.id.delete);
		deleteBtn.setOnClickListener(this);
		Button updateBtn = (Button) findViewById(R.id.update);
		updateBtn.setOnClickListener(this);
		Button queryBtn = (Button) findViewById(R.id.query);
		queryBtn.setOnClickListener(this);

		mPersionDao = new PersonDao(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.insert:
			long id = mPersionDao
					.insert(new Person(0, nameEdit.getText().toString(),
							Integer.parseInt(ageEdit.getText().toString())));
			Log.i("android", "插入成功=" + id);
			break;

		case R.id.delete:

			int raw = mPersionDao.delete(nameEdit.getText().toString());
			Log.i("android", "删除成功 = " + raw);
			break;
		case R.id.update:
			int raws = mPersionDao
					.update(new Person(0, nameEdit.getText().toString(),
							Integer.parseInt(ageEdit.getText().toString())));
			Log.i("android", "更新成功 = " + raws);
			break;
		case R.id.query:

			List<Person> persions = mPersionDao.query(nameEdit.getText()
					.toString());
			Log.i("android", "persion = " + persions);

			break;

		default:
			break;
		}
	}

}
