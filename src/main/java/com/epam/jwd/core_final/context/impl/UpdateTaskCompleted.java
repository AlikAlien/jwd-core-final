package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import static com.epam.jwd.core_final.context.impl.NassaMenu.*;


import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class UpdateTaskCompleted {
    public static final UpdateTaskCompleted UPDATER = new UpdateTaskCompleted();
    private UpdateTaskCompleted() {}
    TimerTask updater;
    public Timer timer;
    public void updMission() {
        updater = new TimerTask() {
            public void run() {
                MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                        .filter(f->f.getMissionResult().equals(MissionResult.IN_PROGRESS))
                        .filter(f-> f.getEndDateTime().isBefore(LocalDateTime.now()))
                        .forEach(f->{f.setMissionResult(MissionResult.COMPLETED);
                                        NassaMenuMissionUpdate.MissionUdpFreeResources(f,MissionResult.COMPLETED);
                                        System.out.println(GREEN+"INFO MESSAGE: MISSION ID#"+f.getId()+" "+ f.getMissionsName()+" AUTO "+MissionResult.COMPLETED+RST);
                                        LoggerImpl.LOGGER.info("MISSION ID#"+f.getId()+" "+ f.getMissionsName()+" "+MissionResult.COMPLETED);
                        });
                }
        };
        try {
            Long period = Long.valueOf(APP_PROPERTIES.getMissionsRefreshRate());
            timer = new Timer("UPDATE_MISSIONS_RND_FAIL");
            timer.scheduleAtFixedRate(updater,10000, period);
            LoggerImpl.LOGGER.info("START UPDATE TASK COMPLETION..");
        } catch (NumberFormatException e) {
            LoggerImpl.LOGGER.error("WRONG PARAMETER!");
        }
    }
    public void cancelUpd (){
        LoggerImpl.LOGGER.info("STOPPED UPDATE FAIL TASK..");
        timer.cancel();
    }
}
