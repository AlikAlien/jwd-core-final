package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class UpdateTask {
    public static final UpdateTask UPDATER = new UpdateTask();
    private UpdateTask() {}
    TimerTask updater;
    public Timer timer;
    public void updMission() {
        updater = new TimerTask() {
            public void run() {
                MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f->f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .filter(f-> f.getEndDateTime().isBefore(LocalDateTime.now()))
                        .forEach(f->{f.setMissionResult(MissionResult.COMPLETED);
                                        NassaMenuMissionUpdate.freeResources(f,MissionResult.COMPLETED);
                                        System.out.println("MISSION ID#"+f.getId()+" "+MissionResult.COMPLETED);
                                        LoggerImpl.LOGGER.info("MISSION ID#"+f.getId()+" "+MissionResult.COMPLETED);
                        });
                List<FlightMission> flightMissionsActive =  MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f->f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .collect(Collectors.toList());
                Random rand = new Random();
                FlightMission f = flightMissionsActive.get(rand.nextInt(flightMissionsActive.size()));
                NassaMenuMissionUpdate.freeResources(f,MissionResult.FAILED);
                System.out.println("MISSION ID#"+f.getId()+" "+MissionResult.FAILED);
                LoggerImpl.LOGGER.info("MISSION ID#"+f.getId()+" "+MissionResult.FAILED);
                }
        };
        try {
            Long period = Long.valueOf(APP_PROPERTIES.getMissionsRefreshRate());
            timer = new Timer("UPDATE");
            timer.scheduleAtFixedRate(updater,10000, period);
            LoggerImpl.LOGGER.info("START UPDATE TASK..");
        } catch (NumberFormatException e) {
            LoggerImpl.LOGGER.error("WRONG PARAMETER!");
        }
    }
    public void cancelUpd (){
        LoggerImpl.LOGGER.info("STOPPED UPDATE TASK..");
        timer.cancel();
    }
}
