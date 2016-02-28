
package de.szut.dqi12.onlinepoker.server;

import de.szut.dqi12.onlinepoker.server.poker.database.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Datenbankverbindung herstellen
            Database database = new Database("database/database.sqlite");

            //Neuen Server mit Datenbankabhängigkeit erstellen
            Server server = new Server(database);

            //Server starten
            server.run(8389);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
