package de.szut.dqi12.holdem.helper;

import java.net.Socket;

import net.sf.json.JSONObject;

public class Event {

	public static final String EVENT_PACKETTYPE = "packettype";
	
	public Table table;
	private PacketType type;
	
	private JSONObject json;
	private Socket socket;
	
	public Event(String json){
		this.json = JSONObject.fromObject(json);
		
		this.type = this.json.get(key)
	}
	
	public PacketType getType(){
		return this.type;
	}

}
