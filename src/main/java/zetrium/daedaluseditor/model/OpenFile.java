/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.daedaluseditor.model;

import java.nio.file.Path;

/**
 *
 * @author xzidek
 */
public class OpenFile {
    Path path;
    boolean saved;
    String currentContent;

    public OpenFile(Path path, boolean saved, String currentContent) {
        this.path = path;
        this.saved = saved;
        this.currentContent = currentContent;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(String currentContent) {
        this.currentContent = currentContent;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }


    
    
    
}
