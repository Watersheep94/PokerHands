# PokerHands
Project Euler Problem 54: Poker Hands

## Instructions
To run the program:
```
java -jar PokerHands.jar
```

## Why I Chose This Problem

Poker Hands seemed like a good problem to demonstrate knowledge of OOP design ands concepts. In addition, it didn't seem too challeneging in an algorithmic sense so I thought this was a good first problem that Adobe recommended. 

## Process

Although there were solutions online that solved it in less lines of code, I wanted to write it in very production-level way. I created a Card class to simplifiy the process of retrieving a card's value and suit. I also created a Hand class to abstract away the logic of a calculating how "good" a player's hand was. By doing this, my actual program simulations could be written more concisely and I was able to quickly create numerous test cases for my JUnit class.

## References

* I looked up insertion sort for my `sortCards` method: https://en.wikipedia.org/wiki/Insertion_sort
* Looked up reading text files: http://www.avajava.com/tutorials/lessons/how-do-i-read-a-string-from-a-file-line-by-line.html

## Time Spent

Spent a good amount of time during the 3-day period on this one, just to iron out the kinks on the OO design and make sure all the logic was sound. Didn't keep track of the hours but estimating at least 3 hours from concept to finished code/tests,
