# PACKETS (Events)
All JSON Keys are definied in the Event Class as static final. [Event.java](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/Event.java)

## Community Action Packets (Userfunctions)
### Login Packet /FB02/

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).LOGIN, username: String, password: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### Register Packet /FB01/

* REQUEST: { type: PacketType.REGISTER, username: String, password: String, email: String, realname: String }

### Logout Packet /FB03/

* REQUEST: { type: PacketType.LOGOUT, playerid: Integer, commWay.REQUEST}

### Password Reset Packet /FB04/ (Wunschkriterium)

### Password Change Packet /FB05/ (Wunschkriterium)

### Name Change Packet /FB06/ (Wunschkriterium)




### Join Table Packet /FL02/

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).JOIN, tableId: Integer, playerId: Integer, stake: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### Get Table List Update Packet (/FL03/)

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).TABLELIST, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### Create Table Packet /FL01/

*

## Game Action Packets (Gamefunctions)
### Fold Packet /FS03/

* REQUEST: {type: PacketType.FOLD, playerid: Integer, tableid:Integer, commWay.REQUEST}

### Raise Packet /FS02/ 

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).RAISE, playerId: Integer, tableId: Integer, amount: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### Call Packet /FS04/

* REQUEST: { type: PacketType.CALL, playerId: Integer, tableId: Integer, commway.REQUEST }

### Bet Packet /FS01/

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).BET, playerId: Integer, tableId: Integer, amount: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### Check Packet /FS05/

* REQUEST: { type: [PacketType](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).CHECK, playerId: Integer, tableId: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }

### All In Packet /FS06/

* REQUEST: { type: PacketType.ALLIN, playerId: Integer, tableId: Integer, commway: CommWay.REQUEST }

### Round switches
These Packet indicate the beginning of a new round and their respective name as well as cards getting drawn.
#### Table Begin Packet
#### Flop Packet
#### Turn Packet
#### River Packet
#### Flop Packet

## Table Status Packet
This is the answer to all Game Action Packets.

* UPDATE: {type: PacketType.STATUS, tableid: Integer, player: [{name: String, chips: Integer, betted: Integer, fold: boolean}], pot: Integer, cards: [{cardIdentifier: String}], activePlayerIndex: Integer}

## Answer Packet

