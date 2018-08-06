package pokerHands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PokerHands {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//boilerplate code to read poker.txt file
		File file = new File("poker.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		int playerOneWins = 0;
		
		try {
			// read each line and create player one's hand and player two's hand
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] splited = line.split("\\s+"); //split the line based on whitespace
				
				Card[] playerOneCards = new Card[5];
				Card[] playerTwoCards = new Card[5];
				
				//add each card to player one
				for (int i = 0; i < splited.length / 2; i++) {
					playerOneCards[i] = new Card(splited[i]);
				}
				Hand playerOneHand = new Hand(playerOneCards);
				
				//add each card to player two
				for (int i = splited.length / 2, j = 0; i < splited.length && j < splited.length / 2; i++, j++) {
					playerTwoCards[j] = new Card(splited[i]);
				}
				Hand playerTwoHand = new Hand(playerTwoCards);
				
				//simulate the poker game
				boolean win = doesPlayerOneWin(playerOneHand, playerTwoHand);
				if (win) {
					playerOneWins++;
				}
				
				line = bufferedReader.readLine();
			}
			fileReader.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	
		System.out.println("Player one has won " + playerOneWins + " hands.");
		
	}
	
	/*
	 * Compares Player One's hand to Player two's hand
	 * 
	 * @return Whether Player one wins
	 */
	public static boolean doesPlayerOneWin(Hand playerOneHand, Hand playerTwoHand) {
		playerOneHand.setRanksAndValues();
		playerTwoHand.setRanksAndValues();
		for (int i = 9; i >= 0; i--) {
			if (playerOneHand.getRankValues()[i].hasRank == true && playerTwoHand.getRankValues()[i].hasRank == false) {
				return true;
			} else if (playerOneHand.getRankValues()[i].hasRank == false && playerTwoHand.getRankValues()[i].hasRank == true) {
				return false;
			} else {
				if (playerOneHand.getRankValues()[i].cardValue > playerTwoHand.getRankValues()[i].cardValue) {
					return true;
				} else if (playerOneHand.getRankValues()[i].cardValue < playerTwoHand.getRankValues()[i].cardValue) {
					return false;
				} else {
					continue;	// if both players have the same rank with the same value, then we move onto the next highest rank
				}
			}
		}
		return false;
	}
}