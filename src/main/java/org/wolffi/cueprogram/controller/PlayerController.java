package org.wolffi.cueprogram.controller;

import org.apache.commons.lang3.StringUtils;
import org.wolffi.cueprogram.model.AudioCue;
import org.wolffi.cueprogram.model.Cue;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.view.PlayerView;


public class PlayerController {

    private final Player model;
    private final PlayerView view;

    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
        this.view.getBtnAddCue().setOnAction(_ -> addCue("Mond mit der Hand berührt", "/mnt/shared_data/Musik/(Disc 5) 05 - Hab Den Mond Mit Der Hand Berührt.mp3", 100.0d));
        this.view.getBtnPlayCue().setOnAction(_ -> playCue(this.view.getCueView().getSelectionModel().getSelectedIndex()));
        this.view.getBtnStopCue().setOnAction(_ -> stopCue(this.view.getCueView().getSelectionModel().getSelectedIndex()));
        this.view.getBtnFadeOutCue().setOnAction(_ -> fadeOutCue(this.view.getCueView().getSelectionModel().getSelectedIndex()));
    }

    private void addCue(String name, String path, double volume) {
        if (StringUtils.isEmpty(name)) {
            name = path;
        }
        Cue cue = new AudioCue(name, path, volume);
        model.addCue(cue);
    }

    private boolean isValidIndex(int index) { return index >= 0; }

    private void playCue(int index) {
        System.out.println(index);
        if (isValidIndex(index)) {
            this.model.playCue(index);
        }
    }

    private void stopCue(int index) {
        System.out.println(index);
        if (isValidIndex(index)) {
            this.model.stopCue(index);
        }
    }

    private void fadeOutCue(int index) {
        System.out.println(index);
        if (isValidIndex(index)) {
            this.model.fadeOutCue(index, 3000.0d);
        }
    }
}