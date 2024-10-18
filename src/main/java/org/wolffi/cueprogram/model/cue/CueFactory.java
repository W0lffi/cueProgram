package org.wolffi.cueprogram.model.cue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.enums.CueType;


public final class CueFactory {

    private static final Logger log = LogManager.getLogger();

    /**
     * Factory classes, which are collections of static members, are not meant to be instantiated.
     * Hence, at least one non-public constructor should be defined, to restrict this.
     */
    private CueFactory() { throw new IllegalStateException("Cannot instantiate a factory class"); };

    public static Cue createCue(CueType type, Object ...params) {
        return switch (type) {
            case AUDIO -> {
                log.debug("With following parameters: name={}, path={}, volume={}", params[1], params[2], params[3]);
                if (StringUtils.isEmpty((String) params[1])) {
                    log.debug("Using path as name because no name is provided");
                    params[1] = params[2];
                }
                yield new AudioCue((int) params[0], (String) params[1], (String) params[2], (double) params[3]);
            }
            case DESCRIPTION -> {
                log.debug("With following parameters: name={}", params[0]);
                yield new DescriptionCue((String) params[0]);
            }
            case CONTROL, TIME -> null;
        };
    }
}
