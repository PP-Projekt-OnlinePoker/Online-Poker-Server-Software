package de.szut.dqi12.onlinepoker.server.comm.packet.request.lobby;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;

public class JoinTable{

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
