
package de.szut.dqi12.onlinepoker.server;

import de.szut.dqi12.onlinepoker.server.poker.database.Database;
import org.apache.log4j.Logger;

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
            Logger.getRootLogger().error("Verbindung zur Datenbank konnte nicht hergestellt werden", e);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getRootLogger().error("Verbindung zur Datenbank konnte nicht hergestellt werden", e);
        }
    }

}
