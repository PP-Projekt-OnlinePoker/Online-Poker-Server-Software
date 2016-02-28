package de.szut.dqi12.onlinepoker.server.comm.packet.request;

import de.szut.dqi12.onlinepoker.server.comm.Packet;

public class JoinTable implements Packet {

	public int tableId;
	public int playerId;
	public int stake;

	public JoinTable(int tableId, int playerId, int stake) {
		this.tableId = tableId;
		this.playerId = playerId;
		this.stake = stake;
	}

    public String toJSON() {
        return null;
    }
}