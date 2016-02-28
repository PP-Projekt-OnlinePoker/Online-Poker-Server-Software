package de.szut.dqi12.onlinepoker.server.handler;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import de.szut.dqi12.onlinepoker.server.Server;
import de.szut.dqi12.onlinepoker.server.exceptions.CouldntAnswerException;
import de.szut.dqi12.onlinepoker.server.helper.Event;

public class PlayerHandle implements Runnable {

    private Server server;

    private Socket clientSocket;
    private Scanner in;

    private boolean loggedIn;
    private Integer currentTableId;
    private boolean isInTable;

    private static final String ERROR_MSG = "Konnte die Aktion nicht ausführen.";
    private static final String ERROR_LOGIN = "Falsches Passwort oder Username.";

    public PlayerHandle(Socket client, Server server) {
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
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Event event;
        while (clientSocket.isConnected()) {
            event = recievePacket(in);

            try {
                handleEvent(event);
            } catch (CouldntAnswerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <code>recievePacket</code> is the function that recieves a Packet.
     *
     * @param clientScanner The scanner corrospondend to the client that this handle handles.
     * @return Returns the corrosponding Event that occured.
     */
    public Event recievePacket(Scanner clientScanner) {
        return Event.fromString(clientScanner.nextLine(), clientSocket);
    }

    public void handleEvent(Event e) throws CouldntAnswerException {

        Event answer = new Event(e.getClientSocket(), ERROR_MSG);

        //TODO: Handle events

        e.answer(answer);
    }
}
