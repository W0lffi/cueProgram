package org.wolffi.cueprogram;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.controller.PlayerController;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.view.PlayerView;

/**
 * The main class has the main method which is the entry point of the application.
 * @author Wolffi
 */
public class Main extends Application {

    private final Logger log = LogManager.getLogger();

    /**
     * Initializes the model, view and controller.
     * @param stage The stage
     */
    @Override
    public void start(Stage stage) {
        Player model = new Player();
        PlayerView view = new PlayerView(stage);
        model.addListener(view.getCueView());
        new PlayerController(model, view);
        log.info("Initialized");
    }

    /**
     * The main method is the entry point and calls the launch method of JavaFX.
     * @param args Passed command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}