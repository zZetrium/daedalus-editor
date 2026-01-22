/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

/**
 *
 * @author xzidek
 */
public record Token(
        TokenType type,
        int startIndex,
        String value // null for non STRING types
        ) {

    public int getEndIndex() {
        return startIndex + value.length();
    }

    public int getLenght() {
        return value.length();
    }
    
}
