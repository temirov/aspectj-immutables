package net.temirov.pureservice;

import net.temirov.aspectj.LogExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HiSayer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HiSayer.class);

    private final String phrase;

    HiSayer(String phrase) {
        this.phrase = phrase;
    }

    @LogExecutionTime
    void speak() {
        LOGGER.info(phrase);
    }
}
