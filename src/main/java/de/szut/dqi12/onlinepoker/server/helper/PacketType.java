
package de.szut.dqi12.onlinepoker.server.helper;

public enum PacketType {
	//Tischaktionen
	FOLD,
	BET,
	RAISE,
	CHECK,
	CALL,
	ALLIN,

	GETTABLELIST,

	//Usersystem-Prozess
	LOGIN,
	REGISTER,
	LOGOUT,

	//Tisch
	JOINTABLE,
	CREATETABLE,
	LEAVETABLE,
	STATUS,
}
