package de.szut.onlinepoker.card;

import java.security.InvalidParameterException;
import java.util.ArrayList;



/**
 * Class for evaluating Hands
 * higher values means better hands
 * also provides type of hand(e.g. pair etc..)
 * @author Fabian
 *
 */
public class HandEvaluator {

	private Card[] cards;
	private int value;
	private int type;
	private ArrayList<Card> temp;
	private ArrayList<Card> oth;

	public static int ROYALFLUSH = 10;
	public static int STRAIGHT_FLUSH = 9;
	public static int FOUR_OF_A_KIND = 8;
	public static int FULL_HOUSE = 7;
	public static int FLUSH = 6;
	public static int STRAIGHT = 5;
	public static int THREE_OF_A_KIND = 4;
	public static int TWO_PAIRS = 3;
	public static int PAIR = 2;
	public static int HIGH_CARD = 1;

	public HandEvaluator(Card[] cardss) {
		this.cards = cardss;
		evaluateCards();
		temp = new ArrayList<Card>();
		oth = new ArrayList<Card>();
	}
	
	public HandEvaluator(){
		
		temp = new ArrayList<Card>();
		oth = new ArrayList<Card>();
	}

	public void setCards(Card[] ca){
		cards = ca;
		evaluateCards();
	}
	
	/**
	 * evaluates the cards and sets a value representing the strength of a
	 * hand the higher the value, the stronger the hand
	 */
	private void evaluateCards() {
		
		if (cards.length != 7) {
			throw new InvalidParameterException("number of cards must be 7");
		}
		boolean flush = false;
		boolean three = false;
		int size = 0;
		int kicker = 0;
		
		temp.clear();
		oth.clear();
		
		
		sortByColour(cards);

		// if a flush exists, the card in the middle
		// of the sorted array must have the same colour as the flush
		int flushcolour = cards[3].getColourValue();

		// add all cards of the same colour
		for (Card c:cards) {
			if (c.getColourValue() == flushcolour) {
				temp.add(c);
			}
		}

		// checks if there are 5 or more cards with the flushcolour
		if (temp.size() >=5) {
			flush = true;
		} else {
			temp.clear();
			flush = false;
		}

		// if there is a flush, straight flush and royal flush
		// are the only possible higher ranks than the flush itself
		// so we only check for them
		// four of a kind and full house aren't possible anymore
		if (flush) {
			size = temp.size();
			Card[] toCheck = new Card[size];
			toCheck = temp.toArray(toCheck);
			
			sortByRank(toCheck);

			if (toCheck[0].getNumberValue() == 12 && toCheck[4].getNumberValue()==8) {
				this.type = HandEvaluator.ROYALFLUSH;
				value = 0b1100000000000000;
				return;
			}
			
			// straight flush
			int q = 0;
			do{
				if ((toCheck[q].getNumberValue() - toCheck[q + 4].getNumberValue()) == 4) {
					// royal flush
					this.type = HandEvaluator.STRAIGHT_FLUSH;
					value = 0b1000000000000000 ^ toCheck[q].getNumberValue();
					return;
				}
				q++;
			}while(q<toCheck.length-4);
			if(toCheck[toCheck.length-1].getNumberValue() == 0 && toCheck[toCheck.length-4].getNumberValue()== 3 ){
				if(toCheck[0].getNumberValue()==12){
					this.type = HandEvaluator.STRAIGHT_FLUSH;
					value = 0b1000000000000000 ^ 256 * toCheck[toCheck.length-1].getNumberValue();
					return;
				}
			}
			
			value = 0b0101000000000000 ^ (256*toCheck[0].getNumberValue() + 64 * toCheck[1].getNumberValue()
					+ 32 * toCheck[2].getNumberValue() +  8* toCheck[3].getNumberValue()
					+ toCheck[4].getNumberValue());
			type = FLUSH;

			// no flush
		}
		temp.clear();
		sortByRank(cards);
		// four of a kind
		int poss = cards[3].getNumberValue();
		for (Card c : cards) {
			if (c.getNumberValue() == poss) {
				temp.add(c);

			}else{
				oth.add(c);
			}
		}
		if (temp.size() == 4) {
			type = FOUR_OF_A_KIND;
			value = 0b0111000000000000 ^ (256*temp.get(0).getNumberValue())+oth.get(0).getNumberValue();
			return;
		}
		temp.clear();
		oth.clear();
		// full house
		int j = 1;
		temp.add(cards[0]);
		while (j < 7 && temp.size() != 3) {
			if (cards[j].getNumberValue() != temp.get(0).getNumberValue()) {
				temp.clear();
			}
			temp.add(cards[j]);
			j++;
		}
		
		if (temp.size() == 3) {
			for (Card c : cards) {
				if (c.getNumberValue() != temp.get(0).getNumberValue()) {
					oth.add(c);
				}
			}
			for (int i = 0; i < 3; i++)
				if (oth.get(i).getNumberValue() == oth.get(i + 1).getNumberValue()) {
					type = FULL_HOUSE;
					value = 0b0110000000000000 ^ (256*temp.get(0).getNumberValue()+256)+oth.get(i).getNumberValue();
					return;
				}

			if (flush) {
				type = FLUSH;
				return;
			}
			three = true;
			value = 0b0011000000000000 ^ (256*temp.get(0).getNumberValue()) + 16 * oth.get(0).getNumberValue()
					+ oth.get(1).getNumberValue();

		}
		if (flush) {
			type = FLUSH;
			return;
		}

		temp.clear();

		// straight
		j = 1;
		temp.add(cards[0]);
		int curVal;
		int lastVal = cards[0].getNumberValue();
		while (j < 7 && temp.size() != 5) {
			curVal = cards[j].getNumberValue();
			if (lastVal - curVal > 1) {
				temp.clear();
				temp.add(cards[j]);
			}else if(lastVal - curVal == 1){
				temp.add(cards[j]);
				
			}
			lastVal = curVal;
			j++;
		}
		size = temp.size();
		if (size == 5) {
			type = STRAIGHT;
			value = 0b0100000000000000 ^  (temp.get(0).getNumberValue());
			return;

			// special case ace low
		} else if (size == 4) {
			if (cards[0].getNumberValue() == 12 && temp.get(0).getNumberValue() == 3) {
				type = STRAIGHT;
				value = 0b0100000000000000 ^ (temp.get(0).getNumberValue());
				return;
			}
		}

		// three of a kind. already checked before
		if (three) {
			type = THREE_OF_A_KIND;
			return;
			// TODO
		}

		temp.clear();
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i].getNumberValue() == cards[i + 1].getNumberValue()) {
				temp.add(cards[i]);
				temp.add(cards[i + 1]);

			}
		}
		size = temp.size();

		// two pair
		if (size >= 4) {
			type = TWO_PAIRS;
			int frstPrVl = temp.get(0).getNumberValue();
			int scndPrVl = temp.get(2).getNumberValue();
			for (Card c : cards) {
				kicker = c.getNumberValue();

				if (kicker != frstPrVl && kicker != scndPrVl) {
					value = 0b0010000000000000 ^ (32*frstPrVl) + kicker;
				}
			}
			return;
		}
		// pair
		if (size >= 2) {
			type = PAIR;
			for (Card c : cards) {
				kicker = c.getNumberValue();
				if (kicker != temp.get(0).getNumberValue()) {
					value = 0b0001000000000000 ^ (32*temp.get(0).getNumberValue()) + kicker;
					return;
				}
			}
		}

		// high card
		type = HIGH_CARD;
		value = 0b0000000000000000 ^ cards[0].getNumberValue() + 1;

		return;

	}

	/**
	 * sorts the cards by rank with ace high the highest card will have index 0
	 */
	private Card[] sortByRank(Card[] c) {
		Card tempCard;
		int k;
		for (int i = 0; i < c.length; i++) {
			k = i;
			while (k > 0 && (c[k].getNumberValue() > c[k - 1].getNumberValue())) {
				tempCard = c[k];
				c[k] = c[k - 1];
				c[k - 1] = tempCard;
				k--;
			}

		}
		return c;
	}

	/**
	 * sorts the cards by colour diamonds spades clubs spades from low to high
	 */
	private Card[] sortByColour(Card[] c) {

		Card tempCard;
		int k;
		for (int i = 0; i < c.length; i++) {
			k = i;
			while (k > 0 && (c[k].getColourValue() > c[k - 1].getColourValue())) {
				tempCard = c[k];
				c[k] = c[k - 1];
				c[k - 1] = tempCard;
				k--;
			}

		}
		return c;
	}

	
	/**
	 * 
	 * @return the type of the hand
	 */
	public int getType() {
		return type;
	}

	
	/**
	 * 
	 * @return the value of the hand
	 */
	public int getValue() {
		return value;
	}
}
