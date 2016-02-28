package de.szut.dqi12.onlinepoker.server.helper.packet.request;

import de.szut.dqi12.onlinepoker.server.helper.Packet;

public class LeaveTable implements Packet {
	
	public int playerId;
	public int tableId;
	public LeaveTable(int playerId, int tableId) {
		this.playerId = playerId;
		this.tableId = tableId;
	}

	public String toJSON() {
		return null;
	}
}
