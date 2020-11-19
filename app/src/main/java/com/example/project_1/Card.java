package com.example.project_1;

public class Card {
    private int name ;
    private int value = 0; //value: 1-13

    public Card(int name, int value){
        this.name = name;
        this.value=value;

    }
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
