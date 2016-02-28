package de.szut.dqi12.onlinepoker.server.helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin on 22.02.2016.
 */
public class PacketParser {

    public static PacketParser parse(String received) throws PacketParseException{
        try{
            JSONObject jsonParsed = new JSONObject(received);

            //Action in PacketType konvertieren
            PacketType packetType = (PacketType) jsonParsed.get(Packet.KEY_ACTION);

            switch (packetType){
                case LOGIN:

                    break;
                case REGISTER:
                    break;
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

            return new PacketParser();
        } catch(JSONException e){
            throw new PacketParseException();
        }
    }
}
