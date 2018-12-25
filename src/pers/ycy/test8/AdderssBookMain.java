package com.ycy.test8;

import java.util.LinkedList;

public class AdderssBookMain {
	static JDBCAddressConnection jdbc;
	public static String queryAll = "select * from adressUser;";
	public static LinkedList<AddressBook> aderss = null;

	public static void main(String[] args) {

		jdbc = new JDBCAddressConnection();
		showAllInfo();
		addAddress("Mass", "男", "13024171180", 87506618, "913005064@qq.com");
		addAddress("Alexs", "男", "18166334401", 913005054, "913005054@qq.com");
		showFindByName("Alexs");
		deleteAddress("Alexs", "18166334401");
		updataAddress("杨成杰", "女", "15330306873", 252816581, "252816581@qq.com", "15823954819");
		System.out.println("---操作结束后通讯录的全部信息---");
		showAllInfo();

	}

	/**
	 * @param name    需要更改的姓名
	 * @param sex     需要更改的性别
	 * @param phone   需要更改的手机号
	 * @param qq      需要更改的qq
	 * @param email   需要更改的邮箱
	 * @param updatePhone   根据该号码进行更新
	 */
	private static void updataAddress(String name, String sex, String phone, int qq, String email, String updatePhone) {
		try {
			jdbc.update(name, sex, phone, qq, email, updatePhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param name    需要删除的姓名
	 * @param phone   需要删除的手机号
	 */
	private static void deleteAddress(String name, String phone) {

		jdbc.deleteAdderssByNameAndPhone(name, phone);
		System.out.println("---已经删除名称为*" + name + "* 号码为*" + phone + "*的通讯录信息---");
	}

	/**
	 * @param name    通过姓名查找通讯录信息
	 */
	private static void showFindByName(String name) {

		LinkedList<AddressBook> adderss = jdbc.queryAccountByName(name);
		System.out.println("\n" + "---以下是查询*" + name + "*的通讯录信息---" + "\n");
		for (AddressBook addressBook : adderss) {
			System.out.println(addressBook.toString() + "\n");
		}

	}

	/**
	 * 以下为添加的信息的变量
	 * @param name    
	 * @param sex
	 * @param phone
	 * @param qq
	 * @param email
	 */
	private static void addAddress(String name, String sex, String phone, int qq, String email) {

		LinkedList<AddressBook> aderss = jdbc.queryAccountByPhone(phone);
		if (aderss.size() == 0) {
			try {
				System.out.println("\n" + "新的通讯联系人已经存入" + "\n");
				System.out.println(name + " " + sex + "  " + phone + " " + qq + "  " + email + "  ");
				jdbc.addAcccount(name, sex, phone, qq, email);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("AdderssBookMain.addAddress()");
			}
		} else {
			for (AddressBook addressBook : aderss) {
				System.out.println("\n" + "手机号码已经存在，它存入的名称为：" + addressBook.getName());
				break;
			}
		}
		aderss.clear();
	}

	/**
	 * 展示数据库中全部的通信录信息
	 */
	private static void showAllInfo() {

		LinkedList<AddressBook> adderss = jdbc.getContent();
		System.out.println("---以下是通讯录全部信息,目前有" + adderss.size() + "条内容---\n");
		for (AddressBook adressBook : adderss) {
			System.out.println(adressBook.toString());
		}

	}

}
