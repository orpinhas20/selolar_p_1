package com.example.project_1;

public class Card {
    private String name;
    private int id;
    private int value; // value: 1-13

    public Card() {
        this.name = "";
        this.id = 0;
        this.value = 0;
    }

    public Card(int id, String name, int value){
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
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
