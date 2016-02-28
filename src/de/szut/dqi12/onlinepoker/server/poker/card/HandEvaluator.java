package de.szut.dqi12.onlinepoker.server.poker.card;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class HandEvaluator {

	private Card[] cards;
	private int value;
	private int type;

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

	public HandEvaluator(Card[] cards) {
		if (cards.length != 7) {
			throw new InvalidParameterException("number of cards must be 7");
		}
		this.cards = cards;
		evaluateCards();
	}

	/**
	 * evaluates the cards and returns a value representating the strength of a
	 * hand the higher the value, the stronger the hand
	 */
	private void evaluateCards() {

		ArrayList<Card> temp = new ArrayList<Card>();
		ArrayList<Card> oth = new ArrayList<Card>();
		boolean flush = false;
		boolean three = false;
		int size = 0;
		int kicker = 0;

		cards = sortByColour(cards);

		// if a flush exists, the card in the middle
		// of the sorted array must have the same colour as the flush
		int flushcolour = cards[3].getColourValue();

		// add all cards of the same colour
		for (Card c : cards) {
			if (c.getColourValue() == flushcolour) {
				temp.add(c);
			}
		}

		// checks if there are 5 or more cards with the flushcolour
		if (temp.size() >= 5) {
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

			Card[] toCheck = sortByRank(temp.toArray(new Card[temp.size()]));

			value = 25000 * toCheck[0].getNumberValue() + 2000 * toCheck[1].getNumberValue()
					+ 200 * toCheck[2].getNumberValue() + 20 * toCheck[3].getNumberValue()
					+ 2 * toCheck[4].getNumberValue();
			// straight flush
			for (int i = 0; i < (toCheck.length - 5); i++) {
				if (toCheck[i].getNumberValue() - toCheck[i + 4].getNumberValue() == 4) {
					// royal flush
					if (toCheck[1].getNumberValue() == 11) {
						this.type = HandEvaluator.ROYALFLUSH;
						value = 60000000;
						return;
					}
					this.type = HandEvaluator.STRAIGHT_FLUSH;
					value = 60000000-toCheck[3].getNumberValue();
					return;
				}

			}

			// no flush
		}

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
			value = 4300000* (temp.get(0).getNumberValue()+1)+oth.get(0).getNumberValue();
			return;
		}
		temp.clear();

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
			for (int i = 0; i < 2; i++)
				if (oth.get(i).getNumberValue() == oth.get(i + 1).getNumberValue()) {
					type = FULL_HOUSE;
					value = 325000 * (temp.get(0).getNumberValue()+1)+oth.get(0).getNumberValue();
					return;
				}

			if (flush) {
				type = FLUSH;
				return;
			}
			three = true;
			value = 4000 * (temp.get(0).getNumberValue()) + 13 * oth.get(0).getNumberValue()
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
			}else if(lastVal - curVal == 1){
				temp.add(cards[j]);
				
			}
			lastVal = curVal;
			j++;
		}
		size = temp.size();
		if (size == 5) {
			type = STRAIGHT;
			value = 20000 * (temp.get(0).getNumberValue());
			return;

			// special case ace low
		} else if (size == 4) {
			if (cards[0].getNumberValue() == 12 && temp.get(0).getNumberValue() == 3) {
				type = STRAIGHT;
				value = 20000 * (temp.get(0).getNumberValue());
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
					value = 300 * (frstPrVl) + kicker;
				}
			}
			return;
		}
		// pair
		if (size == 2) {
			type = PAIR;
			for (Card c : cards) {
				kicker = c.getNumberValue();
				if (kicker != temp.get(0).getNumberValue()) {
					value = 20 * (temp.get(0).getNumberValue() + 1) + kicker;
					return;
				}
			}
		}

		// high card
		type = HIGH_CARD;
		value = cards[0].getNumberValue() + 1;

		return;

	}

	/**
	 * sorts the cards by rank with ace high the highest card will have index 0
	 */
	public Card[] sortByRank(Card[] c) {
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
	public Card[] sortByColour(Card[] c) {

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

	public int getType() {
		return type;
	}

	public int getValue() {
		return value;
	}
}
