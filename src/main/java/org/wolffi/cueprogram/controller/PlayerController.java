package org.wolffi.cueprogram.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.AudioCue;
import org.wolffi.cueprogram.model.Cue;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.view.PlayerView;


/**
 * ToDo: Generic generation of cues so cue type hasn't to be specified here
 */
public class PlayerController {

    private static final Logger log = LogManager.getLogger();

    private final Player model;
    private final PlayerView view;

    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
        this.view.getBtnAddCue().setOnAction(_ -> addCue("Mond mit der Hand berührt", "/mnt/shared_data/Musik/(Disc 5) 05 - Hab Den Mond Mit Der Hand Berührt.mp3", 100.0d));
        this.view.getBtnPlayCue().setOnAction(_ -> playCue(this.view.getSelectedCueIndex()));
        this.view.getBtnStopCue().setOnAction(_ -> stopCue(this.view.getSelectedCueIndex()));
        this.view.getBtnFadeOutCue().setOnAction(_ -> fadeOutCue(this.view.getSelectedCueIndex()));
        this.view.getBtnPauseCue().setOnAction(_ -> pauseCue(this.view.getSelectedCueIndex()));
    }

    private void addCue(String name, String path, double volume) {
        log.info("Cue will be added to list");
        log.debug("With following parameters: name={}, path={}, volume={}", name, path, volume);
        if (StringUtils.isEmpty(name)) {
            log.debug("Using path as name because no name is provided");
            name = path;
        }
        Cue cue = new AudioCue(name, path, volume);
        model.addCue(cue);
    }

    private boolean isValidIndex(int index) { return index >= 0; }

    private void playCue(int index) {
        log.info("Start to play cue with following index: {}", index);
        if (isValidIndex(index)) {
            this.model.playCue(index);
        }
    }

    private void stopCue(int index) {
        log.info("Stop cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.stopCue(index);
        }
    }

    private void pauseCue(int index) {
        log.info("Pause cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.pauseCue(index);
        }
    }

    private void fadeOutCue(int index) {
        log.info("Fade out cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.fadeOutCue(index);
        }
    }
}