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
        String value // null for non STRING types
        ) {

    public int getEndIndex() {
        return startIndex + value.length();
    }

    public int getLenght() {
        return value.length();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (this.startIndex != other.startIndex) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", startIndex=" + startIndex + ", value=" + value + '}';
    }
    
}
