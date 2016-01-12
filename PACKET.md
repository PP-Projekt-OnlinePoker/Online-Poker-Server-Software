# PACKETS
## Community Action Packets
### Login Packet

* REQUEST: { type: PacketType.LOGIN, username: String, password: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }
* ANSWER (good): { result: true, playerId: Integer, bank: Integer, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* ANSWER (bad): { result: false, error: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }

### Join Table Packet

* REQUEST: { type: PacketType.JOIN, tableId: Integer, playerId: Integer, stake: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }
* ANSWER (good): { result: true, players: [ 1: [ username: String, stake: Integer] 2: [ username: String, stake: Integer] ], commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* ANSWER (bad): { result: false, error: String, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }

### Get Table List Packet

* REQUEST: { type: PacketType.TABLELIST, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }
* ANSWER (good): { result: true, tables: [ 1: [ tableId: Integer, tablename: String ], 2: [ tableId: Integer, tablename: String ] ], commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* ANSWER (bad): { result: false, error: String, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }

## Game Action Packets
### Raise Packet

* REQUEST: { type: PacketType.RAISE, playerId: Integer, tableId: Integer, amount: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java)..REQUEST }
* ANSWER (good): { result: true, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER } 
* ANSWER (bad): { result: false, error: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }

### Bet Packet

* REQUEST: { type: PacketType.BET, playerId: Integer, tableId: Integer, amount: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }
* ANSWER (good): { result: true, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* ANSWER (bad): { result: false, error: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* UPDATE: { commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).UPDATE, playerId: Integer, amount: Integer, type: PacketType.BET }

### Check Packet

* REQUEST: { type: PacketType.CHECK, playerId: Integer, tableId: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).REQUEST }
* ANSWER (good): { result: true, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* ANSWER (bad): { result: false, error: String, [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).ANSWER }
* UPDATE: { playerId: Integer, commway: [CommWay](https://github.com/PP-Projekt-OnlinePoker/Online-Poker-Server-Software/blob/master/src/de/szut/dqi12/holdem/helper/PacketType.java).UPDATE }
### All In Packet
### Turn Packet
### River Packet
### Flop Packet
