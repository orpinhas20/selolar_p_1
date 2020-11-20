package com.example.project_1;

public class Card {
    private String name ;
    private int value = 0; //value: 1-13

    public Card(String name, int value){
        this.name = name;
        this.value=value;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
