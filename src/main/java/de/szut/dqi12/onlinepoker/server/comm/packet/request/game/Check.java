package de.szut.dqi12.onlinepoker.server.comm.packet.request.game;


import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;

public class Check extends Packet {

	public int playerID;
	public int tableID;
	public Check(int playerID, int tableID) {
		super(PacketType.CHECK);

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
