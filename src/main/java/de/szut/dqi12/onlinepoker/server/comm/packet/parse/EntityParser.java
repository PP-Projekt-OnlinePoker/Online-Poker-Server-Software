package de.szut.dqi12.onlinepoker.server.comm.packet.parse;

import de.szut.dqi12.onlinepoker.server.comm.packet.entity.EntityType;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Table;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kevin on 29.02.2016.
 */
public class EntityParser {
    public static Object parse(JSONObject jsonParsed){
        EntityType entityType = (EntityType) jsonParsed.get("");

        switch (entityType){
            case PLAYER:
                return parsePlayer(jsonParsed);
            case TABLE:
                return parseTable(jsonParsed);
            default:
                throw new UnsupportedOperationException("Not supported yet");
        }
    }

    private static Player parsePlayer(JSONObject jsonParsed){
        return new Player(
                jsonParsed.getInt("id"),
                jsonParsed.getInt("money"),
                jsonParsed.getString("username"),
                jsonParsed.getString("firstname"),
                jsonParsed.getString("lastname"),
                jsonParsed.getString("email"),
                jsonParsed.getString("password")
        );
    }

    private static Table parseTable(JSONObject jsonParsed){
        ArrayList<Player> players = new ArrayList<>();

        for(Object parsedPlayer : jsonParsed.getJSONArray("players")){
            players.add(parsePlayer((JSONObject) parsedPlayer));
        }

        return new Table(jsonParsed.getInt("id"), jsonParsed.getInt("small_blind"), jsonParsed.getString("name"), jsonParsed.getInt("max_start_money"), jsonParsed.getInt("max_players"), players);
    }

}
