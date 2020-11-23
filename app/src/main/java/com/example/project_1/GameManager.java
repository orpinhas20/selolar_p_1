package com.example.project_1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {

    // Define constants:
    public static final int MAX_CARDS = 26;
    private static final int cardNumberSize = 52;
    private static final int halfArraySize = cardNumberSize / 2;

    // Define helper type:
    public static enum Player {
        Default(0),
        Player1(1),
        Player2(2);

        private final int value;

        Player(final int newValue){
            this.value = newValue;
        }

        public int getValue(){
            return this.value;
        }
    };

    // Add helper data strictures:
    private static List<Card>  mainStack;
    private static List<Card> p1Array;
    private static List<Card> p2Array;

    // Define private class instance:
    private static final GameManager instance = new GameManager();

    // Define private constructor for Singleton pattern:
    private GameManager() {
        this.mainStack = new ArrayList();
        this.p1Array = new ArrayList();
        this.p2Array = new ArrayList();
    }

    // Get the instance of the class:
    public static GameManager getInstance() {
        return instance;
    }

    public Card getP1Card(int index){
        return p1Array.get(index);
    }

    public void setP1Array(List<Card> p1Array) {
        GameManager.p1Array = p1Array;
    }

    public Card getP2Card(int index){
        return p2Array.get(index);
    }

    /* Setup cards for the game. */
    public void initCardGame() {
        // Setup cards data for the game:
        for(int i = 0; i < cardNumberSize / 4; i++)  {

            // Setup index to the card resource:
            int current = i + 1;

            String nameOfPicA = "poker_a_" + (current);
            String nameOfPicB = "poker_b_" + (current);
            String nameOfPicC = "poker_c_" + (current);
            String nameOfPicD = "poker_d_" + (current);

            // Add the new card to the data structure:
            mainStack.add(new Card(i, nameOfPicA, current));
            mainStack.add(new Card(i, nameOfPicB, current));
            mainStack.add(new Card(i, nameOfPicC, current));
            mainStack.add(new Card(i, nameOfPicD, current));
        }

        // Shuffle all the card data:
        Collections.shuffle(mainStack);

        // Add cards for player 1:
        for (int i = 0; i < halfArraySize; i++) {
            p1Array.add(mainStack.get(i));
        }

        // Add cards for player 2:
        for (int i = halfArraySize; i < cardNumberSize; i++) {
            p2Array.add(mainStack.get(i));
        }
    }

    public Player checkWinner(Card p1Card , Card p2Card) {
        int p1Score = p1Card.getValue();
        int p2Score = p2Card.getValue();

        if(p1Score > p2Score) {
            Log.d("CardWar","[checkWinner()], Player1 won (" + p1Score + ", " + p2Score + ")");
            return Player.Player1;
        }
        else if (p1Score < p2Score){
            Log.d("CardWar","[checkWinner()], Player2 won (" + p1Score + ", " + p2Score + ")");
            return Player.Player2;
        }
        else{
            Log.d("CardWar","[checkWinner()], Default won (" + p1Score + ", " + p2Score + ")");
            return Player.Default;
        }
    }

}
