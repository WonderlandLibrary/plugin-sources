package com.elevatemc.anticheat.util.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Configuration {
    private boolean databaseEnabled = true;

    private String databaseHost = "127.0.0.1";

    private String databaseName = "admin";

    private String databaseLogsName = "HoodViolations";
    private String databaseBanwaveName = "HoodBanwave";

    private boolean performanceMode = false;

    boolean autoBans = true;

    private String primaryColor = "&c";

    private String secondaryColor = "&e";

    private int port = 27017;

}