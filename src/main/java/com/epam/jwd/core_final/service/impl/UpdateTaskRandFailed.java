package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.epam.jwd.core_final.context.impl.NassaMenu.*;

import static com.epam.jwd.core_final.util.ApplicationProperties.APP_PROPERTIES;

public class UpdateTaskRandFailed {
    public static final UpdateTaskRandFailed UPDATER = new UpdateTaskRandFailed();

    private UpdateTaskRandFailed() {
    }

    public Timer timer;

    public void updMission() {
        TimerTask updater = new TimerTask() {
            public void run() {
                List<FlightMission> flightMissionsActive = MissionCrudImpl.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f -> f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .collect(Collectors.toList());
                Random rand = new Random();
                if (flightMissionsActive.size() == 0) {
                    LoggerImpl.LOGGER.info("RANDOM FAIL MESSAGES: NO ACTIVE MISSION");
                    return;
                }
                FlightMission f = flightMissionsActive.get(rand.nextInt(flightMissionsActive.size()));
                MissionCrudImpl.MISSION_FACTORY.delete(f, MissionResult.FAILED);
                System.out.println(RED + "MISSION ID#" + f.getId() + " " + f.getMissionsName() + " " + MissionResult.FAILED + " BY RANDOM CHOICE" + RST);
                LoggerImpl.LOGGER.info("MISSION ID#" + f.getId() + " " + f.getMissionsName() + " " + MissionResult.FAILED);
            }
        };

        Long period = APP_PROPERTIES.getMissionsRndFailRate(); //PERIOD FOR RND FAILED in 20
        timer = new Timer("UPDATE_MISSIONS_FAIL");
        timer.scheduleAtFixedRate(updater, 60000, period);
        LoggerImpl.LOGGER.info("START RND FAIL TASK..");

    }

    public void cancelUpd() {
        LoggerImpl.LOGGER.info("STOPPED RANDOM FAIL TASK..");
        timer.cancel();
    }
}
