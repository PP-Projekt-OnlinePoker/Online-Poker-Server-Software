package de.szut.onlinepoker.table;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import de.szut.dqi12.holdem.helper.Event;
import de.szut.onlinepoker.card.Card;
import de.szut.onlinepoker.card.Deck;
import de.szut.onlinepoker.card.HandEvaluator;
import de.szut.onlinepoker.player.TablePlayer;
import net.sf.json.JSONObject;

/**
 * Table class for the actual game thread
 * @author Fabian
 *
 */
public class Table implements Runnable {

	private TablePlayer[] plys;
	private Pot pot; // main and side pots
	private Deck deck;
	private final int smallBlind;
	private final int bigBlind;
	private int dealInd;
	private int smallBlindIndex;
	private int bigBlindIndex;
	private Card[] comu;
	private final int ID;
	private final int maxBet;
	private final int maxPlayerCount;
	JSONObject json;

	private boolean terminated;
	private int actvPl;

	/**
	 * terminates the thread finishes current loop
	 */
	public void terminate() {
		terminated = true;
	}

	/**
	 * 
	 * @return max possible bet
	 */
	public int getMaxBet() {
		return maxBet;
	}

	/**
	 * 
	 * @return max number of players
	 */
	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}

	/**
	 * TableConfiguration is final and cant be altered afterwards
	 * 
	 * @param cfg
	 */
	public Table(TableConfiguration cfg) {
		ID = cfg.getID();
		this.dealInd = 0;
		maxPlayerCount = cfg.getMaxPlayer();
		if (maxPlayerCount > 9 || maxPlayerCount < 0) {
			throw new InvalidParameterException("Invalid number of player");
		}
		plys = new TablePlayer[maxPlayerCount];
		smallBlind = cfg.getSmallBlind();
		bigBlind = 2 * smallBlind;
		maxBet = cfg.getMaxBet();
		deck = new Deck();

	}

	/**
	 * 
	 * @return the unique table id
	 */
	public int getID() {
		return this.ID;
	}

	/**
	 * starts a new round reinitialize all components of a game
	 */
	public void newRound() {
		deck.reInit();
		for (TablePlayer p : plys) {
			if (p != null) {
				p.setAllIn(false);
				p.setFold(false);
				p.setBetted(0);
				p.setCards(null);
				actvPl++;

			}
		}
		dealInd = (dealInd + 1) % maxPlayerCount;
		pot = new Pot();
		comu = new Card[5];
	}

	/**
	 * 
	 * @return the used small Blind
	 */
	public int getSmallBlind() {
		return smallBlind;
	}

	/**
	 * 
	 * @return the used big Blind
	 */
	public int getBigBlind() {
		return bigBlind;
	}

	/**
	 * adds a player to the table
	 * 
	 * @param p
	 */
	public void addPlayer(TablePlayer p) {
		for (TablePlayer play : plys) {
			if (play == null) {
				play = p;
			}
		}
	}

	/**
	 * removes a player from the table
	 * 
	 * @param p
	 */
	public TablePlayer removePlayer(int id) {
		for (TablePlayer play : plys) {
			if (play.getID() == id) {
				TablePlayer temp = play;
				play = null;
				return temp;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return the player at this table
	 */
	public TablePlayer[] getPlayer(){
		return plys;
	}
	
	
	/**
	 * 
	 * @return the number of player at this table
	 */
	public int getPlayerNumber(){
		int n=0;
		for(TablePlayer p:plys){
			if(p!=null){
				n++;
			}
		}
		return n;
	}
	
	
	/**
	 * gives each player two cards
	 */
	public void giveCards() {
		int start = dealInd % maxPlayerCount;
		int cur = start;
		for (int i = 0; i < 2; i++) {
			cur = (start + 1) % maxPlayerCount;
			while (cur != start) {
				plys[cur].setCard(deck.draw(), i);
				plys[cur].setFold(false);
				cur = (cur + 1) % maxPlayerCount;
			}
			plys[cur].setCard(deck.draw(), i);
			plys[cur].setFold(false);
		}
	}

	/**
	 * connects two Card arrays
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public Card[] connect(Card[] c1, Card[] c2) {
		Card[] res = new Card[c1.length + c2.length];
		for (int i = 0; i < c1.length; i++) {
			res[i] = c1[i];
		}
		for (int i = 0; i < c2.length; i++) {
			res[i + c1.length] = c2[i];
		}
		return res;
	}

	/**
	 * table ID
	 * 
	 * @return
	 */

	/**
	 * 
	 * @return the index of the dealer
	 */
	public int getDealerIndex() {
		return dealInd;
	}

	/**
	 * runs the game loop
	 */
	@Override
	public void run() {
		while (!terminated) {
			newRound();
			payBlinds();

			putBetsInPot();

			// preflop start with player left from bigblind
			betRound((bigBlindIndex + 1) % maxPlayerCount, bigBlind);
			putBetsInPot();
			// preflop end
			// flop
			deck.draw(); // burn card
			comu[0] = deck.draw();
			comu[1] = deck.draw();
			comu[2] = deck.draw();
			betRound(dealInd, 0);
			putBetsInPot();
			// flop end
			// turn
			deck.draw();
			comu[3] = deck.draw();
			betRound(dealInd, 0);
			putBetsInPot();
			// turn end
			// river
			deck.draw();
			comu[4] = deck.draw();
			betRound(dealInd, 0);
			putBetsInPot();
			// river end
			// showdown

			ArrayList<TablePlayer> posWin = new ArrayList<TablePlayer>();
			HandEvaluator he = new HandEvaluator();
			Card[] cards;
			int value;
			int max=0;
			for(TablePlayer p:plys){
				if(p!=null){
					if(!p.isFold()){
						cards = connect(comu, p.getCards());
						he.setCards(cards);
						value = he.getValue();
						p.setCurrentHandValue(value);
						if(value>max){
							max = value;
							posWin  = new ArrayList<TablePlayer>();
							posWin.add(p);
							
						}else if(value==max){
							posWin.add(p);
						}
					}
				}
			}
			//gives out the wins. splits if needed
			int divisor = posWin.size();
			for(int i=0; i<divisor;i++){
				posWin.get(i).addMoney(pot.getMoney()/divisor);
			}
			
			//clears the pot
			pot.setMoney(0);
			
			
			
			
		}

	}

	/**
	 * puts the betted money from the players in the pot
	 */
	private void putBetsInPot() {
		// TODO Auto-generated method stub
		for (TablePlayer p : plys) {
			if (p != null) {
				pot.addMoney(p.getBetted());
				p.setBetted(0);
			}
		}

	}


	/**
	 * performs a betround
	 * @param si the index of the player to start
	 */
	private void betRound(int si, int minToCall) {
		int last = (si - 1) % maxPlayerCount;
		Event e = null;
		TablePlayer cur;

		// whole loop through all player
		while (si != last) {
			cur = plys[si];
			if (cur != null) {

				if (!(cur.isAllIn() || cur.isFold())&&(actvPl>=2)) {

					double startTime = System.currentTimeMillis();
					// waits till an event is present or 5 seconds
					while (e == null && (System.currentTimeMillis() - startTime < 5000)) {
						e = cur.getEvent();
					}
					if (e == null) {
						// if no event has been given, the player is force-fold
						cur.setFold(true);

					} else {
						json = JSONObject.fromObject(e.getJSONString());
						switch (e.getType()) {

						case ALLIN:
							cur.setAllIn(true);
							actvPl--;
							int mon = cur.pay(cur.getMoney());
							cur.setBetted(cur.getBetted() + mon);
							if (cur.getBetted() > minToCall) {
								betRound((si + 1) % maxPlayerCount, cur.getBetted());
								return;
							}
							break;

						case BET:
							int bet = json.getInt(Event.EVENT_AMOUNT);
							bet = cur.pay(bet);
							cur.setBetted(cur.getBetted() + bet);
							if (cur.getBetted() > minToCall) {
								betRound((si + 1) % maxPlayerCount, cur.getBetted());
								return;
							}
							break;

						case CALL:
							int paid = cur.getBetted();
							cur.setBetted(paid + cur.pay(minToCall) - paid);
							break;

						case CHECK:
							if(cur.getBetted()<minToCall){
								//Error handling
							}
							break;

						case FOLD:
							cur.setFold(true);
							actvPl--;
							break;

						case RAISE:
							int tobet = json.getInt(Event.EVENT_AMOUNT);
							tobet = cur.pay(tobet);
							cur.setBetted(cur.getBetted() + tobet);
							if (cur.getBetted() > minToCall) {
								betRound((si + 1) % maxPlayerCount, cur.getBetted());
								return;
							}
							break;

						default:
							break;

						}
					}
				}
			}
			si = (si + 1) & maxPlayerCount;
		}

	}

	/**
	 * Subtracts the blind money and adds it to the pot
	 */
	private void payBlinds() {

		smallBlindIndex = (dealInd + 1) % maxPlayerCount;
		while (plys[smallBlindIndex] == null) {
			smallBlindIndex = (smallBlindIndex + 1) % maxPlayerCount;
		}
		plys[smallBlindIndex].setBetted(plys[smallBlindIndex].pay(smallBlind));

		bigBlindIndex = (smallBlindIndex + 1) % maxPlayerCount;
		while (plys[bigBlindIndex] == null) {
			bigBlindIndex = (bigBlindIndex + 1) % maxPlayerCount;
		}
		plys[bigBlindIndex].setBetted(plys[bigBlindIndex].pay(bigBlind));
	}

}
