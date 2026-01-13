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
package zetrium.daedaluseditor.model;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents an opened project.
 *
 * @author xzidek
 */
public class Project {

    // platform specific path
    //private final StringProperty path = new SimpleStringProperty();
    private final transient Property<Path> projectRoot = new SimpleObjectProperty<>();

    public Project(String path) {
        projectRoot.setValue(FileSystems.getDefault().getPath(path));
    }
    public Project(Path path) {
        projectRoot.setValue(path);
    }

    /*public static Project[] fromFiles(List<File> files) {
        return fromFiles((File[]) files.toArray());
    }

    public static Project[] fromFiles(File... files) {
        Project[] projects = new Project[files.length];
        for (int i = 0; i < files.length; i++) {
            projects[i] = new Project(files[i].getAbsolutePath());
        }
        return projects;
    }*/


    public String getPath() {
        return projectRoot.getValue().toAbsolutePath().toString();
    }

    public Path getProjectRoot() {
        return projectRoot.getValue();
    }

    public void setProjectRoot(Path projectRoot) {
        this.projectRoot.setValue(projectRoot);
    }

    public Property<Path> projectRootProperty() {
        return projectRoot;
    }

    @Override
    public String toString() {
        return "Project: "+getPath();
    }
    
    

}
