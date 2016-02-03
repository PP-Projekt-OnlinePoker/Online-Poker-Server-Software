package de.szut.onlinepoker.player;

import java.security.InvalidParameterException;

import de.szut.dqi12.holdem.helper.Event;
import de.szut.onlinepoker.card.Card;

/**
 * Class for representing a player at a table
 * @author Fabian
 *
 */
public class TablePlayer{

	
	private boolean fold;
	private Card[] cards;
	private boolean allIn;
	private int money;
	private final int ID;
	private int betted;
	private Event event;
	private int curHandValue;
	
	
	/**
	 * 
	 * @param val sets the value of the players hand
	 */
	public void setCurrentHandValue(int val){
		curHandValue=val;
	}
	
	/**
	 * 
	 * @return players hand value
	 */
	public int getcurHandValue(){
		return curHandValue;
	}
	
	public TablePlayer(int id){
		ID=id;
		betted = 0;
		fold = true;
		allIn = false;
		cards = new Card[2];
	}
	
	/**
	 * sets the event the player should perform
	 * @param e
	 */
	public void setEvent(Event e){
		event = e;
	}
	
	
	/**
	 * return the event to perform
	 * event is null after
	 * @return
	 */
	public Event getEvent(){
		Event temp = event;
		event = null;
		
		return temp;
	}
	
	/**
	 * 
	 * @param amount sets the amount of money the player already betted
	 */
	public void setBetted(int amount){
		betted = amount;
	}
	
	
	public int getBetted(){
		return betted;
	}
	
	/**
	 * 
	 * @param amount sets the money the player has
	 */
	public void setMoney(int amount){
		money = amount;
	}
	
	public int getMoney(){
		return money;
	}
	
	public int getID(){
		return ID;
	}
	
	/**
	 * folded player wont be able to perfom actions in the gameround
	 * @param f folds or unfold the player
	 * 
	 */
	public void setFold(boolean f){
		fold = f;
	}
	
	public boolean isFold(){
		return fold;
	}
	
	/**
	 * sets the players cards
	 * @param cs
	 */
	public void setCards(Card[] cs){
		if(cs.length!=2){
			throw new InvalidParameterException("player must have exactly 2 cards");
		}
		cards=cs;
	}
	
	public void setCard(Card c, int index){
		cards[index]=c;
	}
	
	public Card[] getCards(){
		return cards;
	}
	
	/**
	 * sets a flag if a player is allin
	 * player gone allin cant perform actions
	 * @param all
	 */
	public void setAllIn(boolean all){
		allIn=all;
	}
	
	public boolean isAllIn(){
		return allIn;
	}

	/**
	 * adds money to the player. e.g. if he wins a hand
	 * @param amount 
	 */
	public void addMoney(int amount) {
		// TODO Auto-generated method stub
		money+=amount;
	}

	
	/**
	 * goes allin, sets money to 0 and returns the money he pays
	 * @return
	 */
	public int allIn(){
		allIn = true;
		int temp = money;
		money = 0;
		return temp;
	}
	
	
	/**
	 * tries to pay an amount of money
	 * goes all in if not sufficient
	 * @param amount
	 * @return the amount of money that had been paid
	 */
	public int pay(int amount){
		if(money>amount){
			money-=amount;
			return amount;
		}else{
			return allIn();
		}
	}
	
}
