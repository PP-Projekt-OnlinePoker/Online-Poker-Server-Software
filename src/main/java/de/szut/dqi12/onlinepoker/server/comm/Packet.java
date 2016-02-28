package de.szut.dqi12.onlinepoker.server.comm;

public interface Packet {
    String KEY_ACTION = "Action";

    String toJSON();
}
