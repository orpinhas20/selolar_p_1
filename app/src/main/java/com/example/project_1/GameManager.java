package com.example.project_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {

    private static final int cardNumberSize = 52;
    private static final int halfArraySize = cardNumberSize/2;

    private static List<String>  mainStack = new ArrayList();
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
//        for(int i=0; i<cardNumberSize/4;i++)
//        {
//            int current = i+1;
//            String nameOfPicA = "R.drawable.pocker_a_" + (current);
//
//            mainStack.add(new Card(Integer.parseInt(nameOfPicA),(current)));
//
//        }
//        for(int i=cardNumberSize/4; i<cardNumberSize/3;i++)
//        {
//            int current = i+1;
//            String nameOfPicB = "R.drawable.pocker_b_" + (current);
//            mainStack.add(new Card(Integer.parseInt(nameOfPicB),(current)));
//        }
//        for(int i=cardNumberSize/3; i<cardNumberSize/2;i++)
//        {
//            int current = i+1;
//            String nameOfPicC = "R.drawable.pocker_c_" + (current);
//            mainStack.add(new Card(Integer.parseInt(nameOfPicC),(current)));
//        }
//        for(int i=cardNumberSize/2; i<cardNumberSize;i++)
//        {
//            int current = i+1;
//            String nameOfPicD = "R.drawable.pocker_d_" + (current);
//            mainStack.add(new Card(Integer.parseInt(nameOfPicD),(current)));
//        }
        mainStack.add("R.drawable.pocker_a_1");
        mainStack.add("R.drawable.pocker_a_2");
        mainStack.add("R.drawable.pocker_a_3");
        mainStack.add("R.drawable.pocker_a_4");
        mainStack.add("R.drawable.pocker_a_5");
        mainStack.add("R.drawable.pocker_a_6");
        mainStack.add("R.drawable.pocker_a_7");
        mainStack.add("R.drawable.pocker_a_8");
        mainStack.add("R.drawable.pocker_a_9");
        mainStack.add("R.drawable.pocker_a_10");
        mainStack.add("R.drawable.pocker_a_11");
        mainStack.add("R.drawable.pocker_a_12");
        mainStack.add("R.drawable.pocker_a_13");

        mainStack.add("R.drawable.pocker_b_1");
        mainStack.add("R.drawable.pocker_b_2");
        mainStack.add("R.drawable.pocker_b_3");
        mainStack.add("R.drawable.pocker_b_4");
        mainStack.add("R.drawable.pocker_b_5");
        mainStack.add("R.drawable.pocker_b_6");
        mainStack.add("R.drawable.pocker_b_7");
        mainStack.add("R.drawable.pocker_b_8");
        mainStack.add("R.drawable.pocker_b_9");
        mainStack.add("R.drawable.pocker_b_10");
        mainStack.add("R.drawable.pocker_b_11");
        mainStack.add("R.drawable.pocker_b_12");
        mainStack.add("R.drawable.pocker_b_13");

        mainStack.add("R.drawable.pocker_c_1");
        mainStack.add("R.drawable.pocker_c_2");
        mainStack.add("R.drawable.pocker_c_3");
        mainStack.add("R.drawable.pocker_c_4");
        mainStack.add("R.drawable.pocker_c_5");
        mainStack.add("R.drawable.pocker_c_6");
        mainStack.add("R.drawable.pocker_c_7");
        mainStack.add("R.drawable.pocker_c_8");
        mainStack.add("R.drawable.pocker_c_9");
        mainStack.add("R.drawable.pocker_c_10");
        mainStack.add("R.drawable.pocker_c_11");
        mainStack.add("R.drawable.pocker_c_12");
        mainStack.add("R.drawable.pocker_c_13");





        mainStack.add("R.drawable.pocker_d_1");
        mainStack.add("R.drawable.pocker_d_2");
        mainStack.add("R.drawable.pocker_d_3");
        mainStack.add("R.drawable.pocker_d_4");
        mainStack.add("R.drawable.pocker_d_5");
        mainStack.add("R.drawable.pocker_d_6");
        mainStack.add("R.drawable.pocker_d_7");
        mainStack.add("R.drawable.pocker_d_8");
        mainStack.add("R.drawable.pocker_d_9");
        mainStack.add("R.drawable.pocker_d_10");
        mainStack.add("R.drawable.pocker_d_11");
        mainStack.add("R.drawable.pocker_d_12");
        mainStack.add("R.drawable.pocker_d_13");

        Collections.shuffle(mainStack);
        for (int i = 0 ;i<halfArraySize;i++)
        {
            p1Array.add(new Card(mainStack.get(i),(i+1)));
        }
        for (int j = halfArraySize ;j<cardNumberSize;j++)
        {
            p2Array.add(new Card(mainStack.get(j),(j+1)));
        }
    }

    public static int checkWinnner(Card p1Card , Card p2Card)
    {
        return ((p1Card.getValue() >= p2Card.getValue()) ? 1:0 );
    }

}
