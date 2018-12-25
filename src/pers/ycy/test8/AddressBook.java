package com.ycy.test8;

public class AddressBook {

	private String name;
	private String sex;
	private String phone;
	private int qq;
	private String email;

	public AddressBook(String name, String sex, String phone, int qq, String email) {
		super();
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
	}

	@Override
	public String toString() {
		return name + " " + sex + "  " + phone + " " + qq + "  " + email + "  ";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
