package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MissionFactory {
    public static final MissionFactory MISSION_FACTORY = new MissionFactory();
    private MissionFactory() {}
    Long id = 0L;
    boolean isReady = true;
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    public void createMission(Long dist, String star){
        FlightMission flightMission = new FlightMission();
        flightMission.setId(++id);
        flightMission.setMissionsName (star +" "+ id);
        flightMission.setDistance(dist);
        flightMission.setStartDate();
        flightMission.setMissionResult(MissionResult.IN_PROGRESS);
        //select starship
        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findStarship (SpaceshipFactory.SPACESHIP_FACTORY.create(dist,isReady));
        spaceship.setReadyForNextMissions(false);
        flightMission.setAssignedSpaceShift(spaceship);
        //select crew
        Map <Role, Short> roleMap = spaceship.getCrew();
        List <CrewMember> members = null;
        for (Role role: roleMap.keySet()){
            members = FindCrewImpl.FIND_CREW.findCrew(CrewMemberFactory.CREW_FACTORY.create(role, roleMap.get(role)));
        }
        flightMission.setAssignedCrew (members);
        flightMissions.add(flightMission);
        System.out.print("CREATED MISSION ID: "+ id +" DESTINATION: " +star+ " DISTANCE: "+ dist );
        System.out.print(" START DATE: "+ flightMission.getStartDate() +" STARSHIP: "+flightMission.getAssignedSpaceShift().getName()+" Range: "+flightMission.getAssignedSpaceShift().getFlightDistance()+" STATUS: "+ flightMission.getMissionResult()+"\nCREW: ");
        for (Role role: roleMap.keySet()){System.out.print(role+":" +roleMap.get(role)+" ");}
        System.out.println();
    }

    public void cancelMission(){}

    public void saveMission(){}

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }
}
