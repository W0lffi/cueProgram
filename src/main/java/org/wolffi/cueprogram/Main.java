package org.wolffi.cueprogram;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.controller.PlayerController;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.view.PlayerView;

public class Main extends Application {

    private final Logger log = LogManager.getLogger();
    @Override
    public void start(Stage stage) {
        Player model = new Player();
        PlayerView view = new PlayerView(stage);
        model.addListener(view.getCueView());
        new PlayerController(model, view);
        log.info("Initialized");
    }

    public static void main(String[] args) {
        launch();
    }
}