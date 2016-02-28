package de.szut.dqi12.holdem.handler;

import de.szut.dqi12.holdem.helper.Event;
import de.szut.dqi12.holdem.helper.Player;
import de.szut.dqi12.holdem.helper.Table;

public class TableHandle implements Runnable {

	private Table table;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void fold(Player player){
		
	}
	
	public Event handleEvent(Event e){
		switch(e.getType()){
		case RAISE:
			break;
		case BET:
			break;
		case CALL:
			break;
		case CHECK:
			break;
		case FOLD:
			break;
		default:
			break;
		}

		return e;
	}
	
	public void updatePlayers(Event e){
		
		
	}
}
