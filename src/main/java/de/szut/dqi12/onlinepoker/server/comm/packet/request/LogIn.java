package de.szut.dqi12.onlinepoker.server.comm.packet.request;

import de.szut.dqi12.onlinepoker.server.comm.Packet;
import de.szut.dqi12.onlinepoker.server.comm.PacketType;
import org.json.JSONObject;

public class LogIn implements Packet {

	private String username;
	private String password;

	public LogIn(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, PacketType.LOGIN);
		jsonObject.put("username", this.username);
		jsonObject.put("password", this.password);

		return jsonObject.toString();
	}
}
