# PACKETS
## Community Action Packets
### Login Packet

REQUEST: { type: PacketType.LOGIN, username: String, password: String }
ANSWER (good): { result: true, playerId: Integer, bank: Integer }
ANSWER (bad): { result: false, error: String }

### Join Table Packet

REQUEST: { type: PacketType.JOIN, tableId: Integer, playerid: Integer, stake: Integer }
ANSWER (good): { result: true, players: [ 1: [ username: String, stake: Integer] 2: [ username: String, stake: Integer] ] }
ANSWER (bad): { result: false, error: String }
