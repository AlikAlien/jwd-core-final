package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static com.epam.jwd.core_final.context.impl.NassaMenu.*;

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
        //SELECT CREW
        Map <Role, Short> roleMap = spaceship.getCrew();
        List <CrewMember> members = new ArrayList<>();
        for (Role role: roleMap.keySet()){
            try {
                members.addAll(FindCrewImpl.FIND_CREW.findCrew(CrewMemberFactory.CREW_FACTORY.create(role, roleMap.get(role))));
            }
            catch (NullPointerException e){
                System.out.println(YELLOW+"WARNING: NOT ENOUGH CREW " +role.getName()+ "S FOR MISSION!"+RST);
            }
        }
        flightMission.setAssignedSpaceShift(spaceship);
        flightMission.setAssignedCrew (members);
        spaceship.setReadyForNextMissions(false);
        spaceship.addiDMission(id);
        members.stream().forEach(f->f.setReadyForNextMissions(false));
        flightMissions.add(flightMission);
        System.out.print("MISSION CREATED");
        printDetailMission(flightMission);
    }

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }

    public void printDetailMission(FlightMission flightMission){
        System.out.print("MISSION ID#"+ id +"  NAME:" +flightMission.getMissionsName()+ "  DISTANCE:"+ flightMission.getDistance() );
        System.out.print("  STARSHIP:"+flightMission.getAssignedSpaceShift().getName()+
                "  START_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getStartDateTime() ) +
                "  END_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getEndDateTime()) +
                "  RANGE: "+flightMission.getAssignedSpaceShift().getFlightDistance()+"  STATUS:"+ flightMission.getMissionResult());

        Map <Role, Short> roleMap = flightMission.getAssignedSpaceShift().getCrew();
        for (Role role: roleMap.keySet()){
            System.out.printf("\n%1$-3d %2$10s (s) ",roleMap.get(role),role.getName());
            flightMission.getAssignedCrew().stream()
                    .filter(f->f.getRole().equals(role))
                    .forEach(f-> System.out.printf(" %18s |",f.getName()));
        }
    }

    public String getMissionName(Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId() == id)
                .findFirst().get();
        return flightMission.getMissionsName() +"  RANGE:"+ flightMission.getDistance() +"  RESULT:"+flightMission.getMissionResult();
    }
}
