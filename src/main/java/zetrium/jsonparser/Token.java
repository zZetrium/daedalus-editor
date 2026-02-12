/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

import java.util.Objects;

/**
 *
 * @author xzidek
 */
public record Token(
        TokenType type,
        int startIndex,
        int endIndex,
        String value,
        String whitespace
// null for non STRING and NUMBER types
        ) {

    

    public int getLenght() {
        return endIndex-startIndex;
    }



    
}
