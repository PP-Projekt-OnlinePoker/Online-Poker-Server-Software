package de.szut.dqi12.onlinepoker.server.comm.packet.request;

import de.szut.dqi12.onlinepoker.server.comm.Packet;
import de.szut.dqi12.onlinepoker.server.comm.PacketType;
import org.json.JSONObject;

public class LogOut implements Packet {

	public int playerId;

	public LogOut(int playerId) {
		this.playerId = playerId;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, PacketType.LOGOUT);
		jsonObject.put("player_id", this.playerId);

		return jsonObject.toString();
	}
}
