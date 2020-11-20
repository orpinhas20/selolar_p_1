package com.example.project_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {

    private static final int cardNumberSize = 52;
    private static final int halfArraySize = cardNumberSize/2;

    private static List<Card>  mainStack = new ArrayList();
    private static List<Card> p1Array = new ArrayList();
    private static List<Card> p2Array = new ArrayList();

    public static List<Card> getP1Array() {
        return p1Array;
    }

    public static void setP1Array(List<Card> p1Array) {
        GameManager.p1Array = p1Array;
    }

    public static List<Card> getP2Array() {
        return p2Array;
    }

    public static void setP2Array(List<Card> p2Array) {
        GameManager.p2Array = p2Array;
    }

    /* Setup cards for the game. */
    public static void initCardGame()
    {
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

    public static int checkWinnner(Card p1Card , Card p2Card)
    {
        return ((p1Card.getValue() >= p2Card.getValue()) ? 1:0 );
    }

}
