package com.company;

public class Token {
    private String value = "";
    private int category = -1;

    public String getValue() {  //getter for value
        return value;
    }

    public void setValue(String value) {    //setter for value
        this.value = value;
    }

    public int getCategory() {  //getter for category
        return category;
    }

    public void setCategory(int category) { //setter for category
        //TODO: find a way to give each token a unique category
        this.category = category;
    }
}