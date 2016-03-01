package de.szut.dqi12.onlinepoker.server.database.table;

import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Kevin on 28.02.2016.
 */
public class UsersTable extends DatabaseTable {
    private static final int START_MONEY = 500;


    public UsersTable(Connection connection) {
        super(connection);
    }

    @Override
    public boolean createIfNotExists() {
        try {
            String sql = "CREATE TABLE if not exists USERS "
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "money	Integer	NOT NULL, "
                    + "firstname	Text	NOT NULL, "
                    + "lastname	Text	NOT NULL, "
                    + "password	Text	NOT NULL,"
                    + "email	Text	NOT NULL, "
                    + "username	Text	NOT NULL)";

            //Neues Statement erzeugen
            Statement statement = connection.createStatement();

            boolean success = statement.executeUpdate(sql) == Statement.SUCCESS_NO_INFO;

            //Statement schließen
            statement.close();

            //True bei erfolg zurückgeben
            return success;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

            //False bei misserfolg zurückgeben
            return false;
        }
    }

    /**
     * Erstellt einen neuen Benutzer
     *
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @param email
     * @param startMoney
     */
    private boolean create(String username, String password, String firstname, String lastname, String email, int startMoney) {
        try {
            System.out.println("Opened database successfully");
            String sql = "INSERT INTO USERS (money,firstname,lastname,password,email,username) "
                    + "VALUES ('"
                    + startMoney
                    + "', '"
                    + firstname
                    + "','"
                    + lastname
                    + "','"
                    + password
                    + "','"
                    + email
                    + "','"
                    + username
                    + "');";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            statement.close();

            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

            return false;
        }
    }

    private Player getByName(String username) {
        try {
            Statement statement = connection.createStatement();

            //Datenbankabfrage für den gesuchen Spieler mit Username in
            //Where Clause durchführen
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM USERS WHERE username ='"
                            + username + "' ;");

            Player player = new Player(resultSet.getString("username"), resultSet.getString("password"));

            //Nur erstes Result Set betrachten, da usernamen unique sind
            resultSet.first();

            //Player mit Daten aus dem Record befüllen
            player.setMoney(resultSet.getInt("money"));
            player.setEmail(resultSet.getString("email"));
            player.setId(resultSet.getInt("id"));
            player.setLastname(resultSet.getString("lastname"));
            player.setFirstname(resultSet.getString("firstname"));

            //Result-Set schließen
            resultSet.close();
            statement.close();

            //Spieler-Objekt zurückgeben
            return player;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean login(String username, String password) {
        Player player = this.getByName(username);

        if (player.getPassword() == password) {
            return true;
        } else {
            return false;
        }
    }

    public boolean register(String username, String password, String firstname, String lastname, String email){
        return this.create(username, password, firstname, lastname, email, START_MONEY);
    }

    private boolean update(Player player) {
        //TODO: Implement update method for all player properties
        return false;
    }
}
