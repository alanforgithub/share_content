package com.alan.demo;

/**
 * 员工信息
 * 
 * @author guangbingw
 * 
 */
public class Staff {

	private String name;
	private int age;

	// 原始名称
	private String orginName;

	public Staff(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOrginName() {
		return orginName;
	}

	public void setOrginName(String orginName) {
		this.orginName = orginName;
	}

	@Override
	public String toString() {
		return "Staff [name=" + name + ", age=" + age + "]";
	}

}
