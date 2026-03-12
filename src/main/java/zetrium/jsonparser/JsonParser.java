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

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author Tomáš Zídek
 */
public class JsonParser {

    private ListIterator<Token> iter;

    public static void main(String[] args) throws LexingException {
        var lexer = new JsonLexer();
        for (int i = 0; i < 100; i++) {
            System.out.println(lexer.lex("{\"hallo\":-3.6e7,[null]}").toString());
        }
    }

    public Json.Node parse(List<Token> tokens) {
        this.iter = tokens.listIterator();
        return parseNode();
    }

    private Token peek() {
        iter.next();
        return iter.previous();
    }

    private Json.Node parseNode() {

        return switch (peek().type()) {
            case OPEN_CURLY ->
                parseObject();
            case TRUE, FALSE, NULL, NUMBER, STRING ->
                Json.Value.of(iter.next());
            case ERROR -> new Json.Error(iter.next(),"Illegal token");
            default ->
                new Json.Error(iter.next(),"Illegal start");

        };

    }

    private Json.Node parseObject() {
        var valueBuilder = new StringBuilder();
        var first = iter.next();
        var last = first;
        if (first.type()!=TokenType.OPEN_CURLY) {
            // should never happen
            throw new AssertionError("Token type must be OPEN_CURLY");
        }
        Map<String,Json.Node> children = new HashMap<>();
        while (true) {
            if (!iter.hasNext()) {
                return new Json.Error(first.start(),last.end(),valueBuilder.toString(),"Input ran out too early");
            }
            var cur = iter.next();
            if (cur.type()!=TokenType.STRING) {
                return new Json.Error
            }
        }
    }

}
