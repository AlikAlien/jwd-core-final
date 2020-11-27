package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import static com.epam.jwd.core_final.context.impl.NassaMenu.*;


import static com.epam.jwd.core_final.util.ApplicationProperties.APP_PROPERTIES;

public class UpdateTaskCompleted {
    public static final UpdateTaskCompleted UPDATER = new UpdateTaskCompleted();

    private UpdateTaskCompleted() {
    }

    public Timer timer;

    public void updMission() {
        TimerTask updater = new TimerTask() {
            public void run() {
                MissionCrudImpl.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f -> f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .filter(f -> f.getEndDateTime().isBefore(LocalDateTime.now()))
                        .forEach(f -> {
                            f.setMissionResult(MissionResult.COMPLETED);
                            MissionCrudImpl.MISSION_FACTORY.delete(f, MissionResult.COMPLETED);
                            System.out.println(GREEN + "MISSION ID#" + f.getId() + " " + f.getMissionsName() + " " + MissionResult.COMPLETED + " AUTOMATICALLY" + RST);
                            LoggerImpl.LOGGER.info("MISSION ID#" + f.getId() + " " + f.getMissionsName() + " AUTO " + MissionResult.COMPLETED);
                        });
            }
        };
        timer = new Timer("UPDATE_MISSIONS");
        Long period = APP_PROPERTIES.getMissionsRefresh();
        timer.scheduleAtFixedRate(updater, 10000, period);
        LoggerImpl.LOGGER.info("START AUTO UPDATE TASK..");
    }

    public void cancelUpd() {
        LoggerImpl.LOGGER.info("STOPPED UPDATE TASK COMPLETION TASK..");
        timer.cancel();
    }
}
