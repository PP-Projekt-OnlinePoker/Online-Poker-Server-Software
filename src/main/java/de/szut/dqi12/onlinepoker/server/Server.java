
package de.szut.dqi12.onlinepoker.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import de.szut.dqi12.onlinepoker.server.comm.handler.PlayerConnectionHandler;
import de.szut.dqi12.onlinepoker.server.comm.Table;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.LogIn;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.Register;
import de.szut.dqi12.onlinepoker.server.database.Database;
import org.apache.log4j.Logger;

public class Server {
    private ServerSocket serverSocket;
    private Database database;

    private ArrayList<PlayerConnectionHandler> connectedPlayers;
    private ArrayList<String> tables;

    private Logger log = Logger.getLogger(this.getClass().getName());

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

        log.info("Server an Port " + serverPort + "gebunden");

        //Die ganze Zeit über neue Verbindungen akzeptieren
        //TODO: Exit Flag
        while (true) {
            Socket newConnection = serverSocket.accept();

            log.info("Neue Verbindung von " + newConnection.getInetAddress() + " akzeptiert");

            //Neue Verbindung akzeptieren
            PlayerConnectionHandler newPlayerConnection = new PlayerConnectionHandler(newConnection, this);

            //Player Verbindung threaden
            new Thread(newPlayerConnection).start();

            log.debug("Thread für " + newConnection.getInetAddress() + " gestartet");

            //Playerhandler zur Liste der verbundenen Spieler hinzufügen
            connectedPlayers.add(newPlayerConnection);
        }
    }

    public boolean login(LogIn login) {
        return database.usersTable().login(login.getUsername(), login.getPassword());
    }

    public boolean register(Register register) {
        return database.usersTable().register(register.getUsername(), register.getPassword(), register.getFirstname(), register.getLastname(), register.getEmail());
    }

    public ArrayList<Table> getTableList() {
        return null;
    }
}