/*
    Copyright (c) 2025 Tomáš Zídek

    Permission is hereby granted, free of charge, to any person
    obtaining a copy of this software and associated documentation
    files (the "Software"), to deal in the Software without
    restriction, including without limitation the rights to use,
    copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the
    Software is furnished to do so, subject to the following
    conditions:

    The above copyright notice and this permission notice shall be
    included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
    OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
    WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
    FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
    OTHER DEALINGS IN THE SOFTWARE.*/
package zetrium.jsonparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomáš Zídek
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
        System.out.println(isLetter(']'));
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

                        if (isDigit(popped) || popped == '.' || popped == '-') {
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
                                    throw new LexingException("Unknown keyword "+identifier+" at " + cur);
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
        addToken(new Token(type, tokenStart, type.length() + tokenStart, null, wsBuilder.toString()));
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

    private char at(int index) {
        if (isInBounds(index)) {
            return input.charAt(index);
        } else {
            return 0;
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
        return (c >= 'A' && c <= 'Z')||(c >= 'a' && c <= 'z');

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

        if (peek() == '-') {
            collect(pop());
        }
        collectDigitSeq();
        if (peek() == '.') {
            collect(pop());
            collectDigitSeq();
        }
        if (peek() == 'e' || peek() == 'E') {
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
