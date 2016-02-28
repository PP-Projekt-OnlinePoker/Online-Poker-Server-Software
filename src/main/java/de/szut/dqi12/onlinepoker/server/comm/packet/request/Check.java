package de.szut.dqi12.onlinepoker.server.comm.packet.request;


import de.szut.dqi12.onlinepoker.server.comm.Packet;

public class Check implements Packet {

	public int playerID;
	public int tableID;
	public Check(int playerID, int tableID) {
		this.playerID = playerID;
		this.tableID = tableID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String toJSON() {
		return null;
	}
}
