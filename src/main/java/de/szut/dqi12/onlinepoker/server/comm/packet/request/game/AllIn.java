package de.szut.dqi12.onlinepoker.server.comm.packet.request.game;


import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;

public class AllIn implements Packet {

	public int playerId;
	public int tableId;
	public AllIn(int playerId, int tableId) {
		this.playerId = playerId;
		this.tableId = tableId;
	}

	public String toJSON() {
		return null;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
}
