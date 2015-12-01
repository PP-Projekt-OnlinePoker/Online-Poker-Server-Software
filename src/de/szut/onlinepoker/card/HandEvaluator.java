package de.szut.onlinepoker.card;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class HandEvaluator {

	private Card[] cards;
	private int value;
	private int type;
	
	
	public static int ROYALFLUSH=10;
	public static int STRAIGHT_FLUSH=9;
	public static int FOUR_OF_A_KIND=8;
	public static int FULL_HOUSE=7;
	public static int FLUSH=6;
	public static int STRAIGHT=5;
	public static int THREE_OF_A_KIND=4;
	public static int TWO_PAIRS=3;
	public static int PAIR=2;
	public static int HIGH_CARD=1;
	
	
	
	public HandEvaluator(Card[] cardss){
		if(cards.length!=7){
			throw new InvalidParameterException("number of cards must be 7");
		}
		this.cards = cardss;
		evaluateCards();
	}
	
	/**
	 * evaluates the cards and returns a value
	 * representating the strength of a hand
	 * the higher the value, the stronger the hand
	 */
	private int evaluateCards(){
		
		ArrayList<Card> temp = new ArrayList<Card>();
		boolean flush=false;
		
		cards = sortByColour(cards);
		
		//if a flush exists, the card in the middle
		//of the sorted array must have the same colour as the flush
		int flushcolour = cards[3].getColourValue();
		//add all cards of the same colour
		for(Card c:cards){
			if(c.getColourValue()==flushcolour){
				temp.add(c);
			}
		}
		
		//checks if there are 5 or more cards with the flushcolour
		if(temp.size()>=5){
			flush=true;
		}
		else{
			temp.clear();
			flush=false;
		}
		
		//if there is a flush, straight flush and royal flush
		//are the only possible higher ranks than the flush itself
		//so we only check for them
		//four of a kind and full house aren't possible
		
		if(flush){
			
			
			Card[] toCheck = sortByRank(temp.toArray(new Card[temp.size()]));
			if(toCheck[0].getNumberValue()==12 && toCheck[4].getNumberValue()==8){
				this.type=this.ROYALFLUSH;
				return 100;
			}
			for(int i = 0;i<temp.size()-5;i++){
				if(toCheck[i].getNumberValue()-toCheck[i+5].getNumberValue()==4){
					this.type=STRAIGHT_FLUSH;
					//the highest value a card within a straight flush 
					//can have is 11
					//so 99 is the highest value a straight flush can reach
					//while the lowest is 91
					return 88+toCheck[0].getNumberValue();
				}
				
			}
			
			
		}else{
			
		}
		
		
		
		
		
		boolean royal;
		boolean straight=false;
		int straightnumber=0;
		
		int i=0;
		while(i<6 && temp.size()!=5){
			int curCarVal = cards[i].getNumberValue();
			int nextCarVal = cards[i+1].getNumberValue();
			if(curCarVal-nextCarVal == 1){
				straightnumber++;
				temp.add(cards[i]);
			}
		}
		
		
		
		
		return 0;
		
	}
	
	
	
	
	/**
	 * sorts the cards by rank with ace high
	 * the highest card will have index 0
	 */
	private Card[] sortByRank(Card[] c){
		
		
		return c;
	}
	
	/**
	 * sorts the cards by colour diamonds spades clubs spades 
	 * from low to high
	 */
	private Card[] sortByColour(Card[] ca){
		
		
		return ca;
	}
	
	
	public int getType(){
		return type;
	}
}
