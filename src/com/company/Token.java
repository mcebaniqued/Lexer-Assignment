package com.company;

public class Token {
    private String value;
    private Category category;

    public Token(String input, Category category){
        this.value = input;
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    public void printTokens() {
        if (this.category == Category.ID || this.category == Category.COMMENTS) {
            System.out.println("Token Category: " + this.category + ", value \"" + this.value + "\"");
        } else if(this.category == Category.INVALID){
            System.out.println("Error: '" + value + "' not allowed");
        } else {
            System.out.println("Token Category: " + this.category + " keyword, value \"" + this.value + "\"");
        }
    }
}