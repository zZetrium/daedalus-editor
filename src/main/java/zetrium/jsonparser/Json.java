/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zetrium.jsonparser;

import java.util.List;
import java.util.Map;
import static zetrium.jsonparser.TokenType.FALSE;

/**
 *
 * @author xzidek
 */
public final class Json {

    public static sealed interface Node permits Value, Array, Object, Error {

        int start();

        int end();

        default int length() {
            return end() - start();
        }

    }

    public static record Array(int start, int end, List<? extends Node> elements) implements Node {

        public Array(int start, int end, List<? extends Node> elements) {
            this.start = start;
            this.end = end;
            this.elements = List.copyOf(elements);
        }

    }

    public static record Object(int start, int end, Map<String, ? extends Node> children) implements Node {

        public Object(int start, int end, Map<String, ? extends Node> children) {
            this.start = start;
            this.end = end;
            this.children = Map.copyOf(children);
        }
    }

    public static sealed interface Value extends Node permits Value.Bool, Value.Null, Value.Str, Value.Number {

        public static record Bool(int start, int end, boolean value) implements Value {

            public Bool(Token token) {
                this(token.start(), token.end(), switch (token.type()) {
                    case TRUE ->
                        true;
                    case FALSE ->
                        false;
                    default ->
                        throw new IllegalStateException("Token type must be TRUE or FALSE, not " + token.type().toString());

                });
            }
        }

        public static record Null(int start, int end) implements Value {

            public Null(Token token) {
                if (token.type() != TokenType.NULL) {
                    throw new IllegalStateException("Token type must be NULL, not " + token.type().toString());
                }
                this(token.start(), token.end());
            }
        }

        public static record Str(int start, int end, String value) implements Value {

            public Str(Token token) {
                if (token.type() != TokenType.STRING) {
                    throw new IllegalStateException("Token type must be STRING, not " + token.type().toString());
                }
                this(token.start(), token.end(), token.value());
            }
        }

        public static record Number(int start, int end, String value) implements Value {

            public Number(Token token) {
                if (token.type() != TokenType.NUMBER) {
                    throw new IllegalStateException("Token type must be NUMBER, not " + token.type().toString());
                }
                this(token.start(), token.end(), token.value());
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
                    throw new IllegalStateException("Token must be of value type, not " + token.type().toString());
            };
        }
    }

    public static record Error(int start, int end, String value,String message) implements Node {

        public Error(Token token,String message) {
            this(token.start(), token.end(), token.value(),message);
        }

    }
}
