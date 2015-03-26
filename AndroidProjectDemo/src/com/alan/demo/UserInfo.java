package com.alan.demo;

public class UserInfo {

	public UserInfo() {

	}

	public UserInfo(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}

	private String userName;
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", userPassword="
				+ userPassword + "]";
	}

}
