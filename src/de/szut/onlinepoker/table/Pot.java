package de.szut.onlinepoker.table;

import java.util.ArrayList;

import de.szut.onlinepoker.player.TablePlayer;

public class Pot {

	private int amount=0;
	private ArrayList<TablePlayer> payed;
	
	public void addPlayer(TablePlayer p){
		if(!payed.contains(p)){
			payed.add(p);
		}
		
	}
	
	public void removePlayer(TablePlayer p){
		payed.remove(p);
	}
	
	
	public void addMoney(int money){
		amount+=money;
	}
	
	public int getMoney(){
		return amount;
	}
	
	
}
