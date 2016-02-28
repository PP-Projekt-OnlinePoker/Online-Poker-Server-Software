package de.szut.dqi12.onlinepoker.server.helper.packet;


import de.szut.dqi12.onlinepoker.server.helper.Packet;
import de.szut.dqi12.onlinepoker.server.helper.PacketType;
import org.json.JSONObject;

public class Register implements Packet {

	private String username;
	private String password;
	private String email;
	private String realName;

	public Register(String username, String password, String email, String realName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.realName = realName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRealName() {
		return realName;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, PacketType.REGISTER);
		jsonObject.put("username", this.username);
		jsonObject.put("password", this.password);
		jsonObject.put("email", this.email);
		jsonObject.put("real_name", this.realName);

		return jsonObject.toString();
	}
}
