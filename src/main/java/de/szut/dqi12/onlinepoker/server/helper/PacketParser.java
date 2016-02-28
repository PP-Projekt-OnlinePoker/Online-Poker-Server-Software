package de.szut.dqi12.onlinepoker.server.helper;

import de.szut.dqi12.onlinepoker.server.helper.packet.request.LogIn;
import de.szut.dqi12.onlinepoker.server.helper.packet.request.Register;
import org.json.JSONObject;

/**
 * Created by Kevin on 22.02.2016.
 */
public class PacketParser {

    public static Packet parse(JSONObject jsonParsed){
            //Action in PacketType konvertieren
            PacketType packetType = (PacketType) jsonParsed.get(Packet.KEY_ACTION);

            switch (packetType){
                case LOGIN:
                    return new LogIn(jsonParsed.getString("username"), jsonParsed.getString("password"));
                case REGISTER:
                    return new Register(
                        jsonParsed.getString("username"),
                        jsonParsed.getString("password"),
                        jsonParsed.getString("email"),
                        jsonParsed.getString("firstname"),
                        jsonParsed.getString("lastname")
                    );
                case LOGOUT:
                    break;
                case GETTABLELIST:
                    break;
                case FOLD:
                    break;
                case BET:
                    break;
                case RAISE:
                    break;
                case CHECK:
                    break;
                case CALL:
                    break;
                case ALLIN:
                    break;
                case JOINTABLE:
                    break;
                case CREATETABLE:
                    break;
                case LEAVETABLE:
                    break;
                case STATUS:
                    break;
            }

            return null;
    }
}
