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
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import zetrium.daedaluseditor.controller.MessageDisplayer;
import zetrium.daedaluseditor.model.OpenFile;
import zetrium.daedaluseditor.model.Project;

/**
 *
 * @author Tomáš Zídek
 */
public class View implements MessageDisplayer{

    private Stage stage;
    private Model model;
    private Controller controller;
    private ObservableMap<OpenFile, Tab> fileTabs = FXCollections.observableHashMap();

    //private Path selectedFile;
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
    private TreeView<Path> projectList;

    private TabPane editorPane;

    private ListChangeListener<Project> projectListListener;

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
        projectList = setupProjectList();

        var editorBox = setupEditor();
        HBox.setHgrow(editorBox, Priority.ALWAYS);

        var verticalDown = new HBox(projectList, editorBox);
        verticalDown.fillHeightProperty().set(true);
        verticalDown.setAlignment(Pos.CENTER);
        VBox.setVgrow(verticalDown, Priority.ALWAYS);

        var borderPane = new VBox(topBar, verticalDown);
        borderPane.fillWidthProperty().set(true);

        connectModel();

        scene.set(new Scene(borderPane));
        scene.get().fillProperty().set(Color.RED);
    }

    private static String stringify(Path path) {
        return path.getFileName() != null ? path.getFileName().toString() : path.toString();

    }

    private TreeItem<Path> createNode(final Path f) {
        return new TreeItem<Path>(f) {
            // We cache whether the ProjectNode is a leaf or not. A ProjectNode is a leaf if
            // it is not a directory and does not have any files contained within
            // it. We cache this as isLeaf() is called often, and doing the
            // actual check on ProjectNode is expensive.
            private boolean isLeaf;

            // We do the children and leaf testing only once, and then set these
            // booleans to false so that we do not check again during this
            // run. A more complete implementation may need to handle more
            // dynamic file system situations (such as where a folder has files
            // added after the TreeView is shown). Again, this is left as an
            // exercise for the reader.
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<Path>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the ProjectNode contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    Path f = (Path) getValue();
                    isLeaf = !Files.isDirectory(f);
                }

                return isLeaf;
            }

            private ObservableList<TreeItem<Path>> buildChildren(TreeItem<Path> TreeItem) {
                Path path = TreeItem.getValue();
                /*if (path != null && Files.isDirectory(path)) {
                    Path[] files;
                    try {
                        files = Files.list(path).toArray((lenght) -> new Path[lenght]);
                    } catch (IOException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        return FXCollections.emptyObservableList();
                    }
                    if (files != null) {
                        ObservableList<TreeItem<Path>> children = FXCollections.observableArrayList();

                        for (Path childProjectNode : files) {
                            children.add(createNode(childProjectNode));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();*/
                Path[] files = controller.listFiles(path);
                ObservableList<TreeItem<Path>> children = FXCollections.observableArrayList();

                for (Path childProjectNode : files) {
                    children.add(createNode(childProjectNode));
                }

                return children;
            }
        };
    }

    private TreeCell<Path> createCell(TreeItem<Path> item) {
        return new TreeCell<>() {
            @Override
            protected void updateItem(Path t, boolean empty) {
                super.updateItem(t, empty);
                if (empty || t == null) {

                    setText(null);

                    setGraphic(null);

                    return;

                }
                var icon = Files.isDirectory(t) ? new FontIcon(MaterialDesignF.FOLDER) : new FontIcon(MaterialDesignF.FILE);
                var label = new Label(stringify(t));
                var graphic = new HBox(icon, label);
                //setScale(label, 0.7);
                // this.prefWidth(10);
                graphic.setSpacing(5);
                setGraphic(graphic);
                setPrefHeight(20);
            }

        };
    }

    private void setScale(Node n, double scale) {
        n.setScaleX(scale);
        n.setScaleY(scale);

    }

    private TreeView<Path> setupProjectList() {

        projectList = new TreeView<>();
        projectList.setFixedCellSize(20);
        projectList.showRootProperty().set(false);
        projectListListener = (ListChangeListener.Change<? extends Project> change) -> {
            System.out.println("hear");
            TreeItem<Path> root = new TreeItem<>(null);
            for (Project p : model.getProjects()) {
                root.getChildren().add(createNode(p.getProjectRoot()));
            }
            projectList.setRoot(root);
        };

        projectList.setCellFactory((p) -> {
            return createCell(p.getRoot());
        });

        projectList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            if (!Files.isDirectory(newVal.getValue())) {
                openFile(newVal.getValue());

            }

        });

        projectList.setPrefWidth(250);
        VBox.setVgrow(projectList, Priority.ALWAYS);

        return projectList;
    }

    private void openFile(Path p) {
        OpenFile of = controller.openFile(p);
        if (of==null) {
            return;
        }
        fileTabs.put(of, new Tab(of.getPath().getFileName().toString(), new Label(of.getCurrentContent())));
        editorPane.getTabs().add(fileTabs.get(of));
        selectFile(model.getOpenedFiles().get(p));
    }

    private void selectFile(OpenFile f) {
        editorPane.getSelectionModel().select(fileTabs.get(f));
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
            System.out.println(model.getProjects().toString());
            Bounds buttonBounds = fileButton.localToScreen(fileButton.getBoundsInLocal());
            fileButtonMenu.show(fileButton, buttonBounds.getMinX(), buttonBounds.getMaxY());
        });
        return new HBox(fileButton);
    }

    private Node setupEditor() {
        editorPane = new TabPane();
        var viewTypeTogglesGroup = new ToggleGroup();
        var sourceOption = new RadioButton("Source");
        sourceOption.setToggleGroup(viewTypeTogglesGroup);
        var visualOption = new RadioButton("Visual");
        visualOption.setToggleGroup(viewTypeTogglesGroup);
        var viewTypeToggles = new HBox(sourceOption, visualOption);
        visualOption.paddingProperty().set(new Insets(0, 0, 0, 20));
        sourceOption.paddingProperty().set(new Insets(0, 0, 0, 20));
        VBox.setVgrow(editorPane, Priority.ALWAYS);
        // todo

        var editorBox = new VBox( editorPane);
        editorBox.setPrefWidth(200);
        return editorBox;
    }

    private Node setupEditTab() {
        return null;
    }

    private void connectModel() {

        projectListListener.onChanged(null);

        model.getProjects().addListener(projectListListener);
        //projectList.setItems(model.getProjects());

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

    @Override
    public void showError(String title,String message) {
        Alert popup = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        popup.setTitle(title);
        
        popup.showAndWait();
    }

    @Override
    public void showWarning(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean showConfirmation(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showInformation(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
