package de.szut.dqi12.onlinepoker.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import de.szut.dqi12.onlinepoker.server.Server;
import de.szut.dqi12.onlinepoker.server.exceptions.CouldntAnswerException;
import de.szut.dqi12.onlinepoker.server.helper.PacketType;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;

public class PlayerConnectionHandler implements Runnable {

    private Server server;

    private Socket clientSocket;

    private BufferedReader in;
    private PrintWriter out;

    private boolean loggedIn;
    private Integer currentTableId;
    private boolean isInTable;

    private static final String ERROR_MSG = "Konnte die Aktion nicht ausführen.";
    private static final String ERROR_LOGIN = "Falsches Passwort oder Username.";

    private Logger log;

    public PlayerConnectionHandler(Socket client, Server server) {
        log = Logger.getLogger("PlayerConnectionHandler");

        //Kein eingeloggter Spieler
        this.loggedIn = false;

        //Spieler ist anfangs nicht an einem Tisch
        this.isInTable = false;

        //Client-Socket für Packetaustausch in Member speichern
        this.clientSocket = client;

        //Master-Server für Packet-Handling in Member speichern
        this.server = server;
    }

    @Override
    public void run() {
        try(
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            this.out = out;
            this.in = in;

            while (clientSocket.isConnected()) {
                //Solange der Client verbunden ist, warte auf Nachrichten
                String jsonEncodedRequest  = in.readLine();

                handleRequest(jsonEncodedRequest);
            }
        } catch(IOException ex){
            log.error("Fehler beim öffnen des Output-Streams für einen Client");
        }

        log.info("Client closed connection");
    }

    private void handleRequest(String jsonEncodedRequest){
        JSONObject parsedRequest =  (JSONObject) JSONSerializer.toJSON(jsonEncodedRequest);

        PacketType packetType = (PacketType) parsedRequest.get("action");

        switch (packetType){
            case LOGIN:
                break;
            case REGISTER:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case LOGOUT:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case FOLD:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case BET:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case RAISE:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case CHECK:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case CALL:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case ALLIN:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case GETTABLELIST:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case JOINTABLE:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case CREATETABLE:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
            case STATUS:
                throw new UnsupportedOperationException("This packet-Type is not supported yet");
        }
    }

}
