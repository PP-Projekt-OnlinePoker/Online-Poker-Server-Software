package de.szut.dqi12.onlinepoker.server.comm.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import de.szut.dqi12.onlinepoker.server.Server;
import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.parse.PacketParser;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.LogIn;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.Register;
import de.szut.dqi12.onlinepoker.server.comm.packet.response.auth.LoginResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

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
        log = Logger.getLogger(this.getClass().getName());

        //Kein eingeloggter Spieler
        this.loggedIn = false;

        //Spieler ist anfangs nicht an einem Tisch
        this.isInTable = false;

        //Client-Socket für Packetaustausch in Member speichern
        this.clientSocket = client;

        //Master-Server für Packet-Handling in Member speichern
        this.server = server;
    }

    public void run() {
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (clientSocket.isConnected()) {
                //Solange der Client verbunden ist, warte auf Nachrichten
                String jsonEncodedRequest  = in.readLine();

                log.info("Packet vom Client erhalten: " + jsonEncodedRequest);

                handleRequest(jsonEncodedRequest);
            }
        } catch(IOException ex){
            log.error("Fehler beim öffnen des Output-Streams für einen Client");
        }

        log.info("Client closed connection");
    }

    private void handleRequest(String jsonEncodedRequest){
        JSONObject parsedRequest = new JSONObject(jsonEncodedRequest);
        PacketType packetType = (PacketType) parsedRequest.get("action");

        Packet parsedPacket = PacketParser.parse(parsedRequest);
        Packet response = null;

        switch (packetType){
            case LOGIN:
                response = handleLogin((LogIn)parsedPacket);
                break;
            case REGISTER:
                response = handleRegister((Register)parsedPacket);
                break;
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

        if(response != null){
            log.info("Sende Packet zum Client: " + response.toJSON());

            //Antwort an den Client senden
            sendPacket(response);
        }
    }

    /**
     * Sendet ein Packet an den Client
     *
     * @param packet
     */
    private void sendPacket(Packet packet){
        out.write(packet.toJSON());
    }

    /**
     * Handelt einen LogIn Versuch des Clients.
     *
     * @param login
     * @return
     */
    private Packet handleLogin(LogIn login){
        LoginResponse response = new LoginResponse(PacketType.LOGIN);

        //Login versuchen
        response.setSuccess(server.login(login));

        //Login-Response senden
        return response;
    }

    /**
     * Handelt einen Register-Request des Clients.
     *
     * @param register
     * @return
     */
    private Packet handleRegister(Register register){
        LoginResponse response = new LoginResponse(PacketType.LOGIN);

        //Registrierung versuchen
        response.setSuccess(server.register(register));

        //Login-Response senden
        return response;
    }

}
