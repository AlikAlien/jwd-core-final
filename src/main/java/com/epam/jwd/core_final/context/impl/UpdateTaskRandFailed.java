package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import static com.epam.jwd.core_final.context.impl.NassaMenu.*;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class UpdateTaskRandFailed {
    public static final UpdateTaskRandFailed UPDATER = new UpdateTaskRandFailed();
    private UpdateTaskRandFailed() {}
    TimerTask updater;
    public Timer timer;
    public void updMission() {
        updater = new TimerTask() {
            public void run() {
                List<FlightMission> flightMissionsActive =  MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f->f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .collect(Collectors.toList());
                Random rand = new Random();
                if(flightMissionsActive.size()==0) {
                    LoggerImpl.LOGGER.info("RND FAIL: NO ACTIVE MISSION");
                    return;}
                FlightMission f = flightMissionsActive.get(rand.nextInt(flightMissionsActive.size()));
                NassaMenuMissionUpdate.MissionUdpFreeResources(f,MissionResult.FAILED);
                System.out.println(RED+"INFO MESSAGE: MISSION ID#"+f.getId()+" "+ f.getMissionsName()+" RND "+MissionResult.FAILED+RST);
                LoggerImpl.LOGGER.info("MISSION ID#"+f.getId()+" "+ f.getMissionsName()+" "+MissionResult.FAILED);
            }
        };
        try {
            Long period = Long.valueOf(APP_PROPERTIES.getMissionsRefreshRate())*50; //PERIOD FOR RND FILED
            timer = new Timer("UPDATE_MISSIONS_FILED");
            timer.scheduleAtFixedRate(updater,60000, period);
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
