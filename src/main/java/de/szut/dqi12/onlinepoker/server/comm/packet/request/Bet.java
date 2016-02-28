package de.szut.dqi12.onlinepoker.server.comm.packet.request;

import de.szut.dqi12.onlinepoker.server.comm.Packet;

public class Bet implements Packet {

	public int playerId;
	public int tableId;
	public int amount;
	public Bet(int playerId, int tableId, int amount) {
		this.playerId = playerId;
		this.tableId = tableId;
		this.amount = amount;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String toJSON() {
		return null;
	}
}
