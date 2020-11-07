package com.epam.jwd.core_final.util;

import java.util.Timer;
import java.util.TimerTask;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class CheckFileUpdate {
    public static final CheckFileUpdate CHECK_UPDATE = new CheckFileUpdate();
    private CheckFileUpdate() {}
    TimerTask updater;
    public Timer timer;
    public void checkFiles() {
        updater = new TimerTask() {
            public void run() {
                int status;
                status = (int) Math.round(Math.random() * 1000);
            }
        };
        try {
            Long period = (long) APP_PROPERTIES.getFileRefreshRate()*1000;
            timer = new Timer("CHECK_FILE");
            timer.scheduleAtFixedRate(updater,10000, period);
        } catch (NumberFormatException e) {
            LoggerImpl.LOGGER.error("WRONG PARAMETER!");
        }
    }
    public void cancelCheckUpd (){
        timer.cancel();
    }
}
