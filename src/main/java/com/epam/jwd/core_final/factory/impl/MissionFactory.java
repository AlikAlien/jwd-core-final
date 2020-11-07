package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MissionFactory {
    public static final MissionFactory MISSION_FACTORY = new MissionFactory();
    private MissionFactory() {}

    Long id = 0L;
    boolean isReady = true;
    private Collection <FlightMission> flightMissions = new ArrayList<>();
    public float capacityCrew = ApplicationProperties.APP_PROPERTIES.getCapacityCrew();

    public void createMission(Long dist, String star){
        FlightMission flightMission = new FlightMission();
        flightMission.setId(++id);
        flightMission.setMissionsName (star +" "+ id);
        flightMission.setDistance(dist);
        flightMission.setStartDateTime();
        flightMission.setEndDateTime(dist);
        flightMission.setMissionResult(MissionResult.IN_PROGRESS);
        //SELECT STARSHIP
        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findStarship (SpaceshipFactory.SPACESHIP_FACTORY.create(dist,isReady));

        spaceship.addiDMission(id);
        flightMission.setAssignedSpaceShift(spaceship);
        //SELECT CREW
        Map <Role, Short> roleMap = spaceship.getCrew();
        List <CrewMember> members = new ArrayList<>();
        for (Role role: roleMap.keySet()){

            members.addAll(
                    FindCrewImpl.FIND_CREW.findCrew(CrewMemberFactory.CREW_FACTORY.create(role, roleMap.get(role))));
        }

        flightMission.setAssignedCrew (members);
        spaceship.setReadyForNextMissions(false);
        members.stream().forEach(f->f.setReadyForNextMissions(false));
        flightMissions.add(flightMission);
        printCreatedMissions(flightMission, star, dist, roleMap);
    }

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }

    void printCreatedMissions(FlightMission flightMission, String star, Long dist, Map <Role, Short> roleMap){
        System.out.print("CREATED MISSION ID:"+ id +"  DESTINATION:" +star+ "  DISTANCE:"+ dist );
        System.out.print("  STARSHIP:"+flightMission.getAssignedSpaceShift().getName()+
                "  START_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getStartDateTime() ) +
                "  END_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getEndDateTime()) +
                "  RANGE: "+flightMission.getAssignedSpaceShift().getFlightDistance()+"  STATUS:"+ flightMission.getMissionResult());

        for (Role role: roleMap.keySet()){
            System.out.print("\n"+roleMap.get(role)+" " + role.getName()+"(s): ");
            flightMission.getAssignedCrew().stream()
                    .filter(f->f.getRole().equals(role))
                    .forEach(f-> System.out.print(f.getName()+"   "));
        }
    }
    public String getMissionName(Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId() == id)
                .findFirst().get();
        return flightMission.getMissionsName() +"  RANGE:"+ flightMission.getDistance() +"  RESULT:"+flightMission.getMissionResult();
    }
}
