package de.szut.dqi12.onlinepoker.server.helper.packet;


import de.szut.dqi12.onlinepoker.server.helper.Packet;
import de.szut.dqi12.onlinepoker.server.helper.PacketType;
import org.json.JSONObject;

public class GetTableList implements Packet {

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Packet.KEY_ACTION, PacketType.GETTABLELIST);
        return jsonObject.toString();
    }
}
