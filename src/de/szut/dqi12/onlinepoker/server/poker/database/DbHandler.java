package de.szut.dqi12.onlinepoker.server.poker.database;

import java.sql.*;

import de.szut.dqi12.onlinepoker.server.helper.Player;

public class DbHandler {
	Connection c = null;
	Statement stmt = null;

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		DbHandler sqhandler = new DbHandler();
		sqhandler.CreateTable();

		// sqhandler.InseartValue(100, "test", "test","test", "test", "test");

		// sqhandler.Select();
	}

	public DbHandler() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:database.db");
		System.out.println("Opened database successfully");
		stmt = c.createStatement();
	}

	public void CreateTable() {
		try {
			String sql = "CREATE TABLE if not exists USERS "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "money	Integer	NOT NULL, " + "vorname	Text	NOT NULL, "
					+ "nachname	Text	NOT NULL, " + "password	Text	NOT NULL,"
					+ "email	Text	NOT NULL, " + "username	Text	NOT NULL)";

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void createPlayer(int money, String vorname, String nachname,
			String password, String email, String username) {
		try {
			System.out.println("Opened database successfully");
			String sql = "INSERT INTO USERS (money,vorname,nachname,password,email,username) "
					+ "VALUES ('"
					+ money
					+ "', '"
					+ vorname
					+ "','"
					+ nachname
					+ "','"
					+ password
					+ "','"
					+ email
					+ "','"
					+ username
					+ "');";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void selectbyUsername(String username, Player player) {
		try {
			System.out.println("Opened database successfully");

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM USERS WHERE username ='"
							+ username + "' ;");
			while (rs.next()) {
				player.setPlayerId(Integer.valueOf(rs.getInt("id")));
				player.setVorname(rs.getString("vorname"));
				player.setNachname(rs.getString("nachname"));
				player.setPassword(rs.getString("password"));
				player.seteMail(rs.getString("email"));
				player.setMoney(Integer.valueOf(rs.getInt("money")));
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public void selectbyID(Integer ID, Player player) {
		try {
			System.out.println("Opened database successfully");

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM USERS WHERE Id ='"
							+ ID + "' ;");
			while (rs.next()) {
				player.setVorname(rs.getString("vorname"));
				player.setNachname(rs.getString("nachname"));
				player.setPassword(rs.getString("password"));
				player.seteMail(rs.getString("email"));
				player.setUsername(rs.getString("username"));
				player.setMoney(Integer.valueOf(rs.getInt("money")));
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public Player getPlayer(Player player) {
		if (player.getPlayerId() == null) {
			this.selectbyUsername(player.getUsername(), player);
			return player;}
		else {
			this.selectbyID(player.getPlayerId(), player);
			return player;
			}

	}
	
	public boolean validatePlayer(String password, Player player){
		if (player.getPlayerId() == null) {
			this.selectbyUsername(player.getUsername(), player);
			if (player.getPassword() == password){
				return true;
			}
			else 
				return false;
		}
		else{
			this.selectbyID(player.getPlayerId(), player);
		}

		if (player.getPassword() == password){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updatePlayer(Player player, Integer money){
		this.getPlayer(player);
		player.setMoney(money);
		return true;
	}
	

	public void finalize() throws SQLException {
		c.close();
		stmt.close();

	}
}