package de.szut.dqi12.onlinepoker.server.comm.packet.response.auth;

import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;
import org.json.JSONObject;

/**
 * Created by Kevin on 28.02.2016.
 */
public class LoginResponse extends Packet {
    private boolean success;
    private Player player;

    public LoginResponse(boolean success, Player player) {
        super(PacketType.RESPONSE_LOGIN);
        this.success = success;
        this.player = player;
        this.packetType = PacketType.RESPONSE_LOGIN;
    }

    @Override
    public String toJSON() {
        JSONObject json = new JSONObject();

        json.put(Packet.KEY_ACTION, packetType);
        json.put("success", success);
        json.put("player", player.toJSON());

        return json.toString();
    }
}
