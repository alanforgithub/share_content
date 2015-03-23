package com.alan.androiddemo.adapter;

import java.util.List;

import com.alan.androiddemo.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	private List<String> list;
	private Context context;
	private LayoutInflater mInflater;

	public MyBaseAdapter(List<String> list, Context context) {
		this.list = list;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		
		Log.i("android", "item = "+list.get(position));
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if(convertView == null){
			
			holder = new ViewHolder();
			
			convertView = mInflater.inflate(R.layout.item_listview_layout, null);
			holder.textTv = (TextView) convertView.findViewById(R.id.itemTV);
			
			convertView.setTag(holder);
			
		}else{
			
			holder = (ViewHolder) convertView.getTag();
			
		}

		

		holder.textTv.setText(list.get(position));

		return convertView;
	}

	class ViewHolder {
		TextView textTv;
	}

}
