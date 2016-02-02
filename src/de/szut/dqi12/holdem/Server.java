
package de.szut.dqi12.holdem;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import de.szut.dqi12.holdem.handler.PlayerHandle;
import de.szut.dqi12.holdem.handler.TableHandle;
import de.szut.dqi12.holdem.helper.Event;
import de.szut.dqi12.holdem.helper.Player;
import de.szut.dqi12.holdem.helper.Table;
import de.szut.onlinepoker.database.DbHandler;
public class Server {

	private ServerSocket socket;
	
	private static Server instance = null;
	private DbHandler db;
	
	private ArrayList<PlayerHandle> players;
	private ArrayList<TableHandle> tables;

	private Server(){
		try {
			db = new DbHandler();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(db.validatePlayer(player.getPassword(), player)){
			return true;
		}else{
			return false;
		}
	}
	
	public Event createTable(Table table){
		return new Event();
	}
	
	public Event getTableListEvent(){
		return new Event(getTableList());
	}
	
	public void joinTableById(Integer tableId){
		
	}
	
	public ArrayList<Table> getTableList(){
		return null;
		
	}
	
	public TableHandle getTableById(Integer tableId){
		return tables.get(tableId);
		
	}
	
	public void updatePlayers(Event e){
		TableHandle table = this.tables.get(e.getTableId());
		table.updatePlayers(e);
	}
	
	public PlayerHandle getPlayerHandleById(Integer playerId){
		return this.players.get(playerId);
	}

}
