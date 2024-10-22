package org.wolffi.cueprogram.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.Player;
import org.wolffi.cueprogram.model.cue.Cue;
import org.wolffi.cueprogram.model.cue.CueFactory;
import org.wolffi.cueprogram.model.enums.CueType;
import org.wolffi.cueprogram.view.PlayerView;


/**
 * This controller provides methods to control the cues from the list.
 * @author Wolffi
 */
public class PlayerController {

    private static final Logger log = LogManager.getLogger();

    private final Player model;

    /**
     * The controller constructor expects a player what is manipulated by the provided methods and a view where the
     * methods are bind to.
     * @param model The player
     * @param view The view
     */
    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        view.getBtnAddCue().setOnAction(_ -> addCue("Mond mit der Hand berührt", "/mnt/shared_data/Musik/(Disc 5) 05 - Hab Den Mond Mit Der Hand Berührt.mp3", 100.0d, view.getCueListSize()));
        view.getBtnPlayCue().setOnAction(_ -> playCue(view.getSelectedCueIndex()));
        view.getBtnStopCue().setOnAction(_ -> stopCue(view.getSelectedCueIndex()));
        view.getBtnFadeOutCue().setOnAction(_ -> fadeOutCue(view.getSelectedCueIndex()));
        view.getBtnPauseCue().setOnAction(_ -> pauseCue(view.getSelectedCueIndex()));
    }

    /**
     * This method adds a new cue to the list of cues. If no name is provided the path is taken as name.
     * @param name The display name of the cue
     * @param path The path to the sound file
     * @param volume The initial volume of the cue
     * @param listIndex The index of the cue in the list
     */
    private void addCue(String name, String path, double volume, int listIndex) {
        log.debug("Will add cue to list");
        Cue cue = CueFactory.createCue(CueType.AUDIO, listIndex, name, path, volume);
        model.addCue(cue);
    }

    /**
     * Checks whether the chosen index is greater equal zero.
     * @param index The index which is checked
     * @return true if the index is greater equal zero else false
     */
    private boolean isValidIndex(int index) { return index >= 0; }

    /**
     * Plays the cue at the chosen index.
     * @param index The index of the highlighted cue in list
     */
    private void playCue(int index) {
        log.debug("Will play cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.playCue(index);
        }
    }

    /**
     * Stops the cue at the chosen index.
     * @param index The index of the highlighted cue in list
     */
    private void stopCue(int index) {
        log.debug("Will stop cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.stopCue(index);
        }
    }

    /**
     * Pauses the cue at the chosen index.
     * @param index The index of the highlighted cue in list
     */
    private void pauseCue(int index) {
        log.debug("Will pause cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.pauseCue(index);
        }
    }

    /**
     * Fades out the cue at the chosen index.
     * @param index The index of the highlighted cue in list
     */
    private void fadeOutCue(int index) {
        log.debug("Will fade out cue with index: {}", index);
        if (isValidIndex(index)) {
            this.model.fadeOutCue(index);
        }
    }
}