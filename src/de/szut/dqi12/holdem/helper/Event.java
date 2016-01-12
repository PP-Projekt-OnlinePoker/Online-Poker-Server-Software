package de.szut.dqi12.holdem.helper;

import java.net.Socket;

import net.sf.json.JSONObject;

public class Event {

	public static final String EVENT_PACKETTYPE = "packettype";
	public static final String EVENT_PLAYERID = "playerId";
	public static final String EVENT_TABLEID = "tableId";
	public static final String EVENT_COMMWAY = "commway";
	
	public static final String EVENT_USERNAME = "username";
	public static final String EVENT_PASSWORD = "password";
	
	private Integer tableId;
	private Integer playerId;
	
	private PacketType type;
	
	private JSONObject json;
	private Socket socket;
	
	private CommWay commWay;
	
	private String username;
	private String password;
	
	public Event(String json, Socket socket){
		this.json = JSONObject.fromObject(json);
		
		this.type = (PacketType) this.json.get(EVENT_PACKETTYPE);
		this.tableId = this.json.getInt(EVENT_TABLEID);
		this.playerId = this.json.getInt(EVENT_PLAYERID);
		this.commWay = (CommWay) this.json.get(EVENT_COMMWAY);
		this.socket = socket;
	}
	
	/**
	 * Login Constructor
	 * @param type
	 * @param playerId
	 */
	public Event(String username, String password){
		this.type = PacketType.LOGIN;
		this.username = username;
		this.password = password;
	}
	
	public String getJSONString(){
		JSONObject obj =new JSONObject();
		switch(this.type){
		case LOGIN:
			obj.put(EVENT_USERNAME, this.username);
			obj.put(EVENT_PASSWORD, this.password);
			break;
		case BET:
			break;
		case CHECK:
			break;
		case FOLD:
			break;
		case RAISE:
			break;
		case TABLELIST:
			break;
		default:
			break;
		}
		return obj.toString();
	}
	
	
	
	public PacketType getType(){
		return this.type;
	}
	
	public Integer getPlayerId(){
		return this.playerId;
	}
	
	public Integer getTableId(){
		return this.tableId;
	}
	
	private void setCommWay(CommWay comm){
		this.commWay = comm;
	}
	
	public void answer(Event e){
		e.setCommWay(CommWay.ANSWER);
	}

}
