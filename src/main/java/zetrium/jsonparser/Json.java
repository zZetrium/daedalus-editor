/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zetrium.jsonparser;

import java.util.List;

/**
 *
 * @author xzidek
 */
public final class Json {

    public static sealed interface Node permits Value, Array, Obj, Error {

        // with whitespace
        int entireLength();

    }

    public static record Array(Token start, Token end, List<Element> elements) implements Node {

        public Array(Token start, Token end, List<Element> elements) {
            this.start = start;
            this.end = end;
            this.elements = List.copyOf(elements);
        }

        @Override
        public int entireLength() {
            int len = start.entireLength() + end.entireLength();
            for (var elem : elements) {
                len += elem.value.entireLength();
                len += elem.comma != null ? elem.comma.entireLength() : 0;
            }
            return len;
        }

        // comma null if last
        public static record Element(Node value, Token comma) {

            public Element(Node value, Token comma) {
                if (comma != null && comma.type() != TokenType.COMMA) {
                    throw new IllegalArgumentException("comma.type() must be COMMA not " + comma.type());
                }
                this.value = value;
                this.comma = comma;
            }

        }

    }

    public static record Obj(Token start, Token end, List<Entry> entries) implements Node {

        public Obj(Token start, Token end, List<Entry> entries) {
            this.start = start;
            this.end = end;
            this.entries = List.copyOf(entries);
        }

        @Override
        public int entireLength() {
            int len = start.entireLength() + end.entireLength();
            for (var elem : entries) {
                len += elem.value.entireLength() + elem.colon.entireLength() + elem.key.entireLength();
                len += elem.comma != null ? elem.comma.entireLength() : 0;
            }
            return len;
        }

        // comma null if last
        public static record Entry( Token key, Token colon, Node value, Token comma) {

            public Entry(Token key, Token colon, Node value, Token comma) {
                if (comma != null && comma.type() != TokenType.COMMA) {
                    throw new IllegalArgumentException("comma.type() must be COMMA not " + comma.type());
                }
                if (colon.type() != TokenType.COLON) {
                    throw new IllegalArgumentException("colon.type() must be COLON not " + colon.type());
                }
                if (key.type() != TokenType.STRING) {
                    throw new IllegalArgumentException("key.type() must be STRING not " + key.type());
                }
                this.colon = colon;
                this.key = key;
                this.value = value;
                this.comma = comma;
            }

        }
    }

    // represented by single token
    public static sealed interface Value extends Node permits Value.Bool, Value.Null, Value.Str, Value.Number {

        Token token();

        default int length() {
            return token().length();
        }

        default int entireLength() {
            return token().entireLength();
        }

        public static record Bool(Token token) implements Value {

            public static final Bool TRUE = new Bool(new Token(TokenType.TRUE, ""));
            public static final Bool FALSE = new Bool(new Token(TokenType.FALSE, ""));

            public Bool(Token token) {
                switch (token.type()) {
                    case TRUE, FALSE ->
                        this.token = token;
                    default ->
                        throw new IllegalArgumentException("Token type must be TRUE or FALSE, not " + token.type().toString());
                }

            }

            public static Bool of(boolean value) {
                return value ? TRUE : FALSE;
            }

            public boolean value() {
                return this.token().type() == TokenType.TRUE;
            }

        }

        public static record Null(Token token) implements Value {

            public static final Null NULL = new Null(new Token(TokenType.NULL, ""));

            public Null(Token token) {
                if (token.type() != TokenType.NULL) {
                    throw new IllegalArgumentException("Token type must be NULL, not " + token.type().toString());
                }
                this.token = token;
            }
        }

        public static record Str(Token token) implements Value {

            public Str(Token token) {
                if (token.type() != TokenType.STRING) {
                    throw new IllegalArgumentException("Token type must be STRING, not " + token.type().toString());
                }
                this.token = token;
            }

            public static Str of(String str) {
                return new Str(new Token(TokenType.STRING, str, ""));
            }

            public String value() {
                return token.value();
            }

        }

        public static record Number(Token token) implements Value {

            public Number(Token token) {
                if (token.type() != TokenType.NUMBER) {
                    throw new IllegalArgumentException("Token type must be NUMBER, not " + token.type().toString());
                }
                this.token = token;
            }

            public static Number of(String num) {
                return new Number(new Token(TokenType.NUMBER, num, ""));
            }
        }

        public static Json.Value of(Token token) {
            return switch (token.type()) {
                case TRUE, FALSE ->
                    new Json.Value.Bool(token);
                case STRING ->
                    new Json.Value.Str(token);
                case NUMBER ->
                    new Json.Value.Number(token);
                case NULL ->
                    new Json.Value.Null(token);
                default ->
                    throw new IllegalArgumentException("Token must be of value type, not " + token.type().toString());
            };
        }
    }

    public static record Error(int length, String value, String message, Json.Node attempt) implements Node {

        @Override
        public int entireLength() {
            return length;
        }

    }
}
