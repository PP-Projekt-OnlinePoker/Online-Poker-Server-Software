package de.szut.dqi12.onlinepoker.server.comm.packet;

public abstract class Packet implements JSONSerializable{
    public static final String KEY_ACTION = "Action";

    protected PacketType packetType;

    public Packet(PacketType packetType) {
        this.packetType = packetType;
    }

    public PacketType getPacketType() {
        return packetType;
    }
}