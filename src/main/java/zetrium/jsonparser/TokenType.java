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
public enum TokenType {
    OPEN_CURLY(1),CLOSE_CURLY(1),
    OPEN_SQUARE(1),CLOSE_SQUARE(1),
    STRING(-1),TRUE(4),FALSE(5),NULL(4),NUMBER(-1),
    COMMA(1),COLON(1);
    
    private final int lenght;
    private TokenType(int lenght) {
        this.lenght = lenght;
    }
    
    public int getLenght() {
        return lenght;
    }
    
    public boolean isLenghtFixed() {
        return lenght != -1;
    }
    
    
}
