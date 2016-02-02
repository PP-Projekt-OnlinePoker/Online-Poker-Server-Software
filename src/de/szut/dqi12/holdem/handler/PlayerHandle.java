package de.szut.dqi12.holdem.handler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import de.szut.dqi12.holdem.Server;
import de.szut.dqi12.holdem.exceptions.CouldntAnswerException;
import de.szut.dqi12.holdem.helper.Event;
import de.szut.dqi12.holdem.helper.Table;

public class PlayerHandle implements Runnable {

	private Socket clientSocket;
	private Scanner in;
	private boolean loggedIn;
	private Integer currentTableId;
	private boolean isInTable;
	
	private static final String ERROR_MSG = "Konnte die Aktion nicht ausführen.";

	public PlayerHandle(Socket client){
		this.loggedIn = false;
		this.isInTable = false;
		this.clientSocket = client;
	}
	
	@Override
	public void run() {
		try {
			this.in = new Scanner(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Event event;
		while(clientSocket.isConnected()){
			event = recievePacket(in);
			handleEvent(event);
		}
	}
	
	/**
	 * <code>recievePacket</code> is the function that recieves a Packet.
	 * 
	 * @param clientScanner The scanner corrospondend to the client that this handle handles.
	 * @return Returns the corrosponding Event that occured.
	 */
	public Event recievePacket(Scanner clientScanner){
		return Event.fromString(clientScanner.nextLine(), clientSocket);
	}
	
	public void handleEvent(Event e){
		
		Event answer = new Event(e.getClientSocket(), ERROR_MSG);
		
		switch(e.getType()){
		case ALLIN:
		case BET:
		case CALL:
		case CHECK:
		case FOLD:
		case RAISE:
			Server.getInstance().getTableById(currentTableId).handleEvent(e);
			break;
		case CREATETABLE:
			
			break;
		case GETTABLELIST:
			answer = Server.getInstance().getTableListEvent();
			break;
		case JOINTABLE:
			break;
		case LOGIN:
			break;
		case LOGOUT:
			
			break;
		
		case REGISTER:
			break;
		default:
			answer = new Event(e.getClientSocket(), ERROR_MSG);
			break;
		
		
		}
		
		e.answer(answer);
	}
}
