package de.szut.dqi12.onlinepoker.server.comm.packet.request.game;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;

public class Raise extends Packet {

	public int playerId;
	public int tableId;
	public int amount;
	public Raise(int playerId, int tableId, int amount) {
		super(PacketType.RAISE);

		this.playerId = playerId;
		this.tableId = tableId;
		this.amount = amount;
	}

	public String toJSON() {
		return null;
	}
}
