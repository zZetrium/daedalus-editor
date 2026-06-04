/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.daedaluseditor;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author xzidek
 */
public class Util {

    private static FXMLLoader loader;

    static Parent loadFXML(String fxml) throws IOException {
        if (loader == null) {
            loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        }
        return loader.load();
    }

}
