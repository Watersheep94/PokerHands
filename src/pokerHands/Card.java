package pokerHands;

public class Card implements Comparable<Card> {
	
	private int value;
	private char suit;
	
	public Card(String card) {
		this.value = convertCardValueToNumber(card.charAt(0));
		this.suit = card.charAt(1);
	}
	public int getValue() {
		return value;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int convertCardValueToNumber(char value) {
		
		int intValue = 0;
		switch (value) {
			case 'T':
				intValue = 10;
				break;
			case 'J':
				intValue = 11;
				break;
			case 'Q':
				intValue = 12;
				break;
			case 'K':
				intValue = 13;
				break;
			case 'A':
				intValue = 14;
				break;
			default:
				intValue = Character.getNumericValue(value);
				break;
		}
		return intValue;
	}
	
	@Override
	public int compareTo(Card o) {
		if (this.value > o.value) {
			return 1;
		} else if (this.value < o.value ){
			return -1;
		} else {
			return 0;
		}
	}
	
	
}