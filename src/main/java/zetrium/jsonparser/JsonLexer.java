/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xzidek
 */
public class JsonLexer {

    private int cur;
    private int tokenStart;
    private List<Token> tokens;
    private String input;
    private StringBuilder wsBuilder;
    private StringBuilder strBuilder;

    public List<Token> lex(String input) throws LexingException {
        this.input = input;
        init();

        char popped = 0;
        while ((popped = pop()) != 0) {
            if (isWhitespace(popped)) {
                wsBuilder.append(popped);
            } else {
                switch (popped) {
                    case ':' ->
                        addSimpleToken(TokenType.COLON);

                    case ',' ->
                        addSimpleToken(TokenType.COMMA);

                    case '{' ->
                        addSimpleToken(TokenType.OPEN_CURLY);

                    case '}' ->
                        addSimpleToken(TokenType.CLOSE_CURLY);

                    case '[' ->
                        addSimpleToken(TokenType.OPEN_SQUARE);

                    case ']' ->
                        addSimpleToken(TokenType.CLOSE_SQUARE);

                    case 't' -> {
                        if (pop() == 'r' & pop() == 'u' & pop() == 'e') {
                            addSimpleToken(TokenType.TRUE);
                        } else {
                            throw new LexingException("Invalid text token between " + tokenStart + " and " + (tokenStart + 3));
                        }
                    }

                    case 'f' -> {
                        if (pop() == 'a' & pop() == 'l' & pop() == 's' & pop() == 'e') {
                            addSimpleToken(TokenType.FALSE);

                        }else {
                            throw new LexingException("Invalid text token between " + tokenStart + " and " + (tokenStart + 4));
                        }
                    }

                }
            }
            tokenStart = cur;
        }

        return tokens;
    }

    private void addSimpleToken(TokenType type) {
        tokens.add(new Token(type, tokenStart, null));
    }

    private void init() {
        tokens = new ArrayList<>();
        cur = 0;
        tokenStart = 0;
        wsBuilder = new StringBuilder(8);
        strBuilder = new StringBuilder(16);
    }

    private char pop() {
        return at(cur++);
    }

    private char last() {
        return at(cur - 1);
    }

    private char peek() {
        return at(cur);
    }

    private char peek(int offset) {
        return at(cur + offset);
    }

    private char at(int index) {
        return isInBounds(index) ? input.charAt(index) : 0;
    }

    private boolean isInBounds(int index) {
        return index > -1 && index < input.length();
    }

    private boolean isInBounds() {
        return isInBounds(cur);
    }

    public boolean isWhitespace(char c) {
        return switch (c) {
            case 0x20, 0xA, 0xD, 0x9 ->
                true;
            default ->
                false;
        };
    }
    
    //private void collectNumber
}
