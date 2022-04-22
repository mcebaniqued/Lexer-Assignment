package com.company;

import java.util.LinkedList;

public class Main {
    public static LinkedList<Token> tokenizer(String input){
        LinkedList <Token> list = new LinkedList<>(); //Linked list of Tokens
        int current = 0; //Tracks the current index of the input string
        String value = ""; //Empty string that gets added into the linked list

        while (current < input.length()) {
            //Letter-only Tokens and ID
            if (Character.isAlphabetic(input.charAt(current))) {
                while (current < input.length()){
                    //Check if next occurring characters are letters, numbers, or '_' only
                    if (Character.isAlphabetic(input.charAt(current)) || Character.isDigit(input.charAt(current)) || input.charAt(current) == '_'){
                        value += input.charAt(current);
                        current++;
                    } else {
                        break;
                    }
                }

                switch (value){
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
                    case "returns":
                        list.add(new Token(value, Category.RETURNS));
                        value = "";
                        break;
                    case "return":
                        list.add(new Token(value, Category.RETURN));
                        value = "";
                        break;
                    case "and":
                        list.add(new Token(value, Category.AND));
                        value = "";
                        break;
                    case "or":
                        list.add(new Token(value, Category.OR));
                        value = "";
                        break;
                    case "undefined":
                        list.add(new Token(value, Category.NULL));
                        value = "";
                        break;
                    case "shared":
                        list.add(new Token(value, Category.STATIC));
                        value = "";
                        break;
                    case "action":
                        list.add(new Token(value, Category.ACTION));
                        value = "";
                        break;
                    case "integer":
                        list.add(new Token(value, Category.INTEGER_KEYWORD));
                        value = "";
                        break;
                    case "number":
                        list.add(new Token(value, Category.NUMBER_KEYWORD));
                        value = "";
                        break;
                    case "text":
                        list.add(new Token(value, Category.TEXT));
                        value = "";
                        break;
                    case "true":
                    case "false":
                        list.add(new Token(value, Category.BOOLEAN));
                        value = "";
                        break;
                    case "boolean":
                        list.add(new Token(value, Category.BOOLEAN_KEYWORD));
                        value = "";
                        break;
                    case "use":
                        list.add(new Token(value, Category.USE));
                        value = "";
                        break;
                    case "not":
                    case "Not":
                        //Check if not EOF
                        if(current + 1 < input.length()) {
                            //value is "not"/"Not" and the next char is '='
                            if(input.charAt(current) == '='){
                                value += input.charAt(current);
                                list.add(new Token(value, Category.NOTEQUALS));
                                current++;
                            } else {
                                list.add(new Token(value, Category.NOT));
                            }
                            value = "";
                            break;
                        }
                    case "mod":
                        list.add(new Token(value, Category.MODULO));
                        value = "";
                        break;
                    case "if":
                        list.add(new Token(value, Category.IF));
                        value = "";
                        break;
                    case "end":
                        list.add(new Token(value, Category.END));
                        value = "";
                        break;
                    case "class":
                        list.add(new Token(value, Category.CLASS));
                        value = "";
                        break;
                    default:
                        list.add(new Token(value, Category.ID));
                        value = "";
                        break;
                }
            }
            //INT and FLOAT
            else if (Character.isDigit(input.charAt(current))) {
                boolean decimalFound = false;
                boolean isInvalid = false;
                while (current < input.length()) {
                    if (Character.isDigit(input.charAt(current)) || input.charAt(current) == '.') {
                        if (input.charAt(current) == '.') {
                            if (decimalFound) {
                                while(current < input.length()){
                                    if(!Character.isWhitespace(input.charAt(current))) {
                                        value += input.charAt(current);
                                        current++;
                                    } else {
                                        break;
                                    }
                                }
                                isInvalid = true;
                                list.add(new Token(value, Category.INVALID));
                                value = "";
                                break;
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
                }
                else if (!isInvalid){
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
                //Tokenize the first '"'
                value += input.charAt(current);
                list.add(new Token(value,Category.DOUBLE_QUOTE));
                value = "";
                current++;

                boolean noStringFound = false;
                //Read in every character until it hits another '"'
                while (current < input.length()) {
                    if (input.charAt(current) != '"') {
                        value += input.charAt(current);
                        current++;
                    } else {
                        noStringFound = true;
                        break;
                    }
                }
                if (noStringFound) {
                    //Tokenize the string
                    list.add(new Token(value, Category.STRING));
                    value = "";

                    //Tokenize the last '"'
                    value += input.charAt(current);
                    list.add(new Token(value, Category.DOUBLE_QUOTE));
                    value = "";
                    current++;
                } else {
                    list.add(new Token(value, Category.INVALID));
                    value = "";
                }
            }
            //COMMENTS and DIVIDE
            else if (input.charAt(current) == '/') {
                //Catches when '/' is the last character of the input
                if(current + 1 < input.length()){
                    //COMMENTS (Single Line)
                    if (input.charAt(current + 1) == '/') {
                        current+=2;
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
                    else if (input.charAt(current + 1) == '*') {
                        current+=2;
                        //Read in every character in the MLC (multi-line comment)
                        boolean foundComment = false;
                        while (current < input.length()) {
                            //If it encounters '*', check the next character if it's a '/' to signify end of MLC
                            if (input.charAt(current) == '*' && current + 1 < input.length()) {
                                current++;
                                //If the next character is '/' then it's the end of MLC. Add to token.
                                if (input.charAt(current) == '/') {
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
                        //Tokenize invalid comments
                        if (!foundComment) {
                            list.add(new Token(value, Category.INVALID));
                            value = "";
                        }
                    }
                    //DIVIDE
                    else {
                        value += input.charAt(current);
                        list.add(new Token(value, Category.DIVIDE));
                        value = "";
                        current++;
                    }
                }
                //Final catch for division if it's the last character
                else {
                    value += input.charAt(current);
                    list.add(new Token(value, Category.DIVIDE));
                    value = "";
                    current++;
                }
            }
            //All the symbols in Category
            else if (!(Character.isWhitespace(input.charAt(current)) || Character.isAlphabetic(input.charAt(current))  ||Character.isDigit(input.charAt(current)))){
                while (current < input.length()) {
                    if(!(Character.isWhitespace(input.charAt(current)) || Character.isAlphabetic(input.charAt(current))  ||Character.isDigit(input.charAt(current)))) {
                        value += input.charAt(current);
                        current++;
                    } else {
                        break;
                    }
                }
                switch (value) {
                    case ":" -> {
                        list.add(new Token(value, Category.COLON));
                        value = "";
                    }
                    case "." -> {
                        list.add(new Token(value, Category.PERIOD));
                        value = "";
                    }
                    case "," -> {
                        list.add(new Token(value, Category.COMMA));
                        value = "";
                    }
                    case "=" -> {
                        list.add(new Token(value, Category.EQUALITY));
                        value = "";
                    }
                    case ">" -> {
                        list.add(new Token(value, Category.GREATER));
                        value = "";
                    }
                    case ">=" -> {
                        list.add(new Token(value, Category.GREATER_EQUAL));
                        value = "";
                    }
                    case "<" -> {
                        list.add(new Token(value, Category.LESS));
                        value = "";
                    }
                    case "<=" -> {
                        list.add(new Token(value, Category.LESS_EQUAL));
                        value = "";
                    }
                    case "+" -> {
                        list.add(new Token(value, Category.PLUS));
                        value = "";
                    }
                    case "-" -> {
                        list.add(new Token(value, Category.MINUS));
                        value = "";
                    }
                    case "*" -> {
                        list.add(new Token(value, Category.MULTIPLY));
                        value = "";
                    }
                    case "[" -> {
                        list.add(new Token(value, Category.LEFT_SQR_BRACE));
                        value = "";
                    }
                    case "]" -> {
                        list.add(new Token(value, Category.RIGHT_SQR_BRACE));
                        value = "";
                    }
                    case "(" -> {
                        list.add(new Token(value, Category.LEFT_PAREN));
                        value = "";
                    }
                    case ")" -> {
                        list.add(new Token(value, Category.RIGHT_PAREN));
                        value = "";
                    }
                    default -> {
                        list.add(new Token(value, Category.INVALID));
                        value = "";
                    }
                }
            }
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
        //String str = "1 12 12.3 \n\r   3  1.2.3 1.2. /"; //Passed
        //String str = "//single comment\n //comment\r // single comment // \n//"; //Passed
        //String str = "//c123 /*multi \n line \n comment 1/2 / 3 */"; //Passed
        //String str = "/*  bad comment *"; //Passed
        //String str = "/* multi \n line \n comment */"; //Passed
        //String str = "a=12/4"; //Passed
        //String str = "  \"this is a string\"  \n "; //Passed
        //String str = "\"bad string";
        //String str = "output output1";
        //String str = "one on one1 one_two output_1 say saying dog Dog";
        //String str = "not Not not= Not= not = not=1 Not=1 dog not= 1 dog not=1";
        //String str = "   output  \n  output1 on one else elseif return returns + = += Dog //comment\n /*multi \n line \n comment*/";
        String str = " : . , = > >= < <= + - * / [ ] ( ) ++ -- += -= */";

        LinkedList <Token> tokenList;    //linked list of token classes

        tokenList = tokenizer(str);

        for(Token token : tokenList){
            token.printTokens();
        }
    }
}