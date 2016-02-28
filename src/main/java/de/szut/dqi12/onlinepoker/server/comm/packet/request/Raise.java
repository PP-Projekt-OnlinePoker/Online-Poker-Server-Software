package de.szut.dqi12.onlinepoker.server.comm.packet.request;

import de.szut.dqi12.onlinepoker.server.comm.Packet;

public class Raise implements Packet {

	public int playerId;
	public int tableId;
	public int amount;
	public Raise(int playerId, int tableId, int amount) {
		this.playerId = playerId;
		this.tableId = tableId;
		this.amount = amount;
	}

	public String toJSON() {
		return null;
	}
}