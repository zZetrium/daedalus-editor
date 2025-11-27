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

import java.nio.file.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author Tomáš Zídek
 */
public class Model {

    // list of opened projects
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableMap<Path, OpenFile> openedFiles = FXCollections.observableHashMap();

    public Model() {
        projects.addAll(
                new Project("K:\\Git\\Projects\\daedalus-editor"));
    }

    /**
     * Returns the list of all currently opened projects.
     *
     * @return The currently opened projects.
     */
    public ObservableList<Project> getProjects() {
        return projects;
    }

    /**
     * Returns the list of all currently opened files.
     *
     * @return The currently opened files.
     */
    public ObservableMap<Path, OpenFile> getOpenedFiles() {
        return openedFiles;
    }

 

}
