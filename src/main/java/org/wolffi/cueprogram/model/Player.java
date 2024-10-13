package org.wolffi.cueprogram.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public final class Player {
    private final List<MediaPlayer> players;
    private final ObservableList<Cue> cues;

    public Player() {
        players = new ArrayList<>();
        cues = FXCollections.observableArrayList();
    }

    public void addCue(Cue cue) {
        cues.add(cue);
        AudioCue sound = (AudioCue) cue;
        players.add(new MediaPlayer(sound.getSound()));
        players.getLast().setVolume(sound.getVolume());
    }

    public void playCue(int index) {
        players.get(index).play();
    }

    public void stopCue(int index) {
        MediaPlayer player = players.get(index);
        player.setStartTime(player.getCurrentTime());
        player.stop();
    }

    public void fadeOutCue(int index, double duration) {
        MediaPlayer player = players.get(index);
        Timeline fadeOut = new Timeline();
        Duration fadeDuration = Duration.millis(duration);
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
        fadeOut.setOnFinished(_ -> players.get(index).stop());
    }

    public void addListener(ListView<Cue> cueView) {
        cues.addListener((ListChangeListener<Cue>) listener -> {
            while (listener.next()) {
                if (listener.wasUpdated()) {
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
        });
    }
}
