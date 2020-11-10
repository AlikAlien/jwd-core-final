package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.util.LoggerImpl;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;
import static com.epam.jwd.core_final.context.impl.NassaContext.NASSA_CONTEXT;

public class UpdaterRefreshInput {
    public static final UpdaterRefreshInput UPDATER = new UpdaterRefreshInput();
    private UpdaterRefreshInput() {}
    Timer timer;
    File fileCrew = new File(APP_PROPERTIES.getInputRootDir() + APP_PROPERTIES.getCrewFileName());
    File fileSpaceship = new File(APP_PROPERTIES.getInputRootDir()+ APP_PROPERTIES.getSpaceshipsFileName() );
    File fileRoute = new File(APP_PROPERTIES.getInputRootDir() +APP_PROPERTIES.getRouteFileName() );


    Long timeFileCrew = fileCrew.lastModified();
    Long timeFileSpaceship = fileSpaceship.lastModified();
    Long timeFileRoute = fileRoute.lastModified();

    public void updInput(){
            TimerTask updater = new TimerTask() {
            public void run() {
                String msg = "AUTO CHECKED INPUT FILES RUN";
                LoggerImpl.LOGGER.info(msg);
                if (timeFileCrew != fileCrew.lastModified()) {
                    timeFileCrew = fileCrew.lastModified();
                    msg = "INFO MESSAGE: FILE CREW WAS CHANGED IN "+ NASSA_CONTEXT.dateFormat.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeFileCrew), ZoneId.systemDefault()));
                    System.out.println(msg);
                    LoggerImpl.LOGGER.info(msg);
                }
                if (timeFileSpaceship != fileSpaceship.lastModified()) {
                    timeFileSpaceship = fileSpaceship.lastModified();
                    msg = "INFO MESSAGE: FILE SPACESHIP WAS CHANGED IN "+ NASSA_CONTEXT.dateFormat.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeFileSpaceship), ZoneId.systemDefault()));
                    System.out.println(msg);
                    LoggerImpl.LOGGER.info(msg);
                }
                if (timeFileRoute != fileRoute.lastModified()) {
                    timeFileRoute = fileRoute.lastModified();
                    msg = "INFO MESSAGE: FILE ROUTE WAS CHANGED IN "+ NASSA_CONTEXT.dateFormat.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeFileRoute), ZoneId.systemDefault()));
                    System.out.println(msg);
                    LoggerImpl.LOGGER.info(msg);
                }
                LoggerImpl.LOGGER.info("AUTO CHECKED INPUT FILES DONE");
            }
        };

        timer = new Timer("REFRESH_TASK");
        Long period = Long.valueOf(APP_PROPERTIES.getFileRefreshRate());
        timer.scheduleAtFixedRate(updater,10000, period);
        LoggerImpl.LOGGER.info("START REFRESH TASK COMPLETION..");
    }

    public void cancelUpd (){
        LoggerImpl.LOGGER.info("STOPPED REFRESH TASK..");
        timer.cancel();
    }
}