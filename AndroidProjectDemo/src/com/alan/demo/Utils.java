package com.alan.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utils {

	public static String spName = "myandroid";
	public static String loginName = "userName";
	public static String password = "password";

	public static void saveUserInfo(UserInfo userInfo, Context context) {

		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(loginName, userInfo.getUserName());
		editor.putString(password, userInfo.getUserPassword());

		editor.commit();
	}

	public static UserInfo getUserInfo(Context context) {
		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(sp.getString(loginName, null));
		userInfo.setUserPassword(sp.getString(password, null));
		return userInfo;
	}

}
