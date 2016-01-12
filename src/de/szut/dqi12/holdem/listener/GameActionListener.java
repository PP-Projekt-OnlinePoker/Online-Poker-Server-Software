package de.szut.dqi12.holdem.listener;

import de.szut.dqi12.holdem.helper.Event;

public interface GameActionListener {
	
	public void onRaise(Event e);
	public void onFold(Event e);
	public void onBet(Event e);
	public void onCheck(Event e);
}
