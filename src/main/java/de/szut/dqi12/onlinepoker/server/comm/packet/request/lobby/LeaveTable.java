package de.szut.dqi12.onlinepoker.server.comm.packet.request.lobby;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;

public class LeaveTable extends Packet {
	
	public int playerId;
	public int tableId;

	public LeaveTable(int playerId, int tableId) {
		super(PacketType.LEAVETABLE);

		this.playerId = playerId;
		this.tableId = tableId;
	}

	public String toJSON() {
		return null;
	}
}
