package de.szut.dqi12.onlinepoker.server.poker.player;

import de.szut.dqi12.onlinepoker.server.poker.card.Card;

public class TablePlayer {
	private boolean fold;
	private Card[] cards;
	private boolean allIn;

	public TablePlayer() {
		//Hole-Cards des Spielers
		cards = new Card[2];
	}

	public void setFold(boolean f){
		fold = f;
	}
	
	public boolean isFold(){
		return fold;
	}
	
	public void setCards(Card[] cs){
		cards=cs;
	}
	
	public void setCard(Card c, int index){
		cards[index]=c;
	}
	
	public Card[] getCards(){
		return cards;
	}
	
	public void setAllIn(boolean all){
		allIn=all;
	}
	
	public boolean isAllIn(){
		return allIn;
	}
}
