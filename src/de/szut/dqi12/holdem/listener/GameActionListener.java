package de.szut.dqi12.holdem.listener;

import de.szut.dqi12.holdem.helper.Event;

public interface GameActionListener {
	
	public Event onRaise(Event e);
	public Event onFold(Event e);
	public Event onBet(Event e);
	public Event onCheck(Event e);
}
