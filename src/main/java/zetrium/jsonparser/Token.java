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

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author Tomáš Zídek
 */
public record Token(
        TokenType type,
        String value, // null for fixed length types
        String whitespace
        ) {

    public String value() {
        return type.value() == null ? this.value : type.value();
    }

    public Token(TokenType type, String value, String whitespace) {
        Objects.requireNonNull(type, "type must not be null");
        if (type.isLengthFixed()) {
            if (value != null && !value.equals(type.value())) {
                throw new IllegalArgumentException("value for fixed token types must be null or equal to type.value()");
            }
            value = type.value();

        } else {
            if (value == null) {
                throw new IllegalArgumentException("value for non-fixed length types must be non-null");
            }
            if (type == TokenType.NUMBER && !isValidNumber(value)) {
                throw new IllegalArgumentException("number values must match -?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");
            }

        }
        if (whitespace == null) {
            whitespace = "";
        }

        this.type = type;
        this.value = value;
        this.whitespace = whitespace;
    }

    public Token(TokenType type, String whitespace) {
        if (!type.isLengthFixed()) {
            throw new IllegalArgumentException("this constructor must not be used with non-fixed token types");
        }
        this(type, null, whitespace);
    }

    public int entireLength() {
        return length() + whitespace.length();
    }

    public int length() {
        return type.isLengthFixed() ? type.length() : value.length();
    }
    private static Predicate<String> numPredicate = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?").asMatchPredicate();

    public static boolean isValidNumber(String num) {
        return numPredicate.test(num);
    }
    
    public static String recover(Token... tokens) {
        var result = new StringBuilder(tokens.length);
        for (var token:tokens) {
            result.append(token.value());
        }
        return result.toString();
    }
    
    public static String recover(List<Token> tokens) {
        var result = new StringBuilder(tokens.size());
        for (var token:tokens) {
            result.append(token.whitespace()).append(token.value());
        }
        return result.toString();
    }

}
