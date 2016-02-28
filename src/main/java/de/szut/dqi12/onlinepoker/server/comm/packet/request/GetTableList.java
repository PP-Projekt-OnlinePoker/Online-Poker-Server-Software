package de.szut.dqi12.onlinepoker.server.comm.packet.request;


import de.szut.dqi12.onlinepoker.server.comm.Packet;
import de.szut.dqi12.onlinepoker.server.comm.PacketType;
import org.json.JSONObject;

public class GetTableList implements Packet {

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Packet.KEY_ACTION, PacketType.GETTABLELIST);
        return jsonObject.toString();
    }
}
