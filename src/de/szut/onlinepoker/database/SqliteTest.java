package de.szut.onlinepoker.database;
import java.sql.*;

import sun.font.CreatedFontTracker;

public class SqliteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SqliteTest test = new SqliteTest();
		test.CreatTable();

	}

	
	
	public void CreatTable(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE USERS "
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, "
					+ " Secound Name   Text     NOT NULL, "
					+ " Username        Text NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	
	
	
	public void InseartValue(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		
	}
	
	
	
	
	
	public void Request(){
		
	}

}
