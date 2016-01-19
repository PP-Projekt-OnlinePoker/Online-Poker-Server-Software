package de.szut.dqi12.packet;

public interface CommunityActionPacket extends BasePacket {
	public Socket client;
	
	public void answer();
}	
