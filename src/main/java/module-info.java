module org.wolffi.cueprogram {
    requires javafx.controls;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.media;
    requires org.apache.commons.lang3;

    exports org.wolffi.cueprogram;
    exports org.wolffi.cueprogram.model;
    exports org.wolffi.cueprogram.view;
    exports org.wolffi.cueprogram.controller;
}