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

/**
 *
 * @author Tomáš Zídek
 */
public class JsonParser {

    private String text;
    private StringBuilder builder;
    private int cur;

    public static void main(String[] args) {
    }

    public JsonElement parseJson(String text) throws InvalidSyntaxException {
        this.text = text;
        this.builder = new StringBuilder(16);
        this.cur = 0;

        return parseElement();
    }

    private JsonElement parseElement() throws InvalidSyntaxException {
        char peeked = peek();
        switch (peeked) {
            case '{' -> {
                return parseObject();
            }
            default -> {
                if (isDigit(peeked) || peeked == 45) {
                    return parseNumber();
                }

                throw new InvalidSyntaxException("Element expected at " + cur);
            }

        }
    }

    private char pop() {
        return text.charAt(cur++);
    }

    private char peek(int i) {
        return text.charAt(cur + i);
    }

    private char peek() {
        return peek(0);
    }

    private void go(int i) {
        cur += i;
    }

    private JsonElement parseObject() {
        if (pop() != '{') {
            throw new IllegalStateException("Internal exception, when parsing object at " + cur);
        }
        return null; // todo
    }

    private JsonNumber parseNumber() {
        var peeked = peek();
        if (!isDigit(peeked) && peeked != '-') {
            throw new IllegalStateException("Internal exception, when parsing number at " + cur);
        }
        builder.setLength(0);
        if (peeked != '-') {
            pop();
            builder.append('-');
        }
        var popped = pop();
        while (isDigit(popped)) {
            builder.append(popped);
            popped = pop();
        }
        //throw
    }

    private boolean isDigit(char c) {
        return c >= 48 && c <= 57;
        
    }

    private boolean isWhitespace(char c) {
        
    }

}
