package de.szut.dqi12.packet;

import net.sf.json.JSONObject;

public interface BasePacket {
	public String getJSONString();
	public void fillPacketByJSON(JSONObject json);
}
