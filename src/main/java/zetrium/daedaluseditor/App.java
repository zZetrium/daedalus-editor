package zetrium.daedaluseditor;

import atlantafx.base.theme.Dracula;
import zetrium.daedaluseditor.view.View;
import zetrium.daedaluseditor.controller.ControllerImplementation;
import zetrium.daedaluseditor.controller.Controller;
import zetrium.daedaluseditor.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Apply AtlantaFX theme globally
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());

        Model m = new Model();
        Controller c = new ControllerImplementation(m, null);
        View v = new View(stage, m, c);

        stage.setScene(v.getScene());
        stage.show();
        stage.setWidth(800);
        stage.setHeight(400);
        stage.setX(0);
        stage.setY(0);
    }

    public static void run() {
        launch();
    }

}
