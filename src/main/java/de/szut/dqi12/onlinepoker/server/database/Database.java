package de.szut.dqi12.onlinepoker.server.database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

import de.szut.dqi12.onlinepoker.server.database.table.TablesTable;
import de.szut.dqi12.onlinepoker.server.database.table.UsersTable;

import org.apache.log4j.Logger;

public class Database implements Closeable {
    Connection connection;

    private UsersTable usersTable;
    private TablesTable tablesTable;

    private Logger log;

    public Database(String databaseFileName) throws ClassNotFoundException, SQLException {
        log = Logger.getLogger(this.getClass().getName());

        //SqLite Database Connector Klasse aus JAR laden
        Class.forName("org.sqlite.JDBC");

        //Verbindung zur Datenbank herstellen
        connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFileName);

        log.info("Verbindung zur Datenbank erfolgreich hergestellt.");

        //Table Objekte initialisieren
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