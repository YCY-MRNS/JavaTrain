package com.ycy.test8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JDBCAdress {
	private static String create = "CREATE TABLE adressUser (name  VARCHAR (8)  PRIMARY KEY NOT NULL,sex   VARCHAR (1)  NOT NULL, phone INTEGER (11),qq INTEGER (13),email VARCHAR (20));";
	public static String diver = "org.sqlite.JDBC";
	public static String queryAll = "select * from adressUser;";
	public static String conn = "jdbc:sqlite:db/AdressBook.db";
	public static String query = "select * from adressUser where name=?;";
	public static String add = "insert into user values(null,?,?,?,?)";
	public static Connection connection = null;
	public static PreparedStatement pst = null;
	public static Statement sta = null;
	public static LinkedList<AddressBook> aderss = null;

	public Connection getCon() throws Exception {
		Class.forName(diver);
		connection = DriverManager.getConnection(conn);
		Statement stat = connection.createStatement();
		stat.executeUpdate("drop table if exists adressUser;");
		stat.executeUpdate(create);
		connection.setAutoCommit(false);
		connection.commit();
		return connection;
	}

	public LinkedList<AddressBook> getContent(String sql) {
		ResultSet resultSet = null;
		try {
			aderss = new LinkedList<>();
			sta = getCon().createStatement();
			resultSet = sta.executeQuery(sql);

			while (resultSet.next()) {
				aderss.add(new AddressBook(resultSet.getString("name"), resultSet.getString("sex"),
						resultSet.getString("phone"), resultSet.getInt("qq"), resultSet.getString("email")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aderss;
	}

	public static void main(String[] args) {
		
		for (AddressBook adressBook : aderss) {
			System.out.println(adressBook.toString());
		}

	}
}
