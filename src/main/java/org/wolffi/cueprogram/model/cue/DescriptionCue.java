package org.wolffi.cueprogram.model.cue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DescriptionCue implements Cue {

    private static final Logger log = LogManager.getLogger();

    private String name;

    public DescriptionCue(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        log.debug("Change description cue name from {} to {}", this.name, name);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
