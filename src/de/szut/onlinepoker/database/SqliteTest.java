package de.szut.onlinepoker.database;
import java.sql.*;

import sun.font.CreatedFontTracker;

public class SqliteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SqliteTest test = new SqliteTest();
//		test.CreatTable();
//		test.InseartValue("test", "test","test");
		
		test.Select();
	}

	public void CreatTable(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql ="CREATE TABLE USERS "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "Name	TEXT	NOT NULL, "
					+ "LastName	Text	NOT NULL, "
					+ "Username	Text	NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	public void InseartValue(String username, String name, String lastName){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO USERS (Name,LastName,Username) "
					+ "VALUES ('"+name +"', '"+ lastName +"','"+ username +"');";
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
	
	public void Select(){
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("Name");
         String lastname  = rs.getString("LastName");
         String  username = rs.getString("Username");

         System.out.println( "ID = " + id );
         System.out.println( "Name = " + name );
         System.out.println( "LastName = " + lastname);
         System.out.println( "Username = " + username );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
	}
}
