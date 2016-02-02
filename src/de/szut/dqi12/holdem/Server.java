
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
	
	public Event login(Event e){
		if(db.validatePlayer(e., player)){
			return new Event();
		}else{
			return new Event(player.get);
		}
	}
	
	public Event createTable(Table table){
		
	}
	
	public Event getTableListEvent(){
		return new Event(getTableList());
	}
	
	public void joinTableById(Integer tableId){
		
	}
	
	public ArrayList<Table> getTableList(){
		
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
