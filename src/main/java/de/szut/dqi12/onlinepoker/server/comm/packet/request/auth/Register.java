package de.szut.dqi12.onlinepoker.server.comm.packet.request.auth;


import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import org.json.JSONObject;

public class Register extends Packet {

	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;

	public Register(String username, String password, String email, String firstname, String lastname) {
		super(PacketType.REGISTER);

		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
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

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Packet.KEY_ACTION, PacketType.REGISTER);
		jsonObject.put("username", this.username);
		jsonObject.put("password", this.password);
		jsonObject.put("firstname", this.firstname);
		jsonObject.put("lastname", this.lastname);
		jsonObject.put("email", this.email);

		return jsonObject.toString();
	}
}
