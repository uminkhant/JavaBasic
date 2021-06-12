package com.jdc.mkt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static String user;
	private static String pass;
	private static String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";

	public static Connection getConnetion() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}

	

	public  void setUser(String user) {
		ConnectionManager.user = user;
	}

	public  void setPass(String pass) {
		ConnectionManager.pass = pass;
	}

}
