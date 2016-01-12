package de.szut.dqi12.holdem;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import de.szut.dqi12.holdem.helper.Event;
import de.szut.dqi12.holdem.helper.Player;
import de.szut.dqi12.holdem.listener.CommunityActionListener;
import de.szut.dqi12.holdem.listener.GameActionListener;

public class Server {

	private GameActionListener gameActionListener;
	private Log log;
	private ServerSocket socket;
	
	private ArrayList<Socket> players;
	private ArrayList<Table> tables;
	private CommunityActionListener communityActionListener;

	public Server(Integer port, String fileName){
		this.log = new Log(new File(fileName));
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {
			this.log.logError(e.getStackTrace().toString());
		}
		
		this.players = new ArrayList<Socket>();
	}

	public void registerGameActionListener(GameActionListener listener){
		this.gameActionListener = listener;
	}
	
	public void registerCommunityActionListener(CommunityActionListener listener){
		
	}
	
	public Event recievePacket(ServerSocket socket){
		Event event;
		
		if(gameActionListener == null || communityActionListener == null){
			log.logError("Listener not registered.");
		}else{
			
			
			Socket client = socket.accept();
			client.getInputStream();
			
			Scanner in = new Scanner(client.getInputStream());
			
			event = new Event(in.nextLine());
			
		    //PrintWriter out = new PrintWriter( server.getOutputStream(), true );


		}
		
		
		return event;
	}
	
	public void handlePacket(Event e){
		switch(e.getType()){
		case BET:
			this.gameActionListener.onBet(e);
			break;
		case FOLD:
			this.gameActionListener.onFold(e);
			break;
		case CHECK:
			this.gameActionListener.onCheck(e);
			break;
		case RAISE:
			this.gameActionListener.onRaise(e);
			break;
		default:
			break;
		}
	}
	
	public Table getTableByTd(Integer tableId){
		
	}

}
