/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

/**
 *
 * @author xzidek
 */
public class LexingException extends Exception {

    public LexingException() {
    }

    public LexingException(String message) {
        super(message);
    }

    public LexingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexingException(Throwable cause) {
        super(cause);
    }

    public LexingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
