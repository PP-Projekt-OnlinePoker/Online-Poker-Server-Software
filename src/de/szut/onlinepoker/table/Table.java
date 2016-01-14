package de.szut.onlinepoker.table;

import java.util.ArrayList;

import de.szut.onlinepoker.card.Card;
import de.szut.onlinepoker.card.Deck;
import de.szut.onlinepoker.game.Player;
import de.szut.onlinepoker.game.Pot;


public class Table {

	
	private Player activePlayer;
	private ArrayList<Player> plys;
	private ArrayList<Pot> pots; //main and side pots
	private Deck deck;
	private int smallBlind;
	private int bigBlind;
	private int dealInd;
	private Card[] comu;
	private int roundPlrNr;
	private int actvPlrIndx;
	private int notPassed;
	private final int ID;
	private int maxBet;
	private int maxPlayerCount;
	
	public int getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}

	public void setMaxPlayerCount(int maxPlayerCount) {
		this.maxPlayerCount = maxPlayerCount;
	}

	public Table(int id){
		ID=id;
		this.dealInd=0;
		//deck = new Deck();
		//newRound();
	}
	
	public int getID(){
		return this.ID;
	}
	
	public void newRound(){
		deck.reInit();
		roundPlrNr = plys.size();
		notPassed=roundPlrNr;
		dealInd = (dealInd+1)%roundPlrNr;
		
	}
	
	/**
	 * sets the small blind and indirect the big blind
	 * @param smllB
	 */
	public void setSmallBlind(int smllB){
		this.smallBlind=smllB;
		this.bigBlind=2*smallBlind;
	}
	
	public int getSmallBlind(){
		return smallBlind;
	}
	
	public int getBigBlind(){
		return bigBlind;
	}
	
	/**
	 * adds a player to the table
	 * @param p
	 */
	public void addPlayer(Player p){
		plys.add(p);
	}
	
	/**
	 * removes a player from the table
	 * @param p
	 */
	public void removePlayer(Player p){
		plys.remove(p);
	}
	
	
	/**
	 * gives each player two cards
	 */
	public void giveCards(){
		int start = dealInd%roundPlrNr;
		for(int i=0;i<2;i++){
			dealInd = (start+1)%roundPlrNr;
			while(dealInd!=start){
				plys.get(dealInd).setCard(deck.draw(), i);
				plys.get(dealInd).setIngame(true);
				dealInd = (dealInd+1)%roundPlrNr;
			}
		}
		actvPlrIndx = (dealInd+2)%roundPlrNr;
	}
	
	
	

	/**
	 * connects two Card arrays
	 * @param c1
	 * @param c2
	 * @return
	 */
	public Card[] connect(Card[] c1, Card[] c2){
		Card[] res = new Card[c1.length+c2.length];
		for(int i=0;i<c1.length;i++){
			res[i]=c1[i];
		}
		for(int i=0;i<c2.length;i++){
			res[i+c1.length] = c2[i];
		}
		return res;
	}
	
	/**
	 * table ID
	 * @return
	 */
	
	public int getDealerIndex(){
		return dealInd;
	}
	
	public Player getActivePlayer(){
		return activePlayer;
	}
	
	public void foldPlayer(){
		
		activePlayer = getActivePlayer();
		activePlayer.setIngame(false);
		notPassed--;
		for(Pot p:pots){
			p.removePlayer(activePlayer);
		}
	}
	
	public void raisePlayer(int raiseTo){
		
	}
	
	private void nextActive(){
		
	}
	
	public void destroy(){
		
	}
	
}
