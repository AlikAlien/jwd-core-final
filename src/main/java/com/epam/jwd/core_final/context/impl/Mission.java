package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import java.util.ArrayList;
import java.util.Collection;

public class Mission {
    public static final Mission MISSION = new Mission();
    private Mission() {}
    Long id = 0L;
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    public void createMission(Long dist){
        FlightMission flightMission = new FlightMission();
        flightMission.setId(++id);
        flightMissions.add(flightMission);
        System.out.println("CREATED MISSION ....... " + id);
    }
    public void cancelMission(){}

    public void saveMission(){}

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }
}
