package de.szut.dqi12.onlinepoker.server.poker.database.table;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Kevin on 28.02.2016.
 */
public class TablesTable extends DatabaseTable {
    public TablesTable(Connection connection) {
        super(connection);
    }

    @Override
    public boolean createIfNotExists() {
        try {
            String sql = "CREATE TABLE if not exists Tables "
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "money	Integer	NOT NULL, "
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
}
