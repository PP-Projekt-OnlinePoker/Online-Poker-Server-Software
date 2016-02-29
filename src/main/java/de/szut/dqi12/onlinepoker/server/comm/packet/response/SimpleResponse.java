package de.szut.dqi12.onlinepoker.server.comm.packet.response;

import de.szut.dqi12.onlinepoker.server.comm.Packet;
import de.szut.dqi12.onlinepoker.server.comm.PacketType;
import org.json.JSONObject;

/**
 * Created by Kevin on 28.02.2016.
 */
public class SimpleResponse implements Packet{
    private PacketType packetType;
    private boolean success;

    public SimpleResponse(PacketType packetType, boolean success) {
        this.packetType = packetType;
        this.success = success;
    }

    public SimpleResponse(PacketType packetType) {
        this.packetType = packetType;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toJSON() {
        JSONObject json = new JSONObject();

        json.put(Packet.KEY_ACTION, PacketType.RESPONSE);
        json.put("source_type", this.packetType);
        json.put("success", success);

        return json.toString();
    }
}
