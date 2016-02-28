package de.szut.dqi12.onlinepoker.server.poker.database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

import de.szut.dqi12.onlinepoker.server.helper.Player;
import de.szut.dqi12.onlinepoker.server.poker.database.table.TablesTable;
import de.szut.dqi12.onlinepoker.server.poker.database.table.UsersTable;

public class Database implements Closeable {
    Connection connection;

    private UsersTable usersTable;
    private TablesTable tablesTable;

    public Database(String databaseFileName) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        //Verbindung zur Datenbank herstellen
        connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFileName);

        usersTable = new UsersTable(connection);
        tablesTable = new TablesTable(connection);
    }

    public UsersTable usersTable() {
        return usersTable;
    }

    public TablesTable getTablesTable() {
        return tablesTable;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}