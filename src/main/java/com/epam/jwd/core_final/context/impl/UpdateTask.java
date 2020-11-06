package com.epam.jwd.core_final.context.impl;

import java.util.Timer;
import java.util.TimerTask;
import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class UpdateTask {
    public static final UpdateTask UPDATER = new UpdateTask();
    private UpdateTask() {}
    TimerTask updater;
    public Timer timer;
    public void upd() {
        updater = new TimerTask() {
            public void run() {
                int status = 0;
                //System.out.print("Changing Data ... before change age is " + status + " ");
                status = (int) Math.round(Math.random() * 1000);
                //System.out.println("after change age is " + status);
            }
        };
        try {
            Long period = Long.valueOf(APP_PROPERTIES.getMissionsRefreshRate());
        } catch (NumberFormatException e) {
            System.err.println("WRONG PARAMETER!");
        }

        Long period = 2000L;
        timer = new Timer("UPDATE");
        timer.scheduleAtFixedRate(updater,2000, period);
    }
    public void cancelUpd (){
        timer.cancel();
    }
}
