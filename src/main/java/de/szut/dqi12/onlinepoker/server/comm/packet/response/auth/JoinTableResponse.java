package de.szut.dqi12.onlinepoker.server.comm.packet.response.auth;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Table;
import org.json.JSONObject;

/**
 * Created by Kevin on 29.02.2016.
 */
public class JoinTableResponse {
    private boolean success;
    private Table table;
    protected PacketType packetType;

    public JoinTableResponse(boolean success, Table table) {
        this.success = success;
        this.table = table;
        this.packetType = PacketType.RESPONSE_LOGIN;
    }

    public String toJSON() {
        JSONObject json = new JSONObject();

        json.put(Packet.KEY_ACTION, packetType);
        json.put("success", success);
        json.put("table", table.toJSON());

        return json.toString();
    }
}
