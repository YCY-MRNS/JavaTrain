package com.ycy.test8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JDBCAddressConnection {
	// private static String create = "CREATE TABLE adressUser (name VARCHAR (8)
	// PRIMARY KEY NOT NULL,sex VARCHAR (1) NOT NULL, phone INTEGER (11),qq
	// INTEGER (13),email VARCHAR (20));";
	
	//�������ݿ��������ַ
	private static String diver = "org.sqlite.JDBC";
	//��������
	private static String conn = "jdbc:sqlite:db/AdressBook.db";
	//��ѯȫ����sql���
	private static String queryAll = "select * from adressUser;";
	//���µ�sql���
	private static String updateAddress = "update adressUser set name=?,sex=?,phone=?,qq=?,email=? where phone=?";
	//ɾ����������sql���
	private static String deleteWhereName = "delete from adressUser where name=? and phone=?;";
	//��ѯ��������name����sql���
	private static String queryWhereName = "select * from adressUser where name=?;";
	//��ѯ��������phone����sql���
	private static String queryWherePhone = "select * from adressUser where phone=?;";
	//��ӵ�sql���
	private static String add = "insert into adressUser values(?,?,?,?,?)";
	private static Connection connection = null;
	private static PreparedStatement pst = null;
	private static Statement sta = null;
	private static LinkedList<AddressBook> adderss = null;

	public Connection getCon() throws Exception {
		Class.forName(diver);
		connection = DriverManager.getConnection(conn);
		return connection;
	}

	public int update(String name, String sex, String phone, int qq, String email, String updatePhone)
			throws Exception {
		PreparedStatement pst = null;
		pst = getCon().prepareStatement(updateAddress);
		pst.setString(1, name);
		pst.setString(2, sex);
		pst.setString(3, phone);
		pst.setInt(4, qq);
		pst.setString(5, email);
		pst.setString(6, updatePhone);
		int rs = pst.executeUpdate();
		System.out.println("\n" + "---�Ѿ���ԭ����Ϊ*" + updatePhone + "*��ͨѶ¼��Ϣ�޸ĳɹ�---" + "\n");

		return rs;
	}

	public int deleteAdderssByNameAndPhone(String name, String phone) {
		int result = 0;
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(deleteWhereName);
			pst.setString(1, name);
			pst.setString(2, phone);
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public LinkedList<AddressBook> queryAccountByPhone(String account) {
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		try {
			adderss = new LinkedList<>();
			pst = getCon().prepareStatement(queryWherePhone);
			pst.setString(1, account);
			resultSet = pst.executeQuery();

			try {
				while (resultSet.next()) {

					adderss.add(new AddressBook(resultSet.getString("name"), resultSet.getString("sex"),
							resultSet.getString("phone"), resultSet.getInt("qq"), resultSet.getString("email")));

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adderss;
	}

	public LinkedList<AddressBook> queryAccountByName(String account) {
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		try {
			adderss = new LinkedList<>();
			pst = getCon().prepareStatement(queryWhereName);
			pst.setString(1, account);
			resultSet = pst.executeQuery();

			try {
				while (resultSet.next()) {

					adderss.add(new AddressBook(resultSet.getString("name"), resultSet.getString("sex"),
							resultSet.getString("phone"), resultSet.getInt("qq"), resultSet.getString("email")));

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adderss;
	}

	public LinkedList<AddressBook> getContent() {
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		try {
			adderss = new LinkedList<>();
			sta = getCon().createStatement();
			resultSet = sta.executeQuery(queryAll);

			while (resultSet.next()) {
				adderss.add(new AddressBook(resultSet.getString("name"), resultSet.getString("sex"),
						resultSet.getString("phone"), resultSet.getInt("qq"), resultSet.getString("email")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return adderss;
	}

	public int addAcccount(String name, String sex, String phone, int qq, String email) throws Exception {
		PreparedStatement pst = null;
		pst = getCon().prepareStatement(add);
		pst.setString(1, name);
		pst.setString(2, sex);
		pst.setString(3, phone);
		pst.setInt(4, qq);
		pst.setString(5, email);
		int rs = pst.executeUpdate();

		return rs;
	}

}
