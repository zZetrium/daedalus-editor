/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.daedaluseditor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import zetrium.daedaluseditor.model.Project;

/**
 *
 * @author xzidek
 */
public class PrimaryViewController {
    
//    private ContextMenu fileMenu = Util.loadFXML("")
    @FXML
    private TreeView<Project> projectList;

    public PrimaryViewController() {
    }

    @FXML
    private void chooseFile(ActionEvent evt) throws IOException {
        FileChooser fc = new FileChooser();
        List<File> selected = fc.showOpenMultipleDialog(App.getStage());
        if (selected == null) {
            return;
        }
    }
    
    @FXML
    private void chooseDirectory(ActionEvent evt) throws IOException {
        DirectoryChooser fc = new DirectoryChooser();
            File selected = fc.showDialog(App.getStage());
            if (selected == null) {
                return;
            }
    }

}
