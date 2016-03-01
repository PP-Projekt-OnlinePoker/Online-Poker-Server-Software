package de.szut.dqi12.onlinepoker.server.comm.handler;

import java.io.*;
import java.net.Socket;

import de.szut.dqi12.onlinepoker.server.Server;
import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;
import de.szut.dqi12.onlinepoker.server.comm.packet.parse.PacketParser;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.LogIn;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.Register;
import de.szut.dqi12.onlinepoker.server.comm.packet.response.auth.LoginResponse;
import de.szut.dqi12.onlinepoker.server.comm.packet.response.auth.RegisterResponse;
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
                String jsonEncodedRequest = in.readLine();

                if(jsonEncodedRequest != null && !(jsonEncodedRequest.equals(""))){
                    handleRequest(jsonEncodedRequest);
                }
            }
        } catch(IOException ex){
            log.error("Fehler beim öffnen des Output-Streams für einen Client");
        }

        log.info("Client closed connection");
    }

    private void handleRequest(String jsonEncodedRequest){
        JSONObject parsedRequest = new JSONObject(jsonEncodedRequest);
        PacketType packetType = (PacketType) parsedRequest.get("action");

        Object parsedPacket = PacketParser.parse(parsedRequest);
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
        out.println(packet.toJSON());
    }

    /**
     * Handelt einen LogIn Versuch des Clients.
     *
     * @param login
     * @return
     */
    private Packet handleLogin(LogIn login){
        boolean loginSuccess = server.login(login);
        Player player = null;

        //Registrierung versuchen
        return new RegisterResponse(loginSuccess, player);
    }

    /**
     * Handelt einen Register-Request des Clients.
     *
     * @param register
     * @return
     */
    private Packet handleRegister(Register register){
        boolean registerSuccess = server.register(register);
        Player player = null;

        //Registrierung versuchen
        return new RegisterResponse(registerSuccess, player);
    }

}
