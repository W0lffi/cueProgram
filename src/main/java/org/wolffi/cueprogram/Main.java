package org.wolffi.cueprogram;

import javafx.application.Application;
import javafx.stage.Stage;
import org.wolffi.cueprogram.controller.PlayerController;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.view.PlayerView;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Player model = new Player();
        PlayerView view = new PlayerView(stage);
        model.addListener(view.getCueView());
        new PlayerController(model, view);
    }

    public static void main(String[] args) {
        launch();
    }
}