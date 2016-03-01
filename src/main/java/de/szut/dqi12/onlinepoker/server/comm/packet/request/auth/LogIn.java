package de.szut.dqi12.onlinepoker.server.comm.packet.request.auth;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import org.json.JSONObject;

public class LogIn extends Packet {

	private String username;
	private String password;

	public LogIn(String username, String password) {
		super(PacketType.LOGIN);

		this.username = username;
		this.password = password;
	}


	@Override
	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, PacketType.LOGIN);
		jsonObject.put("username", this.username);
		jsonObject.put("password", this.password);

		return jsonObject.toString();
	}
}

