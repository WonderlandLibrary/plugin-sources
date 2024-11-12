package dev.coldservices.alert.alert;

import dev.coldservices.alert.alert.impl.ThreadedAlertHandler;
import dev.coldservices.util.Factory;

public class AlertHandlerFactory implements Factory<AlertHandler> {

    @Override
    public AlertHandler build() {
        return new ThreadedAlertHandler();
    }
}
