package de.szut.dqi12.onlinepoker.server.poker.card;


public class Card {

	private int value;
	private int colnumber;
	private String identifier;
											   //0	1	2	3	4	5	 6	 7	 8	 9   10	 11 12
	private static char[] valuemap = new char[]{'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
	private static char[] colormap = new char[]{'h', 'd','c','s'};
	
	/**
	 * Takes the identifier as argument
	 * The String should have the length of to
	 * The first char is the identifier for the number
	 * 2,3,4,5,6,7,8,9,(T)en,(J)ack,(Q)ueen,(K)ing,(A)ce
	 * The second for the colour
	 * (h)earts, (s)pades, (c)lubs, (d)iamonds
	 * @param ident
	 */
	public Card(String ident){
		identifier = ident;
		
		//assign the card a value of 0-12
		value = 0;
		char vc = ident.toUpperCase().charAt(0);
		while(valuemap[value]!=vc){
			value++;
		}
		
		//map the colour to a number of 0-3
		colnumber = 0;
		char colo = ident.toLowerCase().charAt(1);
		while(colormap[colnumber]!=colo){
			colnumber++;
		}
		
	}
	
	/**
	 * Returns the identifier of the card. 
	 * e.g. "7d" or "Qh"
	 * @return
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	/**
	 * returns the value of the card
	 * 0 for a 2, 1 for a 3 and so on
	 * @return
	 */
	public int getNumberValue(){
		return value;
	}
	
	/**
	 * return the colour value
	 * the worth of the colours in the game are all the same,
	 * but its easier to determine a flush with numbers
	 */
	public int getColourValue(){
		return colnumber;
	}
	
	
	@Override
	public String toString(){
		return getIdentifier();
	}
}
