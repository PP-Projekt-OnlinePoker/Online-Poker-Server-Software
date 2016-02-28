package de.szut.dqi12.onlinepoker.server.poker.table;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import de.szut.dqi12.onlinepoker.server.poker.card.Card;
import de.szut.dqi12.onlinepoker.server.poker.card.Deck;
import de.szut.dqi12.onlinepoker.server.poker.player.TablePlayer;


public class Table {

	
	private TablePlayer activePlayer;
	private TablePlayer[] plys;
	private ArrayList<Pot> pots; //main and side pots
	private Deck deck;
	private final int smallBlind;
	private final int bigBlind;
	private int dealInd;
	private Card[] comu;
	private int roundPlrNr;
	private int actvPlrIndx;
	private int notPassed;
	private final int ID;
	private final int maxBet;
	private final int maxPlayerCount;
	
	
	
	
	public int getMaxBet() {
		return maxBet;
	}

	

	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}

	
	/**
	 * TableConfiguration is final and cant be altered afterwards
	 * 
	 * @param cfg
	 */
	public Table(TableConfiguration cfg){
		ID=cfg.getID();
		this.dealInd=0;
		maxPlayerCount = cfg.getMaxPlayer();
		if(maxPlayerCount>9 || maxPlayerCount<0){
			throw new InvalidParameterException("Invalid number of player");
		}
		plys = new TablePlayer[maxPlayerCount];
		smallBlind = cfg.getSmallBlind();
		bigBlind = 2*smallBlind;
		maxBet = cfg.getMaxBet();
		deck = new Deck();
		//newRound();
	}
	
	public int getID(){
		return this.ID;
	}
	
	public void newRound(){
		deck.reInit();
		roundPlrNr = 0;
		for(TablePlayer p:plys){
			if(p!=null){
				roundPlrNr++;
			}
		}
		notPassed=roundPlrNr;
		dealInd = (dealInd+1)%roundPlrNr;
		
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
	public void addPlayer(TablePlayer p){
		for(TablePlayer play:plys){
			if(play==null){
				play=p;
			}
		}
	}
	
	/**
	 * removes a player from the table
	 * @param p
	 */
	public void removePlayer(TablePlayer p){
		for(TablePlayer play:plys){
			if(play==p){
				play = null;
			}
		}
	}
	
	
	/**
	 * gives each player two cards
	 */
	public void giveCards(){
		int start = dealInd%roundPlrNr;
		for(int i=0;i<2;i++){
			dealInd = (start+1)%roundPlrNr;
			while(dealInd!=start){
				plys[dealInd].setCard(deck.draw(), i);
				plys[dealInd].setFold(false);
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
	
	public TablePlayer getActivePlayer(){
		return activePlayer;
	}
	
	public void foldPlayer(){
		
		activePlayer = getActivePlayer();
		activePlayer.setFold(true);
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
