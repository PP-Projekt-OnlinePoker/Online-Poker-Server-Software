package de.szut.dqi12.onlinepoker.server.helper.packet;

import de.szut.dqi12.onlinepoker.server.helper.Packet;
import de.szut.dqi12.onlinepoker.server.helper.PacketType;
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
