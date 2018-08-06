package pokerHands;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokerHandsTest {

	@Test
	public void testPairs() {
		
		//Player One has a pair of fives
		Card[] playerOneCards = new Card[] {
			new Card("5H"),
			new Card("5C"),
			new Card("6S"),
			new Card("7S"),
			new Card("KD"), 	
		};
		Hand playerOneHand = new Hand(playerOneCards);
		
		//Player Two has a pair of eights
		Card[] playerTwoCards = new Card[] {
				new Card("2C"),
				new Card("3S"),
				new Card("8S"),
				new Card("8D"),
				new Card("TD"), 	
			};
		Hand playerTwoHand = new Hand(playerTwoCards);
			
		assertFalse("Player one should lose.", PokerHands.doesPlayerOneWin(playerOneHand, playerTwoHand));
	}
	
	@Test
	public void testHighestCard() {
		
		//Player One has a highest card of Ace
		Card[] playerOneCards = new Card[] {
			new Card("5D"),
			new Card("8C"),
			new Card("9S"),
			new Card("JS"),
			new Card("AC"), 	
		};
		Hand playerOneHand = new Hand(playerOneCards);
		
		//Player Two has a highest card of Queen
		Card[] playerTwoCards = new Card[] {
				new Card("2C"),
				new Card("5C"),
				new Card("7D"),
				new Card("8S"),
				new Card("QH"), 	
			};
		Hand playerTwoHand = new Hand(playerTwoCards);
			
		assertTrue("Player one should win.", PokerHands.doesPlayerOneWin(playerOneHand, playerTwoHand));
	}
	
	@Test
	public void testFlushVsThreeOfKind() {
		
		//Player One has three aces
		Card[] playerOneCards = new Card[] {
			new Card("2D"),
			new Card("9C"),
			new Card("AS"),
			new Card("AH"),
			new Card("AC"), 	
		};
		Hand playerOneHand = new Hand(playerOneCards);
		
		//Player Two has a Flush of Diamonds
		Card[] playerTwoCards = new Card[] {
				new Card("3D"),
				new Card("6D"),
				new Card("7D"),
				new Card("TD"),
				new Card("QD"), 	
			};
		Hand playerTwoHand = new Hand(playerTwoCards);
			
		assertFalse("Player one should lose.", PokerHands.doesPlayerOneWin(playerOneHand, playerTwoHand));
	}
	
	@Test
	public void testPairAndHighestCard() {
		
		//Player One has a Pair of Queens and Highest card Nine
		Card[] playerOneCards = new Card[] {
			new Card("4D"),
			new Card("6S"),
			new Card("9H"),
			new Card("QH"),
			new Card("QC"), 	
		};
		Hand playerOneHand = new Hand(playerOneCards);
		
		//Player Two has a Pair of Queens and Highest card Seven
		Card[] playerTwoCards = new Card[] {
				new Card("3D"),
				new Card("6D"),
				new Card("7H"),
				new Card("QD"),
				new Card("QS"), 	
			};
		Hand playerTwoHand = new Hand(playerTwoCards);
			
		assertTrue("Player one should win.", PokerHands.doesPlayerOneWin(playerOneHand, playerTwoHand)); 		
	}
	
	@Test
	public void testFullHouseAndThreeOfAKind() {
		
		//Player One has a Full House and Three Fours
		Card[] playerOneCards = new Card[] {
			new Card("2H"),
			new Card("2D"),
			new Card("4C"),
			new Card("4D"),
			new Card("4S"), 	
		};
		Hand playerOneHand = new Hand(playerOneCards);
		
		//Player Two has a Full House and Three Threes
		Card[] playerTwoCards = new Card[] {
				new Card("3C"),
				new Card("3D"),
				new Card("3S"),
				new Card("9S"),
				new Card("9D"), 	
			};
		Hand playerTwoHand = new Hand(playerTwoCards);
			
		assertTrue("Player one should win.", PokerHands.doesPlayerOneWin(playerOneHand, playerTwoHand)); 	
 
	}
}
