package org.wolffi.cueprogram.model.cue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.enums.CueType;


public final class CueFactory {

    private static final Logger log = LogManager.getLogger();

    private CueFactory() {};

    public static Cue createCue(CueType type, Object ...params) {
        return switch (type) {
            case AUDIO -> {
                log.debug("With following parameters: name={}, path={}, volume={}", params[0], params[1], params[2]);
                if (StringUtils.isEmpty((String) params[0])) {
                    log.debug("Using path as name because no name is provided");
                    params[0] = params[1];
                }
                yield new AudioCue((String) params[0], (String) params[1], (double) params[2]);
            }
            case DESCRIPTION -> {
                log.debug("With following parameters: name={}", params[0]);
                yield new DescriptionCue((String) params[0]);
            }
            case CONTROL, TIME -> null;
        };
    }
}
