package org.wolffi.cueprogram.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.cue.Cue;


/**
 * The player hold the list of cues and can also control every of it.
 * @author Wolffi
 */
public final class Player {

    private static final Logger log = LogManager.getLogger();

    private final ObservableList<Cue> cues;

    public Player() {
        cues = FXCollections.observableArrayList();
        log.debug("Player (model) initialized");
    }

    public void addCue(Cue cue) {
        log.info("Will add \"{}\" to list", cue.getName());
        cues.add(cue);
    }

    public void playCue(int index) {
        Cue cue = cues.get(index);
        log.info("Will play \"{}\"", cue.getName());
        cue.play();
    }

    public void stopCue(int index) {
        Cue cue = cues.get(index);
        log.info("Will stop \"{}\"", cue.getName());
        cue.stop();
    }

    public void pauseCue(int index) {
        Cue cue = cues.get(index);
        log.info("Will pause \"{}\"", cue.getName());
        cue.pause();
    }

    public void fadeInCue(int index) {
        Cue cue = cues.get(index);
        log.info("Will fade in \"{}\" to list", cue.getName());
        cue.fadeIn();
    }

    public void fadeOutCue(int index) {
        Cue cue = cues.get(index);
        log.info("Will fade out \"{}\"", cue.getName());
        cue.fadeOut();
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
