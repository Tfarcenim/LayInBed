package tfar.layinbed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class LayInBed {

    public static final String MOD_ID = "layinbed";
    public static final String MOD_NAME = "LayInBed";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static void init() {

    }
}
//I'd love to be able to just have my character go lay in a bed while AFK...
// even better if the sleep command lets you skip to sunup and sundown so you can sleep through the day too...