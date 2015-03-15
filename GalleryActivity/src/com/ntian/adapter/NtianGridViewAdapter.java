package com.ntian.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.android.gallery3d.R;
import com.ntian.listener.OnOptionItemClickListener;
import com.ntian.model.OptionButton;
import com.ntian.utils.ToastUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class NtianGridViewAdapter extends BaseAdapter {
	Context mContext;
	List<OptionButton> mOptionList;
	private LayoutInflater mInflater;

	public NtianGridViewAdapter(Context context, List<OptionButton> optionList) {
		this.mContext = context;
		this.mOptionList = optionList;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mOptionList.size();
	}

	@Override
	public Object getItem(int position) {
		return mOptionList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final OptionButton holder;
		if (null == convertView) {
			holder = new OptionButton();
			convertView = mInflater.inflate(
					R.layout.ntian_option_gridview_item, null);
			holder.button = (ImageButton) convertView
					.findViewById(R.id.ntian_option_icon);
			holder.textView = (TextView) convertView
					.findViewById(R.id.ntian_option_name);
			convertView.setTag(holder);
		} else {
			holder = (OptionButton) convertView.getTag();
		}

		final OptionButton option = mOptionList.get(position);

		holder.optionName = option.optionName;
		holder.drawableId = option.drawableId;
		holder.textView.setText(option.optionName);
		holder.mSwitch = option.mSwitch;
		if (holder.mSwitch) {
			holder.button.setBackgroundResource(option.selectDrawableId);
		} else {
			holder.button.setBackgroundResource(option.drawableId);
		}
		holder.optionId = option.optionId;

		holder.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (null != mOnOptionItemClickListener) {
					mOnOptionItemClickListener.optionClick(holder);
				}
			}
		});

		return convertView;
	}

	private OnOptionItemClickListener mOnOptionItemClickListener;

	/**
	 * 注册选项卡中的点击事件
	 * 
	 * @param mOnOptionItemClickListener
	 */
	public void setOnOptionItemClickListener(
			OnOptionItemClickListener mOnOptionItemClickListener) {
		this.mOnOptionItemClickListener = mOnOptionItemClickListener;
	}

}
