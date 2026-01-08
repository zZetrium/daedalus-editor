/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.jsonparser;

/**
 *
 * @author xzidek
 */
class JsonNumber extends JsonValue {
    private String value;

    public JsonNumber(String value, int startOffset, int endOffset, int precedingWs) {
        super(startOffset, endOffset, precedingWs);
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
    

}
