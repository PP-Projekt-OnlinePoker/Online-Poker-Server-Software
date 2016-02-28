package de.szut.dqi12.onlinepoker.server.helper.packet.request;


import de.szut.dqi12.onlinepoker.server.helper.Packet;

public class Call implements Packet {
	public int tableID;
	public int playerID;

	public Call(int tableID, int playerID) {
		this.tableID = tableID;
		this.playerID = playerID;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String toJSON() {
		return null;
	}
}
