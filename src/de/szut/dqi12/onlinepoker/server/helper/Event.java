
package de.szut.dqi12.onlinepoker.server.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import de.szut.dqi12.onlinepoker.server.exceptions.CouldntAnswerException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Event {

    //JSON Keys
    public static final String EVENT_PACKETTYPE = "packettype";
    public static final String EVENT_PLAYERID = "playerId";
    public static final String EVENT_TABLEID = "tableId";
    public static final String EVENT_COMMWAY = "commway";
    public static final String EVENT_USERNAME = "username";
    public static final String EVENT_PASSWORD = "password";
    public static final String EVENT_EMAIL = "email";
    public static final String EVENT_NAME = "realname";
    public static final String EVENT_AMOUNT = "amount";
    public static final String EVENT_STAKE = "stake";
    public static final String EVENT_TABLENAME = "tablename";
    public static final String EVENT_RESULT = "result";
    public static final String EVENT_TABLES = "tables";

    private Integer tableId;
    private Integer playerId;
    private PacketType type;
    private JSONObject json;
    private CommWay commWay;
    private Player player;
    private Socket socket;
    private String error;
    private boolean result;
    private Integer amount;

    /**
     * Server constructor, when the server receives the Packet, this constructor is called.
     *
     * @param json
     * @param socket
     */
    private Event(String json, Socket socket) {
        this.json = JSONObject.fromObject(json);

        this.type = (PacketType) this.json.get(EVENT_PACKETTYPE);
        this.tableId = this.json.getInt(EVENT_TABLEID);
        this.playerId = this.json.getInt(EVENT_PLAYERID);
        this.commWay = (CommWay) this.json.get(EVENT_COMMWAY);
    }

    /**
     * Get Table List constructor
     *
     * @param tables The tables currently available
     * @param socket The socket from out client
     */
    public Event(ArrayList<Table> tables) {
        JSONArray arr = new JSONArray();
        JSONObject tableObj = new JSONObject();

        for (Table t : tables) {
            tableObj.put(Event.EVENT_TABLEID, t.tablename);
            tableObj.put(Event.EVENT_TABLENAME, t.tableId);

            arr.add(tableObj);
            tableObj.clear();
        }

        this.json.put(Event.EVENT_RESULT, true);
        this.json.put(Event.EVENT_TABLES, arr);
    }

    /**
     * Login constructor
     *
     * @param type
     * @param playerId
     */
    public Event(String username, String password) {
        this.player = new Player(username, password);
        this.type = PacketType.LOGIN;
    }

    /**
     * Answer (bad) constructor
     *
     * @return
     */
    public Event(Socket socket, String error) {
        this.error = error;
        this.socket = socket;
        this.commWay = CommWay.ANSWER;
    }

    /**
     * Answer (good) constructor
     *
     * @return
     */
    public Event() {
        this.result = true;
        this.commWay = CommWay.ANSWER;
    }

    public static Event fromString(String json, Socket client) {
        Event event = new Event(json, client);
        JSONObject obj = JSONObject.fromObject(json);

        switch (event.type) {
            case ALLIN:
                break;
            case BET:
            case RAISE:
                event.amount = obj.getInt(EVENT_AMOUNT);
                break;
            case CALL:
                break;
            case CHECK:
                break;
            case CREATETABLE:
                break;
            case FOLD:
                break;
            case GETTABLELIST:
                break;
            case JOINTABLE:
                break;
            case LOGIN:
                Player player = new Player(obj.getString(EVENT_USERNAME), obj.getString(EVENT_PASSWORD));
                event.player = player;
                break;
            case LOGOUT:
                break;

            case REGISTER:
                break;
            case STATUS:
                break;
            default:
                break;
        }

        return event;
    }

    public String getJSONString() {
        JSONObject obj = new JSONObject();
        switch (this.type) {
            case ALLIN:
                break;
            case BET:
                break;
            case CALL:
                break;
            case CHECK:
                break;
            case CREATETABLE:
                break;
            case FOLD:
                break;
            case GETTABLELIST:
                break;
            case JOINTABLE:
                break;
            case LOGIN:
                break;
            case LOGOUT:
                break;
            case RAISE:
                break;
            case REGISTER:
                break;
            case STATUS:
                break;
            default:
                break;
        }
        return obj.toString();
    }

    public PacketType getType() {
        return this.type;
    }

    public Integer getPlayerId() {
        return this.playerId;
    }

    public Integer getTableId() {
        return this.tableId;
    }

    private void setCommWay(CommWay comm) {
        this.commWay = comm;
    }

    public Socket getClientSocket() {
        return socket;
    }

    /**
     * To answer to the Event that occured.
     *
     * @param e
     * @throws CouldntAnswerException
     */
    public void answer(Event e) throws CouldntAnswerException {
        if (!(e.commWay == CommWay.ANSWER)) {
            throw new CouldntAnswerException();
        }

        PrintWriter pr;
        try {
            pr = new PrintWriter(socket.getOutputStream());
        } catch (IOException e1) {
            throw new CouldntAnswerException();
        }

        pr.println(e.getJSONString());

        pr.flush();
        pr.close();
    }


}
