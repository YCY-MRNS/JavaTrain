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
	
	//连接数据库的驱动地址
	private static String diver = "org.sqlite.JDBC";
	//连接数据
	private static String conn = "jdbc:sqlite:db/AdressBook.db";
	//查询全部的sql语句
	private static String queryAll = "select * from adressUser;";
	//更新的sql语句
	private static String updateAddress = "update adressUser set name=?,sex=?,phone=?,qq=?,email=? where phone=?";
	//删除带条件的sql语句
	private static String deleteWhereName = "delete from adressUser where name=? and phone=?;";
	//查询到条件（name）的sql语句
	private static String queryWhereName = "select * from adressUser where name=?;";
	//查询到条件（phone）的sql语句
	private static String queryWherePhone = "select * from adressUser where phone=?;";
	//添加的sql语句
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
		System.out.println("\n" + "---已经将原号码为*" + updatePhone + "*的通讯录信息修改成功---" + "\n");

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
