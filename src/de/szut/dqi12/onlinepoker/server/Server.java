
package de.szut.dqi12.onlinepoker.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import de.szut.dqi12.onlinepoker.server.handler.PlayerHandle;
import de.szut.dqi12.onlinepoker.server.handler.TableHandle;
import de.szut.dqi12.onlinepoker.server.helper.Table;
import de.szut.dqi12.onlinepoker.server.poker.database.Database;

public class Server {
    private ServerSocket serverSocket;
    private Database database;

    private ArrayList<PlayerHandle> connectedPlayers;
    private ArrayList<TableHandle> tables;

    public Server(Database database) {
        this.database = database;

        //Anfangs sind keine Spieler verbunden
        connectedPlayers = new ArrayList<>();

        //Und es gibt keine Tische
        //TODO: Von Datenbank lesen
        tables = new ArrayList<>();
    }

    public void run(int serverPort) throws IOException {
        //Serversocket erstellen und an einen Port binden
        serverSocket = new ServerSocket(serverPort);

        //Die ganze Zeit über neue Verbindungen akzeptieren
        //TODO: Exit Flag
        while (true) {
            //Neue Verbindung akzeptieren
            PlayerHandle newPlayerConnection = new PlayerHandle(serverSocket.accept(), this);

            //Player Verbindung threaden
            new Thread(newPlayerConnection).start();

            //Playerhandler zur Liste der verbundenen Spieler hinzufügen
            connectedPlayers.add(newPlayerConnection);
        }
    }

    public boolean login(String username, String password) {
        return database.usersTable().login(username, password);
    }

    public boolean register(String username, String password, String firstname, String lastname, String email) {
        return database.usersTable().register(username, password, firstname, lastname, email);
    }

    public ArrayList<Table> getTableList() {
        return null;
    }

    public TableHandle getTableById(Integer tableId) {
        return tables.get(tableId);
    }
}