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

	public PlayerHandle(Socket client){
		this.loggedIn = false;
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
	 * <code>recievePacket</code> is the function the recieve a Packet.
	 * 
	 * @param clientScanner The scanner corrospondend to the client that this handle handles.
	 * @return Returns the corrosponding Event that occured.
	 */
	public Event recievePacket(Scanner clientScanner){
		return Event.fromString(clientScanner.nextLine(), clientSocket);
	}
	
	public Event handleEvent(Event e){
		
		Event answer;
		
		switch(e.getType()){
		case ALLIN:
		case BET:
		case CALL:
		case CHECK:
		case FOLD:
		case RAISE:
			answer = new Event();
			break;
		case CREATETABLE:
			break;
		
		case GETTABLELIST:
			answerWithTableList(Event e);
			break;
		case JOINTABLE:
			break;
		case LOGIN:
			break;
		case LOGOUT:
			if(this.login())
			break;
		
		case REGISTER:
			break;
		case STATUS:
			break;
		default:
			answer = new Event();
			break;
		
		
		}
		
		return answer;
	}
	
	public void answerWithTableList(Event e) throws CouldntAnswerException{
		ArrayList<Table> tables = Server.getInstance().getTableList();
		Event answer = new Event(tables);
		e.answer(answer);
	}
}
