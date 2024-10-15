package org.wolffi.cueprogram.model.cue;

public interface Cue {
    void setStart(double startTime);
    void setStop(double endTime);
    String getName();
}
