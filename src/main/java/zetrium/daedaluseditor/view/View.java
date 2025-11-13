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
package zetrium.daedaluseditor.view;

import atlantafx.base.theme.Styles;
import zetrium.daedaluseditor.controller.Controller;
import zetrium.daedaluseditor.model.Model;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.zondicons.Zondicons;
import zetrium.daedaluseditor.model.Project;

/**
 *
 * @author Tomáš Zídek
 */
public class View {

    private Stage stage;
    private Model model;
    private Controller controller;

    private Path selectedFile;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    private ReadOnlyObjectWrapper<Scene> scene = new ReadOnlyObjectWrapper<>();
    private ListView<Project> projectList;

    /*  private HBox topBar;
    private Button fileButton;
    private ContextMenu fileButtonMenu;
    private MenuItem openFileMenuItem;

    private TabPane editorPane;
    private ToggleGroup viewTypeTogglesGroup;

    private Tab sourceTab;
    private TextArea sourceEditor;

     */
    // private BorderPane borderPane;
    public View(Stage stage, Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        this.stage = stage;
        setupUI();
        connectModel();
    }

    private void setupUI() {

        var topBar = setupTopBar();

        var editorBox = setupEditor();
        HBox.setHgrow(editorBox, Priority.ALWAYS);

        projectList = setupProjectList();

        var verticalDown = new HBox(projectList, editorBox);
        verticalDown.fillHeightProperty().set(true);
        verticalDown.setAlignment(Pos.CENTER);
        VBox.setVgrow(verticalDown, Priority.ALWAYS);

        var borderPane = new VBox(topBar, verticalDown);
        borderPane.fillWidthProperty().set(true);

        scene.set(new Scene(borderPane));
        scene.get().fillProperty().set(Color.RED);
    }

    private ListView<Project> setupProjectList() {
      var  projectList = new ListView<Project>();

        projectList.setCellFactory((param) -> new ListCell<Project>() {

            @Override
            protected void updateItem(Project item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }
                //var leftPart = new HBox();
                var pane = new HBox(new FontIcon(Zondicons.FOLDER), new Label(new File(item.getPath()).getName()));
                //pane.setAlignment(Pos.CENTER);
                pane.setSpacing(20);
                pane.setPadding(new Insets(0, 0, 0, 10));
                var wrapper = new VBox(pane);
                wrapper.setAlignment(Pos.CENTER);
                setGraphic(wrapper);
            }
        }
        );

        projectList.setMinWidth(250);
        VBox.setVgrow(projectList, Priority.ALWAYS);

        return projectList;
    }

    private Node setupTopBar() {
        var openFileMenuItem = new MenuItem("Open");
        openFileMenuItem.setOnAction((event) -> {
            FileChooser fc = new FileChooser();
            List<File> selected = fc.showOpenMultipleDialog(stage.getOwner());
            if (selected == null) {
                return;
            }
            controller.openProjects(Project.fromFiles(selected));
        });
        var fileButtonMenu = new ContextMenu(openFileMenuItem);
        var fileButton = new Button("Project");
        fileButton.getStyleClass().add(Styles.FLAT);

        fileButton.setOnAction(t -> {
            Bounds buttonBounds = fileButton.localToScreen(fileButton.getBoundsInLocal());
            fileButtonMenu.show(fileButton, buttonBounds.getMinX(), buttonBounds.getMaxY());
        });
        return new HBox(fileButton);
    }

    private Node setupEditor() {
        var editorPane = new TabPane();
        var viewTypeTogglesGroup = new ToggleGroup();
        var sourceOption = new RadioButton("Source");
        sourceOption.setToggleGroup(viewTypeTogglesGroup);
        var visualOption = new RadioButton("Visual");
        visualOption.setToggleGroup(viewTypeTogglesGroup);
        var viewTypeToggles = new HBox(sourceOption, visualOption);
        visualOption.paddingProperty().set(new Insets(0, 0, 0, 20));
        sourceOption.paddingProperty().set(new Insets(0, 0, 0, 20));
        VBox.setVgrow(editorPane, Priority.ALWAYS);

        projectList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            editorPane.getTabs().add(new Tab(newVal.projectRootProperty().getName(), new TextArea()));
        });
        var editorBox = new VBox(viewTypeToggles, editorPane);

        return editorBox;
    }

    private Node setupEditTab() {
        return null;
    }

    private void connectModel() {
        projectList.setItems(model.getProjects());

    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        connectModel();
    }

    public Scene getScene() {
        return scene.getValue();
    }

    public ReadOnlyProperty<Scene> sceneProperty() {
        return scene.getReadOnlyProperty();
    }

    public void openFile(Path file) {

    }

}
