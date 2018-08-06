package pokerHands;


public class Hand {
	
	private Card[] cards = new Card[5];
	private RankValue[] rankValues = new RankValue[10];
	
	// Initialize an array to map to each card value. 
	// This acts as a HashMap to track how many of each card value is encountered.
	int[] cardValues = new int[15]; 
	
	public Hand(Card[] cards) {
		this.cards = cards;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public RankValue[] getRankValues() {
		return rankValues;
	}
	
	/*
	 * checks whether the rank is fulfilled and the value of the cards for that rank
	 */
	public class RankValue {
		boolean hasRank;
		int cardValue;
		
		public RankValue(boolean hasRank, int cardValue) {
			this.hasRank = hasRank;
			this.cardValue = cardValue;
		}
	}
	
	/*
	 * Sort the cards from lowest to highest value. 
	 */
	public void sortCards() {	
		//use insertion sort
		int i = 1;
		while (i < cards.length) {
			int j = i;
			while (j > 0 && cards[j-1].compareTo(cards[j]) == 1) {
				//swap card j and card j-1
				Card temp = cards[j];
				cards[j] = cards[j-1];
				cards[j-1] = temp;
		        j--;
			}
			i++;
		}
	}
	
	/*
	 * Set whether the hand has a particular rank and the rank's value
	 * Ex. rankValues[1] corresponds to whether we have a Pair and the card value of that pair
	 */
	public void setRanksAndValues() {
		sortCards();
		findCardValueFrequency();
		
		rankValues[1] = countPairs(1);			// one pair
		rankValues[2] = countPairs(2);			// two pairs
		rankValues[3] = isKindValues(3);		// three of a kind
		rankValues[4] = isStraight();			// straight
		rankValues[5] = isFlush();				// flush
		
		//Determine if its a full house by reusing previous RankValues
		if (rankValues[1].hasRank == true && rankValues[3].hasRank == true) {
			rankValues[6] = new RankValue(true, rankValues[3].cardValue);
		} else {
			rankValues[6] = new RankValue(false, 0);
		}

		rankValues[7] = isKindValues(4);		// four of a kind
		rankValues[9] = isRoyalFlush();			// royal flush
		rankValues[8] = isStraightFlush();		// straight flush
		
		//We determine Highest Value card at the end in case other cards are already used for higher ranks
		rankValues[0] = getHighestValueCard(); 
	}
	
	private int getSumOfCards() {
		int sum = 0;
		for (Card c : cards) {
			sum += c.getValue();
		}
		return sum;
	}
	
	/*
	 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	 * @return RankValue that determines if this rank is true and the card value associated with it
	 */
	private RankValue isRoyalFlush() {
		
		RankValue straightFlush = isStraightFlush();
		if (getSumOfCards() == 60 && straightFlush.hasRank) {
			return new RankValue(true,60);
		} else {
			return new RankValue(false,0);
		}
	}
	
	/*
	 * Straight Flush: All cards are consecutive values of same suit.
	 * @return RankValue that determines if this rank is true and the card value associated with it
	 */
	private RankValue isStraightFlush() {
		
		RankValue straight = isStraight();
		RankValue flush = isFlush();
		
		if (straight.hasRank && flush.hasRank) {
			return new RankValue(true, getSumOfCards());
		} else {
			return new RankValue(false,0);
		}
	}
	
	/*
	 * Straight: All cards are consecutive values.
	 * @return RankValue that determines if this rank is true and the card value associated with it
	 */
	private RankValue isStraight() {
		for (int i = 1; i < cards.length; i++) {
			if (cards[i-1].getValue() + 1 != cards[i].getValue()) {
				return new RankValue(false,0);
			} 
		}
		return new RankValue(true, getSumOfCards());
	}
	
	/*
	 * Flush: All cards of the same suit.
	 * @return RankValue that determines if this rank is true and the card value associated with it
	 */
	private RankValue isFlush() {
		for (int i = 1; i < cards.length; i++) {
			if (cards[0].getSuit() != cards[i].getSuit()) {
				return new RankValue(false,0);
			}
		}
		return new RankValue(true, getSumOfCards());
	}
	
	private void findCardValueFrequency() {
		for (int i = 0; i < cards.length; i++) {
			int value = cards[i].getValue();
			cardValues[value]++;
		}
	}
	
	/*
	 * Used to find Four of a Kind and Three of a Kind
	 * @param N cards of the same value.
	 * @return RankValue that determines if this rank is true and the card value associated with it
	 * 
	 */
	private RankValue isKindValues(int n) {
		
		for (int i = 0; i < cards.length; i++) {
			int value = cards[i].getValue();
			if (cardValues[value] == n) {
				return new RankValue(true, value);
			} 
		}
		
		return new RankValue(false, 0);
	}
	
	/*
	 *  Count the pairs in cards.
	 *  @param The number of pairs we want to find
	 *  @return RankValue that determines if this rank is true and the card value associated with it
	 */
	private RankValue countPairs(int pairCount) {

		int pairs = 0;
		int value = 0;
		for (int i = 0; i < cardValues.length; i++) {
			if (cardValues[i] == 2) {
				pairs++;
				value += i; //add the card value of that pair
			}
			
			if (pairs == pairCount) {
				return new RankValue(true, value);
			}
			
		}
		return new RankValue(false, 0);
	}
	
	private RankValue getHighestValueCard() {
		
		int max = 0;
		for (int i = 0; i < cards.length; i++) {
			int value = cards[i].getValue();
			//make sure the highest value card is only encountered once; otherwise its used in a higher rank
			if (value > max && cardValues[value] == 1) {
				max = value;
			}
		}
		
		return new RankValue(true,max);
	}
	
}