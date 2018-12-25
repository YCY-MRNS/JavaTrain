package com.ycy.test8;

import java.util.LinkedList;

public class AdderssBookMain {
	static JDBCAddressConnection jdbc;
	public static String queryAll = "select * from adressUser;";
	public static LinkedList<AddressBook> aderss = null;

	public static void main(String[] args) {

		jdbc = new JDBCAddressConnection();
		showAllInfo();
		addAddress("Mass", "��", "13024171180", 87506618, "913005064@qq.com");
		addAddress("Alexs", "��", "18166334401", 913005054, "913005054@qq.com");
		showFindByName("Alexs");
		deleteAddress("Alexs", "18166334401");
		updataAddress("��ɽ�", "Ů", "15330306873", 252816581, "252816581@qq.com", "15823954819");
		System.out.println("---����������ͨѶ¼��ȫ����Ϣ---");
		showAllInfo();

	}

	/**
	 * @param name    ��Ҫ���ĵ�����
	 * @param sex     ��Ҫ���ĵ��Ա�
	 * @param phone   ��Ҫ���ĵ��ֻ���
	 * @param qq      ��Ҫ���ĵ�qq
	 * @param email   ��Ҫ���ĵ�����
	 * @param updatePhone   ���ݸú�����и���
	 */
	private static void updataAddress(String name, String sex, String phone, int qq, String email, String updatePhone) {
		try {
			jdbc.update(name, sex, phone, qq, email, updatePhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param name    ��Ҫɾ��������
	 * @param phone   ��Ҫɾ�����ֻ���
	 */
	private static void deleteAddress(String name, String phone) {

		jdbc.deleteAdderssByNameAndPhone(name, phone);
		System.out.println("---�Ѿ�ɾ������Ϊ*" + name + "* ����Ϊ*" + phone + "*��ͨѶ¼��Ϣ---");
	}

	/**
	 * @param name    ͨ����������ͨѶ¼��Ϣ
	 */
	private static void showFindByName(String name) {

		LinkedList<AddressBook> adderss = jdbc.queryAccountByName(name);
		System.out.println("\n" + "---�����ǲ�ѯ*" + name + "*��ͨѶ¼��Ϣ---" + "\n");
		for (AddressBook addressBook : adderss) {
			System.out.println(addressBook.toString() + "\n");
		}

	}

	/**
	 * ����Ϊ��ӵ���Ϣ�ı���
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
				System.out.println("\n" + "�µ�ͨѶ��ϵ���Ѿ�����" + "\n");
				System.out.println(name + " " + sex + "  " + phone + " " + qq + "  " + email + "  ");
				jdbc.addAcccount(name, sex, phone, qq, email);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("AdderssBookMain.addAddress()");
			}
		} else {
			for (AddressBook addressBook : aderss) {
				System.out.println("\n" + "�ֻ������Ѿ����ڣ������������Ϊ��" + addressBook.getName());
				break;
			}
		}
		aderss.clear();
	}

	/**
	 * չʾ���ݿ���ȫ����ͨ��¼��Ϣ
	 */
	private static void showAllInfo() {

		LinkedList<AddressBook> adderss = jdbc.getContent();
		System.out.println("---������ͨѶ¼ȫ����Ϣ,Ŀǰ��" + adderss.size() + "������---\n");
		for (AddressBook adressBook : adderss) {
			System.out.println(adressBook.toString());
		}

	}

}
