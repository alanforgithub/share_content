package com.ntian.model;

import android.widget.ImageButton;
import android.widget.TextView;

public class OptionButton {

	public int optionId;
	
	/**
	 * 选项中的名称
	 */
	public String optionName;
	/**
	 * 选项中的button图片id
	 */
	public int drawableId;
	
	/**
	 * 选中时的button图片ID
	 */
	public int selectDrawableId;

	/**
	 * button
	 */
	public ImageButton button;
	
	/**
	 * textView
	 */
	public TextView textView;
	
	/**
	 * 当前开关状态
	 */
	public boolean mSwitch;
	

	@Override
	public String toString() {
		return "OptionButton [optionName=" + optionName + ", drawableId="
				+ drawableId + "]";
	}
}
