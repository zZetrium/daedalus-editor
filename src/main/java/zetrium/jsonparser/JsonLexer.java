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

        char popped;
        while (isInBounds()) {
            popped = pop();
            if (isWhitespace(popped)) {
                wsBuilder.append(popped);
            } else {
                caser:
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
                    case '"' -> {
                        collectUntil('"');
                        pop();
                        var value = strBuilder.toString();
                        addToken(new Token(TokenType.STRING, tokenStart, tokenStart + value.length() + 2, value, wsBuilder.toString()));

                    }

                    default -> {

                        if (isDigit(popped) || popped == '.') {
                            back();
                            collectNumber();
                            var value = strBuilder.toString();
                            addToken(new Token(TokenType.NUMBER, tokenStart, tokenStart + value.length(), value, wsBuilder.toString()));
                            break caser;
                        }

                        if (isWhitespace(popped)) {
                            back();
                            collectMultiWs();
                            break caser;
                        }
                        if (isLetter(popped)) {
                            back();
                            collectWord();
                            var identifier = strBuilder.toString();
                            switch (identifier) {
                                default ->
                                    throw new LexingException("Unknown keyword at " + cur);
                                case "true" ->
                                    addSimpleToken(TokenType.TRUE);
                                case "false" ->
                                    addSimpleToken(TokenType.FALSE);
                                case "null" ->
                                    addSimpleToken(TokenType.NULL);

                            }
                            break caser;
                        }

                        throw new LexingException("Unknown input at " + cur + " of " + popped);
                    }

                }
            }
            tokenStart = cur;
        }

        return tokens;
    }

    private void addSimpleToken(TokenType type) {
        //  tokens.add(new Token(type, tokenStart, null,wsBuilder.toString()));
        addToken(new Token(type, tokenStart, type.getLenght() + tokenStart, null, wsBuilder.toString()));
    }

    private void addToken(Token token) {
        tokens.add(token);
        wsBuilder.setLength(0);
        strBuilder.setLength(0);
    }

    private void init() {
        tokens = new ArrayList<>();
        cur = 0;
        tokenStart = 0;
        wsBuilder = new StringBuilder(8);
        strBuilder = new StringBuilder(16);
    }

    private char pop() throws LexingException {
        return at(cur++);
    }

    private char last() throws LexingException {
        return at(cur - 1);
    }

    private void back() {
        cur--;
    }

    private char peek() throws LexingException {
        return at(cur);
    }

    private char peek(int offset) throws LexingException {
        return at(cur + offset);
    }

    private char at(int index) throws LexingException {
        if (isInBounds(index)) {
            return input.charAt(index);
        } else {
            throw new LexingException("End reached too early.");
        }

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

    public boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    public boolean isLetter(char c) {
        return (c >= 'A' && c <= 'z');

    }

    private char collect(char c) {
        strBuilder.append(c);
        return c;
    }

    private char collectWs(char c) {
        wsBuilder.append(c);
        return c;
    }

    private void collectNumber() throws LexingException {
        char first = collect(pop());

        if (!isDigit(first) && first != '.') {
            return;
        }

        collectDigitSeq();

        if (first != '.' && peek() == '.') {
            collect(pop());
            collectDigitSeq();
        }
    }

    private void collectDigitSeq() throws LexingException {
        while (isDigit(peek())) {
            collect(pop());
        }
    }

    private void collectMultiWs() throws LexingException {
        while (isWhitespace(peek())) {
            collectWs(pop());
        }
    }

    private void collectWord() throws LexingException {
        while (isLetter(peek())) {
            collect(pop());
        }
    }

    private void collectUntil(char c) throws LexingException {
        while (c != peek()) {
            collect(pop());
        }
    }

}
