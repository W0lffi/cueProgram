module org.wolffi.cueprogram {
    requires javafx.controls;

    requires org.controlsfx.controls;
    requires javafx.media;
    requires org.apache.commons.lang3;
    requires org.apache.logging.log4j;

    exports org.wolffi.cueprogram;
    exports org.wolffi.cueprogram.model;
    exports org.wolffi.cueprogram.view;
    exports org.wolffi.cueprogram.controller;
    exports org.wolffi.cueprogram.model.cue;
    exports org.wolffi.cueprogram.model.enums;
}