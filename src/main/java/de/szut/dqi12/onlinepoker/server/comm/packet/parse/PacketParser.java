package de.szut.dqi12.onlinepoker.server.comm.packet.parse;

import de.szut.dqi12.onlinepoker.server.comm.packet.PacketType;
import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.LogIn;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.LogOut;
import de.szut.dqi12.onlinepoker.server.comm.packet.request.auth.Register;
import org.json.JSONObject;

/**
 * Created by Kevin on 22.02.2016.
 */
public class PacketParser {

    public static Object parse(JSONObject jsonParsed){
            //Action in PacketType konvertieren
            PacketType packetType = (PacketType) jsonParsed.get(Packet.KEY_ACTION);

            switch (packetType){
                case LOGIN:
                    return parseLogIn(jsonParsed);
                case REGISTER:
                    return parseRegister(jsonParsed);
                case LOGOUT:
                    return parseLogout(jsonParsed);
                default:
                    throw new UnsupportedOperationException("Unsupported Packet");
            }
    }

    private static Object parseLogIn(JSONObject jsonParsed){
        return new LogIn(jsonParsed.getString("username"), jsonParsed.getString("password"));
    }

    private static Object parseRegister(JSONObject jsonParsed){
        return new Register(
                jsonParsed.getString("username"),
                jsonParsed.getString("password"),
                jsonParsed.getString("email"),
                jsonParsed.getString("firstname"),
                jsonParsed.getString("lastname")
        );
    }

    private static Object parseLogout(JSONObject jsonParsed){
        return new LogOut(jsonParsed.getInt("player_id"));
    }
}
