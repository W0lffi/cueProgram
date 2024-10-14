package org.wolffi.cueprogram.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * ToDo: Use fade out/in time of cue class instead of static time
 */
public final class Player {

    private static final Logger log = LogManager.getLogger();

    private final List<MediaPlayer> players;
    private final ObservableList<Cue> cues;

    public Player() {
        players = new ArrayList<>();
        cues = FXCollections.observableArrayList();
        log.debug("Player (model) initialized");
    }

    public void addCue(Cue cue) {
        cues.add(cue);
        AudioCue sound = (AudioCue) cue;
        players.add(new MediaPlayer(sound.getSound()));
        players.getLast().setVolume(sound.getVolume());
        log.debug("Added cue to list");
    }

    public void playCue(int index) {
        players.get(index).play();
        log.info("Playing cue with following index: {}", index);
    }

    public void stopCue(int index) {
        MediaPlayer player = players.get(index);
        player.stop();
        log.info("Stopped cue with following index: {}", index);
    }

    public void pauseCue(int index) {
        MediaPlayer player = players.get(index);
        player.setStartTime(player.getCurrentTime());
        player.stop();
        log.info("Paused cue with following index: {}", index);
    }

    public void fadeOutCue(int index) {
        MediaPlayer player = players.get(index);
        Timeline fadeOut = new Timeline();
        Duration fadeDuration = Duration.millis(3_000.0d);
        double fadeSteps = 30.0d;
        double volumeDecrease = 1.0d / fadeSteps;

        for (int i = 0; i <= fadeSteps; i++) {
            double volume = 1.0d - (i * volumeDecrease);
            fadeOut.getKeyFrames().add(
                new KeyFrame(fadeDuration.divide(fadeSteps).multiply(i),
                    _ -> player.setVolume(volume))
            );
        }
        fadeOut.setCycleCount(1);
        fadeOut.play();
        fadeOut.setOnFinished(_ -> {
            players.get(index).stop();
            log.info("Faded out cue with following index: {}", index);
        });
    }

    public void addListener(ListView<Cue> cueView) {
        cues.addListener((ListChangeListener<Cue>) listener -> {
            while (listener.next()) {
                if (listener.wasUpdated() || listener.wasPermutated()) {
                    ;
                } else if (listener.wasAdded() || listener.wasRemoved()) {
                    cueView.setItems(this.cues);
                    int lastIndex = cues.size() - 1;
                    if (!cueView.getItems().isEmpty()) {
                        cueView.getSelectionModel().select(lastIndex);
                        cueView.scrollTo(lastIndex);
                    }
                }
            }
            log.info("Updated list view based on cue list");
        });
    }
}
