package de.szut.dqi12.holdem;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import de.szut.dqi12.holdem.handler.PlayerHandle;
import de.szut.dqi12.holdem.handler.TableHandle;
import de.szut.dqi12.holdem.helper.Event;
import de.szut.dqi12.holdem.helper.Player;
import de.szut.dqi12.holdem.helper.Table;
public class Server {

	private Log log;
	private ServerSocket socket;
	
	private static Server instance = null;
	private DbHandler db;
	
	private ArrayList<PlayerHandle> players;
	private ArrayList<TableHandle> tables;

	private Server(){
		db = new DbHandler();
//		this.log = new Log(new File(fileName));
//		try {
//			this.socket = new ServerSocket(port);
//		} catch (IOException e) {
//			this.log.logError(e.getStackTrace().toString());
//		}
//		
//		this.players = new ArrayList<PlayerHandle>();
//		this.tables = new ArrayList<TableHandle>();
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static Server getInstance(){
		if(instance == null){
			instance = new Server();
		}
		return instance;
	}
	
	public boolean login(Player player){
		if(db.validatePlayer(player)){
			
		}else{
			
		}
	}
	
	public ArrayList<Table> getTableList(){
		
	}
	
	public TableHandle getTableById(Integer tableId){
		return tables.get(tableId);
		
	}
	
	public void updatePlayers(Event e){
		this.tables.get(e.getTableId());
	}
	
	public PlayerHandle getPlayerHandleById(Integer playerId){
		return this.players.get(playerId);
	}

}
