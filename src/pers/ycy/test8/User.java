package com.ycy.test8;

public class User {
	
	private String name;
	private String password;
	private String nickName ;
	private String loginNum ;
	
	
	public User(String name, String password, String nickName, String loginNum) {
		super();
		this.name = name;
		this.password = password;
		this.nickName = nickName;
		this.loginNum = loginNum;
	}
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", nickName=" + nickName + ", loginNum=" + loginNum
				+ "]";
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(String loginNum) {
		this.loginNum = loginNum;
	}
	
	
	

}
