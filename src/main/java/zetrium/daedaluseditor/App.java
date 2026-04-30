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
package zetrium.daedaluseditor;

import atlantafx.base.theme.*;
import java.io.IOException;

import zetrium.daedaluseditor.view.View;
import zetrium.daedaluseditor.controller.ControllerImplementation;
import zetrium.daedaluseditor.controller.Controller;
import zetrium.daedaluseditor.model.Model;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tomáš Zídek
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {

        Model m = new Model();
        Controller c = new ControllerImplementation(m, null);
        try {
            //View v = new View(stage, m, c);
            //c.setMessageDisplayer(v);
            App.scene = new Scene(loadFXML("primary"));
        } catch (IOException ex) {
            System.getLogger(App.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        stage.setScene(scene);
        stage.show();
        stage.setWidth(800);
        stage.setHeight(400);
        stage.setX(0);
        stage.setY(0);

        // Apply AtlantaFX theme globally
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        stage.getScene().getStylesheets().add(
                getClass().getClassLoader().getResource("fix.css").toExternalForm()
        );

    }

    public static void run() {
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
