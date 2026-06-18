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
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents an opened project.
 *
 * @author Tomáš Zídek
 */
public class Project {

    private Path rootFolder;
    private ProjectNode root;

    public Project(String path) {
        rootFolder = Path.of(path);
    }

    public Project(Path path) {
        rootFolder = path;
    }

    public Project(File file) {
        rootFolder = file.toPath();
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
    public Path getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(Path path) {
        this.rootFolder = path;
    }

    public Project(Path rootFolder, ProjectNode root) {
        this.rootFolder = rootFolder;
        this.root = root;
    }
    
    

    @Override
    public String toString() {
        return "Project: " + getRootFolder();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.rootFolder);
        hash = 89 * hash + Objects.hashCode(this.root);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.rootFolder, other.rootFolder)) {
            return false;
        }
        return Objects.equals(this.root, other.root);
    }
    
    

}
