package com.alan.demo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.alan.demo.R;
import com.alan.demo.Staff;

public class StaffListAdapter extends BaseAdapter {

	private Context context;
	private List<Staff> staffList;
	private LayoutInflater mInflater;

	public StaffListAdapter(Context context, List<Staff> staffList) {
		this.context = context;
		this.staffList = staffList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return staffList.size();
	}

	@Override
	public Object getItem(int position) {
		return staffList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_staff_list_layout,
					null);
			holder.staffName = (TextView) convertView
					.findViewById(R.id.item_staff_name);
			holder.staffAge = (TextView) convertView
					.findViewById(R.id.item_staff_age);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Staff staff = staffList.get(position);
		holder.staffName.setText(staff.getName());
		holder.staffAge.setText(String.valueOf(staff.getAge()));

		return convertView;
	}

	class ViewHolder {
		TextView staffName;
		TextView staffAge;
	}

}
