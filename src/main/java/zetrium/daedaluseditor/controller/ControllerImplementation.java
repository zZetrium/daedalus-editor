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
package zetrium.daedaluseditor.controller;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import zetrium.daedaluseditor.model.Model;
import zetrium.daedaluseditor.model.OpenFile;
import zetrium.daedaluseditor.model.Project;
import zetrium.daedaluseditor.view.View;

/**
 *
 * @author Tomáš Zídek
 */
public class ControllerImplementation implements Controller {

    private Model model;
    private MessageDisplayer messageDisplayer;

    public ControllerImplementation(Model model, MessageDisplayer messageDisplayer) {
        this.model = model;
        this.messageDisplayer = messageDisplayer;
    }

    @Override
    public void sourceEdited(String source) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Path[] listFiles(Path path) {
        if (path != null && Files.isDirectory(path)) {
            Path[] files;
            try {
                files = Files.list(path).toArray((lenght) -> new Path[lenght]);
                return files;
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                return new Path[0];
            }

        }
        return new Path[0];
    }

    @Override
    public OpenFile openFile(Path path) {
        if (model.getOpenedFiles().containsKey(path)) {
            return model.getOpenedFiles().get(path);
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path cannot be a directory.");
        }

        String content;
        try {
            content = Files.readString(path);
            OpenFile of = new OpenFile(path, false, content);
            model.getOpenedFiles().put(path, of);
            return of;
        } catch (IOException ex) {
            messageDisplayer.showError("Exception occured while loading "+path.toString(),ex.toString());
            return null;
        }

    }

    @Override
    public Project openProject(Path path) {
        Project p = new Project(path);
        model.getProjects().add(p);
        return p;
        
    }
    
    

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public MessageDisplayer getMessageDisplayer() {
        return messageDisplayer;
    }

    @Override
    public void setMessageDisplayer(MessageDisplayer messageDisplayer) {
        this.messageDisplayer = messageDisplayer;
    }

}
