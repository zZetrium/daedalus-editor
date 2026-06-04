/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.daedaluseditor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import zetrium.daedaluseditor.model.Project;

/**
 *
 * @author xzidek
 */
public class PrimaryViewController {

//    private ContextMenu fileMenu = Util.loadFXML("")
    @FXML
    private TreeView<Project> projectList;

    @FXML
    public void initialize() {
        projectList.setCellFactory(treeItem -> {
            return new TreeCell<>() {
                @Override
                protected void updateItem(File file, boolean empty) {
                    super.updateItem(file, empty);
                    if (empty || getTreeItem() == null || file == null) {
                        setGraphic(null);
                        return;
                    }
                    var item = getTreeItem();

                    var icon = item.isLeaf() ? new FontIcon(MaterialDesignF.FILE) : new FontIcon(MaterialDesignF.FOLDER);
                    var label = new Label(item.getValue().getName());
                    setGraphic(new HBox(icon, label));
                    getGraphic().setOnMouseClicked(evt -> {
                        
                    });
                }

            };
        });
    }

    @FXML
    private void chooseFile(ActionEvent evt) throws IOException {
        FileChooser fc = new FileChooser();
        List<File> selected = fc.showOpenMultipleDialog(App.getStage());
        if (selected == null) {
            return;
        }
        for (var f : selected) {
            projectList.getRoot().getChildren().add(createFileNode(f));
        }
    

    }

    @FXML
    private void chooseDirectory(ActionEvent evt) throws IOException {
        DirectoryChooser fc = new DirectoryChooser();
        File selected = fc.showDialog(App.getStage());
        if (selected == null) {
            return;
        }
        projectList.getRoot().getChildren().add(createFileNode(selected));

    }

    // This method creates a TreeItem to represent the given File. It does this
    // by overriding the TreeItem.getChildren() and TreeItem.isLeaf() methods
    // anonymously, but this could be better abstracted by creating a
    // 'FileTreeItem' subclass of TreeItem. However, this is left as an exercise
    // for the reader.
    private TreeItem<Project> createFileNode(final File f) {
        return new TreeItem<Project>(f) {
            // We cache whether the File is a leaf or not. A File is a leaf if
            // it is not a directory and does not have any files contained within
            // it. We cache this as isLeaf() is called often, and doing the
            // actual check on File is expensive.
            private boolean isLeaf;

            // We do the children and leaf testing only once, and then set these
            // booleans to false so that we do not check again during this
            // run. A more complete implementation may need to handle more
            // dynamic file system situations (such as where a folder has files
            // added after the TreeView is shown). Again, this is left as an
            // exercise for the reader.
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            {

                //setGraphic(isLeaf()? new FontIcon(MaterialDesignF.FILE) : new FontIcon(MaterialDesignF.FOLDER));
            }

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = getValue();
                    isLeaf = f.isFile();
                }

                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();
                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                        for (File childFile : files) {
                            children.add(createFileNode(childFile));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }

        };
    }

    @FXML
    private void pressTreeItem(MouseEvent evt) {
    }

    private static String stringifyPath(Path path) {
        return path.getFileName() != null ? path.getFileName().toString() : path.toString();

    }

}
