package de.szut.onlinepoker.table;

import java.util.ArrayList;

import de.szut.onlinepoker.player.TablePlayer;


/**
 * Represents a poker pot
 * not much function, 
 * but can be extended for sidepots later
 * @author Fabian
 *
 */
public class Pot {

	private int amount=0;
	
	
	
	public void setMoney(int money){
		amount=money;
	}
	
	
	public void addMoney(int money){
		amount+=money;
	}
	
	public int getMoney(){
		return amount;
	}
	
	
}
