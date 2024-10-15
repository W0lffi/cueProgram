package org.wolffi.cueprogram.model.cue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DescriptionCue implements Cue{

    private static final Logger log = LogManager.getLogger();
    private final String name;

    public DescriptionCue(String name) {
        this.name = name;
    }

    @Override
    public void setStart(double startTime) {

    }

    @Override
    public void setStop(double endTime) {

    }

    @Override
    public String getName() {
        return "";
    }
}
