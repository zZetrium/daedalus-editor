/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

/**
 *
 * @author xzidek
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
