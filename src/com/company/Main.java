package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String input = "output output";                            //test input
        int current = 0;                                           //tracks the current index of the input string
        String values = "";                                        //variable where the string input is compared to tokens
        LinkedList <Token> tokenList = new LinkedList<Token>();     //linked list of token classes

        while(current < input.length()){
            char c = input.charAt(current); //reads current character of input string
            values = values + c;            //add each character currently read in values each iteration

            if(isWhiteSpace(c)) {
                values = GreedyWhiteSpace(values);
            }

            if(values.compareTo("output") == 0){
                Token token = new Token();  //create a Token class for OUTPUT token
                token.setValue(values);     //set the value of OUTPUT token as "output" string
                tokenList.add(token);       //add the token class in a token linked list
                values = "";                //reset the values variable back to empty string
            }

            //System.out.println(values);   //debug
            current++;
        }

        //debug: print out value and category of linked list
        for (Token token : tokenList) {
            System.out.println("Token category: " + token.getCategory() + ", value \"" + token.getValue() + "\"");
        }
    }

    //Returns true or false if the current character is a whitespace
    public static boolean isWhiteSpace(char c){
        if(c == ' '){
            return true;
        } else {
            return false;
        }
    }

    //A function that takes care of the whitespace currently read
    public static String GreedyWhiteSpace(String value){
        //get rid of all the whitespace
        //check if values is a valid token
        //if not, it's an error
        //if it is, put in the token list
        return value.replace(" ","");
    }
}