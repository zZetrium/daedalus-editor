module zetrium.daedaluseditor {
    requires javafx.controls;
    requires net.harawata.appdirs;
    //requires com.fasterxml.jackson.databind;
    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.zondicons;
    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    exports zetrium.daedaluseditor;
    exports zetrium.daedaluseditor.controller;
    exports zetrium.daedaluseditor.model;
    exports zetrium.daedaluseditor.view;

}
