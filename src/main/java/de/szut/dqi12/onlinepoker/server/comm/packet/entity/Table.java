
package de.szut.dqi12.onlinepoker.server.comm.packet.entity;

import de.szut.dqi12.onlinepoker.server.comm.packet.JSONSerializable;
import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import de.szut.dqi12.onlinepoker.server.comm.packet.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Table implements JSONSerializable{

    private int id;
    private int maxPlayers;
    private int smallBlind;
    private int bigBlind;
    private String name;
    private int maxStartMoney;

    private ArrayList<Player> tablePlayers = new ArrayList<>();

    @Override
    public String toJSON() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", this.id);
        jsonObject.put("small_blind", this.smallBlind);
        jsonObject.put("big_blind", this.bigBlind);
        jsonObject.put("max_players", this.maxPlayers);
        jsonObject.put("name", this.name);
        jsonObject.put("max_start_money", this.maxStartMoney);

        //Spieler hinzufügen
        JSONArray players = new JSONArray();

        for(Player player : tablePlayers){
            players.put(player.toJSON());
        }

        jsonObject.put("players", players);

        return jsonObject.toString();
    }

    public Table(int id, int smallBlind, String name, int maxStartMoney, int maxPlayers, ArrayList<Player> tablePlayers) {
        this.id = id;
        this.smallBlind = smallBlind;
        this.bigBlind = smallBlind * 2;
        this.maxPlayers = maxPlayers;
        this.name = name;
        this.maxStartMoney = maxStartMoney;
        this.tablePlayers = tablePlayers;
    }
}
