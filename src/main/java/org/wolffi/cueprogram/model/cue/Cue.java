package org.wolffi.cueprogram.model.cue;

public interface Cue {
    default void play() {}
    default void stop() {}
    default void pause() {}
    default void fadeIn() {}
    default void fadeOut() {}

    void setName(String name);

    String getName();
}
