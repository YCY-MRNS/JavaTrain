package com.ycy.test8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JDBC {

	public static String diver = "org.sqlite.JDBC";
	public static String conn = "jdbc:sqlite:db/student.db";
	public static String query = "select * from user where name=?;";
	public static String add = "insert into user values(null,?,?,?,?)";
	public static Connection connection = null;
	public static PreparedStatement pst = null;
	public static Statement sta = null;
	public static LinkedList<User> users = new LinkedList<>();

	public Connection getCon() throws Exception {
		Class.forName(diver);
		connection = DriverManager.getConnection(conn);
		return connection;
	}

	public int addAcccount(String name, String nickName, int loginNum, String pw) throws Exception {

		PreparedStatement pstmt = getCon().prepareStatement(add);
		pstmt.setString(1, name);
		pstmt.setString(2, nickName);
		pstmt.setInt(3, loginNum);
		pstmt.setString(4, pw);
		int rs = pstmt.executeUpdate();

		pstmt.close();
		return rs;
	}

	public LinkedList<User> queryAccount(String account) {
		ResultSet rs = null;

		try {

			pst = getCon().prepareStatement(query);
			pst.setString(1, account);
			rs = pst.executeQuery();

			try {
				while (rs.next()) {

					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String loginNum = rs.getString("login");
					String password = rs.getString("passward");

					users.add(new User(name, password, nickName, loginNum));

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return users;
	}

	public LinkedList<User> getContent(String sql) {
		ResultSet rs = null;

		try {
			sta = getCon().createStatement();
			rs = sta.executeQuery(sql);

			try {
				while (rs.next()) {

					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String loginNum = rs.getString("login");
					String password = rs.getString("passward");

					users.add(new User(name, password, nickName, loginNum));

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

}
