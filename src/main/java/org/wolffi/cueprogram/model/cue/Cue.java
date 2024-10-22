package org.wolffi.cueprogram.model.cue;


/**
 * Generalizes cue behavior.
 * @author Wolffi
 */
public interface Cue {

    /**
     * Plays the cue.
     */
    default void play() {}

    /**
     * Stops the cue-
     */
    default void stop() {}

    /**
     * Pauses the cue.
     */
    default void pause() {}

    /**
     * Fades a cue in.
     */
    default void fadeIn() {}

    /**
     * Fades a cue out.
     */
    default void fadeOut() {}


    void setName(String name);

    String getName();
}
