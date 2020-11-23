package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.EntityType;

import java.util.*;

public class MissionCrudImpl implements EntityFactory {
    public static final MissionCrudImpl MISSION_FACTORY = new MissionCrudImpl();

    MissionCrudImpl() {
    }
    final static String DELIMITER       = "--------------------------------------------------------------------------------------------------------------";
    final static String FIELDS          = "ID#  MISSION        DISTANCE   STARSHIP       RANGE        START_DATE             END_DATE            STATUS";
    final static String DETAIL_MISSION  = "%1$-3d  %2$-14s %3$-8d  %4$-13s  %5$-7d   %6$s   %7$s   %8$s";
    final static String NAME_MISSION    = "ID#%1$-3d %2$-10s DISTANCE:%3$-7d STATUS:%4$-10s";
    Long id = 0L;
    private final Collection<FlightMission> flightMissions = new ArrayList<>();
    public float capacityCrew = ApplicationProperties.APP_PROPERTIES.getCapacityCrew();

    public FlightMission create(AbstractBaseEntity obj) throws NullPointerException {
        Route route = null;
        if (obj instanceof Route) route = (Route) obj;
        Spaceship spaceship = (Spaceship) EntityFactoryImpl.FACTORY.create(route, EntityType.SPACESHIP);
        List<CrewMember> members = CrewMemberCrudImpl.CREW_FACTORY.create(spaceship);

        FlightMission flightMission = new FlightMission();
        flightMission.setId(++id);
        flightMission.setMissionsName(route.getName() + " " + id);
        flightMission.setDistance(route.getRouteDistance());
        flightMission.setStartDateTime();
        flightMission.setEndDateTime(route.getRouteDistance());
        flightMission.setMissionResult(MissionResult.IN_PROGRESS);
        flightMission.setAssignedSpaceShift(spaceship);
        flightMission.setAssignedCrew(members);

        spaceship.setReadyForNextMissions(false);
        spaceship.addiDMission(id);
        members.forEach(f -> f.setReadyForNextMissions(false));
        members.forEach(f -> f.addiDMission(id));
        flightMissions.add(flightMission);
        System.out.print("MISSION CREATED\n");
        return flightMission;
    }

    public void delete(FlightMission flightMission, MissionResult result) {
        flightMission.setMissionResult(result);
        flightMission.setEndDateTime();
        flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
        flightMission.getAssignedCrew()
                .forEach(f -> f.setReadyForNextMissions(true));
        if (result == MissionResult.FAILED) flightMission.getAssignedSpaceShift().setHasFailedMissions(true);
        System.out.println("MISSION ID#" + flightMission.getId() + " " + flightMission.getMissionsName() + " " + result.toString());
    }

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }

    public void printListAll() {
        System.out.println(DELIMITER+"\n"+FIELDS+"\n"+DELIMITER);
        MissionCrudImpl.MISSION_FACTORY.getFlightMissions()
                .forEach(x ->
                        System.out.printf(DETAIL_MISSION+"\n"
                        , x.getId(), x.getMissionsName(), x.getDistance(),
                        x.getAssignedSpaceShift().getName(), x.getAssignedSpaceShift().getFlightDistance(),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getStartDateTime()),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getEndDateTime()), x.getMissionResult()));
        System.out.println(DELIMITER);
    }

    public void printDetailItem(AbstractBaseEntity obj) {
        FlightMission flight = null;
        if (obj instanceof FlightMission) flight = (FlightMission) obj;
        assert flight != null;
        System.out.println(DELIMITER+"\n"+FIELDS+"\n"+DELIMITER);
        System.out.printf(DETAIL_MISSION
                ,flight.getId(), flight.getMissionsName(), flight.getDistance(),
                flight.getAssignedSpaceShift().getName(), flight.getAssignedSpaceShift().getFlightDistance(),
                NassaContext.NASSA_CONTEXT.dateFormat.format(flight.getStartDateTime()),
                NassaContext.NASSA_CONTEXT.dateFormat.format(flight.getEndDateTime()), flight.getMissionResult());
        System.out.print("\n"+DELIMITER);
        Map<Role, Short> roleMap = flight.getAssignedSpaceShift().getCrew();
        for (Role role : roleMap.keySet()) {
            System.out.printf("\n%1$-3d %2$10s (s):", (int) Math.ceil(roleMap.get(role)*MissionCrudImpl.MISSION_FACTORY.capacityCrew), role.getName());
            flight.getAssignedCrew().stream()
                    .filter(f -> f.getRole().equals(role))
                    .forEach(f -> System.out.printf(" %18s", f.getName()));
        }
    }

    public FlightMission getMission(Long id) {
        return flightMissions.stream()
                .filter(f -> Objects.equals(f.getId(), id))
                .findFirst().get();
    }

    public String getPrintedMissionName(Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst().get();
        return String.format(NAME_MISSION, id, flightMission.getMissionsName(),flightMission.getDistance(),flightMission.getMissionResult());
    }
}
