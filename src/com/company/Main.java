package com.company;

import java.util.LinkedList;

public class Main {
    /*public enum Type {
        LETTER,     // [a-z] | [A-Z]
        NUMBER,     // [0-9]
        OPERATOR,   // symbols
        WHITESPACE, // ' ', '\t', '\n', 'r'
        STRING      // """
    }
    public static Type getType(String input, int current){
        char c = input.charAt(current);
        if(Character.isAlphabetic(c)){
            return Type.LETTER;
        } else if (Character.isDigit(c)){
            return Type.NUMBER;
        } else if (Character.isWhitespace(c)) {
            return Type.WHITESPACE;
        } else if (c == '"'){
            return Type.STRING;
        }else {
            return Type.OPERATOR;
        }
    }*/

    public static LinkedList<Token> tokenizer(String input){
        LinkedList <Token> list = new LinkedList<>(); //Linked list of Tokens
        int current = 0; //Tracks the current index of the input string
        String value = ""; //Empty string that gets added into by char c

        /*
        switch (value) {
            case "output":
                list.add(new Token(value, Category.OUTPUT));
                value = "";
                break;
            case "on":
                list.add(new Token(value, Category.ON));
                value = "";
                break;
            case "create":
                list.add(new Token(value, Category.OPERATE));
                value = "";
                break;
            case "constant":
                list.add(new Token(value, Category.CONSTANT));
                value = "";
                break;
            case "elseif":
                list.add(new Token(value, Category.ELSE_IF));
                value = "";
                break;
            case "me":
                list.add(new Token(value, Category.ME));
                value = "";
                break;
            case "until":
                list.add(new Token(value, Category.UNTIL));
                value = "";
                break;
            case "public":
                list.add(new Token(value, Category.PUBLIC));
                value = "";
                break;
            case "private":
                list.add(new Token(value, Category.PRIVATE));
                value = "";
                break;
            case "alert":
                list.add(new Token(value, Category.ALERT));
                value = "";
                break;
            case "detect":
                list.add(new Token(value, Category.DETECT));
                value = "";
                break;
            case "always":
                list.add(new Token(value, Category.ALWAYS));
                value = "";
                break;
            case "check":
                list.add(new Token(value, Category.CHECK));
                value = "";
                break;
            case "parent":
                list.add(new Token(value, Category.PARENT));
                value = "";
                break;
            case "blueprint":
                list.add(new Token(value, Category.BLUEPRINT));
                value = "";
                break;
            case "system":
                list.add(new Token(value, Category.NATIVE));
                value = "";
                break;
            case "is":
                list.add(new Token(value, Category.INHERITS));
                value = "";
                break;
            case "cast":
                list.add(new Token(value, Category.CAST));
                value = "";
                break;
            case "input":
                list.add(new Token(value, Category.INPUT));
                value = "";
                break;
            case "say":
                list.add(new Token(value, Category.SAY));
                value = "";
                break;
            case "now":
                list.add(new Token(value, Category.NOW));
                value = "";
                break;
            case "while":
                list.add(new Token(value, Category.WHILE));
                value = "";
                break;
            case "package":
                list.add(new Token(value, Category.PACKAGE_NAME));
                value = "";
                break;
            case "times":
                list.add(new Token(value, Category.TIMES));
                value = "";
                break;
            case "repeat":
                list.add(new Token(value, Category.REPEAT));
                value = "";
                break;
            case "else":
                list.add(new Token(value, Category.ELSE));
                value = "";
                break;
            case "return":
                list.add(new Token(value, Category.RETURNS));
                value = "";
                break;
            case "not":
            case "Not":
                list.add(new Token(value, Category.NOT));
                value = "";
                break;
            case "not=":
            case "Not=":
                list.add(new Token(value, Category.NOTEQUALS));
                value = "";
                break;
        }
        */

        while(current < input.length()) {
            //Letter-only Tokens
            if (Character.isAlphabetic(input.charAt(current))) {
                while (current < input.length()) {
                    if (Character.isAlphabetic(input.charAt(current)) || Character.isDigit(input.charAt(current)) || input.charAt(current) == '_') {
                        value += input.charAt(current);
                    } else {
                        break;
                    }
                    current++;
                }
                list.add(new Token(value, Category.ID));
                value = "";
            }
            //INT and FLOAT
            else if (Character.isDigit(input.charAt(current))) {
                boolean decimalFound = false;
                while (current < input.length()) {
                    if (Character.isDigit(input.charAt(current)) || input.charAt(current) == '.') {
                        if (input.charAt(current) == '.') {
                            if (decimalFound) {
                                throw new RuntimeException("More than one use of decimal in a float");
                            }
                            decimalFound = true;
                        }
                        value += input.charAt(current);
                    } else {
                        break;
                    }
                    current++;
                }
                if (!decimalFound) {
                    list.add(new Token(value, Category.INT));
                    value = "";
                } else {
                    list.add(new Token(value, Category.DECIMAL));
                    value = "";
                }
            }
            //WHITESPACE
            else if (Character.isWhitespace(input.charAt(current))) {
                while (current < input.length()) {
                    if (Character.isWhitespace(input.charAt(current))) {
                        current++;
                    } else {
                        break;
                    }
                }
            }
            //STRING
            else if (input.charAt(current) == '"') {
                //Add the first '"'
                //value += input.charAt(current); //Commented out for now since I don't want to add '"' in the value
                current++;

                //Read in every character until it hits another '"'
                while (current < input.length()) {
                    if (input.charAt(current) != '"') {
                        value += input.charAt(current);
                        current++;
                    } else {
                        break;
                    }
                }
                //Add the last '"'
                //value += input.charAt(current); //Commented out for now since I don't want to add '"' in the value
                list.add(new Token(value, Category.STRING));
                value = "";
                current++;
            }
            //COMMENTS and DIVIDE
            else if (input.charAt(current) == '/') {
                //value += input.charAt(current); //Commented out for now since I dont wanna add it to value
                current++;
                //Catches when '/' is the last character of the input
                if(current < input.length()){
                    //COMMENTS (Single Line)
                    if (input.charAt(current) == '/') {
                        //value += input.charAt(current); //Commented out for now since I dont wanna add it to value
                        current++;
                        while (current < input.length()) {
                            if (input.charAt(current) != '\n' && input.charAt(current) != '\r') {
                                value += input.charAt(current);
                                current++;
                            } else {
                                break;
                            }
                        }
                        list.add(new Token(value, Category.COMMENTS));
                        value = "";
                    }
                    //COMMENTS (Multi Line)
                    else if (input.charAt(current) == '*') {
                        //value += input.charAt(current); //Commented out for now since I dont wanna add it to value
                        current++;
                        //Read in every character in the MLC (multi-line comment)
                        boolean foundComment = false;
                        while (current < input.length()) {
                            //If it encounters '*', check the next character if it's a '/' to signify end of MLC
                            if (input.charAt(current) == '*' && current + 1 < input.length()) {
                                //value += input.charAt(current); //Commented out for now since I dont wanna add it to value
                                current++;
                                //If the next character is '/' then it's the end of MLC. Add to token.
                                if (input.charAt(current) == '/') {
                                    //value += input.charAt(current); //Commented out for now since I dont wanna add it to value
                                    list.add(new Token(value, Category.COMMENTS));
                                    value = "";
                                    current++;
                                    foundComment = true;
                                    break;
                                }
                            }
                            // Else keep reading every character until it finds '/' or EOF
                            else {
                                value += input.charAt(current);
                                current++;
                            }
                        }
                        if (!foundComment) {
                            throw new RuntimeException("Multi-line comment never properly terminated");
                        }
                    }
                    //DIVIDE
                    else {
                        list.add(new Token(value, Category.DIVIDE));
                        value = "";
                    }
                }
                //Final catch for division if it's the last character
                else {
                    list.add(new Token(value, Category.DIVIDE));
                    value = "";
                }
            }
            //OPERATORS
            //else if (){

            //}
            //Catch all as INVALID
            else {
                value += input.charAt(current);
                list.add(new Token(value, Category.INVALID));
                value = "";
                current++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //String str = "1 12 12.3 \n\r   3"; //Passed
        //String str = "//single comment\n //comment\r // single comment // \n//"; //Passed
        //String str = "//c123 /*multi \n line \n comment 1/2 / 3 */"; //Passed
        //String str = "/*  *"; //Passed
        //String str = "/* multi \n line \n comment */"; //Passed
        //String str = "a=12/4"; //Passed
        //String str = "  \"this is a string\"  \n "; //Passed
        String str = "output output1";
        //String str = "   output  \n  output1 on one else elseif return returns + = += Dog //comment\n /*multi \n line \n comment*/";

        LinkedList <Token> tokenList;    //linked list of token classes

        tokenList = tokenizer(str);

        for(Token token : tokenList){
            token.printTokens();
        }
    }
}