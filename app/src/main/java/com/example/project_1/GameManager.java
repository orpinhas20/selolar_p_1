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

    public static void initCardGame()
    {
        for(int i=0; i<cardNumberSize/4;i++)
        {
            int current = i+1;
            String nameOfPicA = "R.drawable.pocker_a_" + (current);
            String nameOfPicB = "R.drawable.pocker_b_" + (current);
            String nameOfPicC = "R.drawable.pocker_c_" + (current);
            String nameOfPicD = "R.drawable.pocker_d_" + (current);
            mainStack.add(new Card(Integer.parseInt(nameOfPicA),(current)));
            mainStack.add(new Card(Integer.parseInt(nameOfPicB),(current)));
            mainStack.add(new Card(Integer.parseInt(nameOfPicC),(current)));
            mainStack.add(new Card(Integer.parseInt(nameOfPicD),(current)));
        }
        Collections.shuffle(mainStack);
        for (int i = 0 ;i<halfArraySize;i++)
        {
            p1Array.add(mainStack.get(i));
        }
        for (int j = halfArraySize ;j<cardNumberSize;j++)
        {
            p2Array.add(mainStack.get(j));
        }
    }

    public static int checkWinnner(Card p1Card , Card p2Card)
    {
        return ((p1Card.getValue() >= p2Card.getValue()) ? 1:0 );
    }

}
