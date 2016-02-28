package de.szut.dqi12.onlinepoker.server.database.table;

import java.sql.Connection;

/**
 * Created by Kevin on 28.02.2016.
 */
public abstract class DatabaseTable {
    protected Connection connection;

    public DatabaseTable(Connection connection) {
        this.connection = connection;
    }

    public abstract boolean createIfNotExists();
}
