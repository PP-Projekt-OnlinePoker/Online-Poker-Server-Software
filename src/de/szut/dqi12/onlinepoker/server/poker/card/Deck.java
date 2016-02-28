package de.szut.dqi12.onlinepoker.server.poker.card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> deck;
	
	public Deck(){
		init();
	}

	private void init(){
		deck = new ArrayList<Card>(52);
		deck.add(new Card("2d"));
		deck.add(new Card("2h"));
		deck.add(new Card("2c"));
		deck.add(new Card("2s"));
		deck.add(new Card("3d"));
		deck.add(new Card("3h"));
		deck.add(new Card("3c"));
		deck.add(new Card("3s"));
		deck.add(new Card("4d"));
		deck.add(new Card("4h"));
		deck.add(new Card("4c"));
		deck.add(new Card("4s"));
		deck.add(new Card("5d"));
		deck.add(new Card("5h"));
		deck.add(new Card("5c"));
		deck.add(new Card("5s"));
		deck.add(new Card("6d"));
		deck.add(new Card("6h"));
		deck.add(new Card("6c"));
		deck.add(new Card("6s"));
		deck.add(new Card("7d"));
		deck.add(new Card("7h"));
		deck.add(new Card("7c"));
		deck.add(new Card("7s"));
		deck.add(new Card("8d"));
		deck.add(new Card("8h"));
		deck.add(new Card("8c"));
		deck.add(new Card("8s"));
		deck.add(new Card("9d"));
		deck.add(new Card("9h"));
		deck.add(new Card("9c"));
		deck.add(new Card("9s"));
		deck.add(new Card("Td"));
		deck.add(new Card("Th"));
		deck.add(new Card("Tc"));
		deck.add(new Card("Ts"));
		deck.add(new Card("Jd"));
		deck.add(new Card("Jh"));
		deck.add(new Card("Jc"));
		deck.add(new Card("Js"));
		deck.add(new Card("Qd"));
		deck.add(new Card("Qh"));
		deck.add(new Card("Qc"));
		deck.add(new Card("Qs"));
		deck.add(new Card("Kd"));
		deck.add(new Card("Kh"));
		deck.add(new Card("Kc"));
		deck.add(new Card("Ks"));
		deck.add(new Card("Ad"));
		deck.add(new Card("Ah"));
		deck.add(new Card("Ac"));
		deck.add(new Card("As"));
		Collections.shuffle(deck);
	}
	
	/**
	 * returns a card
	 * deletes that card from the deck
	 * @return
	 */
	public Card draw(){
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}
	
	public void reInit(){
		init();
	}
}
