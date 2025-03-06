package dev.coldservices.processor.prechecks;

import lombok.Getter;
import dev.coldservices.processor.prechecks.impl.LargeMoveCheck;
import dev.coldservices.processor.prechecks.impl.LargeRotationCheck;

@Getter
public enum PreProcessorManager {

    LARGE_MOVE(new LargeMoveCheck()),
    LARGE_ROTATION(new LargeRotationCheck());

    private final PreProcessorCheck prevention;

    PreProcessorManager(final PreProcessorCheck prevention) {
        this.prevention = prevention;
    }
}
