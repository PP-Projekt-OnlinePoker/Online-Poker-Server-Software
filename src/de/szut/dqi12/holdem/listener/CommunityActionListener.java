package de.szut.dqi12.holdem.listener;

import de.szut.dqi12.holdem.helper.Event;

public interface CommunityActionListener {
	public void onLogin(Event e);
	public void onGetProfile(Event e);
}
