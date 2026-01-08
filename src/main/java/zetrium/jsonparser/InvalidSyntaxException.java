/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package zetrium.jsonparser;

/**
 *
 * @author xzidek
 */
public class InvalidSyntaxException extends Exception {

    public InvalidSyntaxException() {
    }

    public InvalidSyntaxException(String message) {
        super(message);
    }

    public InvalidSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSyntaxException(Throwable cause) {
        super(cause);
    }

    public InvalidSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

 
}
