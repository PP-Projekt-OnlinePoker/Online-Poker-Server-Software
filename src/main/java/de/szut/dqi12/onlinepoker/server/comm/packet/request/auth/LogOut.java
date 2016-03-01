package de.szut.dqi12.onlinepoker.server.comm.packet.request.auth;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import org.json.JSONObject;

public class LogOut extends Packet {

	public int playerId;

	public LogOut(int playerId) {
		super(PacketType.LOGOUT);
		this.playerId = playerId;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, this.packetType);
		jsonObject.put("player_id", this.playerId);

		return jsonObject.toString();
	}
}
