package org.wolffi.cueprogram.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.model.cue.Cue;
import org.wolffi.cueprogram.model.cue.CueFactory;
import org.wolffi.cueprogram.model.enums.CueType;
import org.wolffi.cueprogram.view.PlayerView;


public class PlayerController {

    private static final Logger log = LogManager.getLogger();

    private final Player model;

    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        view.getBtnAddCue().setOnAction(_ -> addCue("Mond mit der Hand berührt", "/mnt/shared_data/Musik/(Disc 5) 05 - Hab Den Mond Mit Der Hand Berührt.mp3", 100.0d, view.getCueListSize()));
        view.getBtnPlayCue().setOnAction(_ -> playCue(view.getSelectedCueIndex()));
        view.getBtnStopCue().setOnAction(_ -> stopCue(view.getSelectedCueIndex()));
        view.getBtnFadeOutCue().setOnAction(_ -> fadeOutCue(view.getSelectedCueIndex()));
        view.getBtnPauseCue().setOnAction(_ -> pauseCue(view.getSelectedCueIndex()));
    }

    private void addCue(String name, String path, double volume, int listIndex) {
        log.debug("Will add cue to list");
        Cue cue = CueFactory.createCue(CueType.AUDIO, listIndex, name, path, volume);
        model.addCue(cue);
    }

    private boolean isValidIndex(int index) { return index >= 0; }

    private void playCue(int index) {
        log.debug("Will play cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.playCue(index);
        }
    }

    private void stopCue(int index) {
        log.debug("Will stop cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.stopCue(index);
        }
    }

    private void pauseCue(int index) {
        log.debug("Will pause cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.pauseCue(index);
        }
    }

    private void fadeOutCue(int index) {
        log.debug("Will fade out cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.fadeOutCue(index);
        }
    }
}