package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.FlightMissionCriteriaBuilder;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.EntityType;
import com.epam.jwd.core_final.service.impl.FindMissionImpl;

import java.util.*;

public class MissionCrudImpl implements EntityFactory {
    public static final MissionCrudImpl MISSION_FACTORY = new MissionCrudImpl();

    MissionCrudImpl() {
    }
    final static String START_DELIMITER = "--------------------------------------- MISSIONS -------------------------------------------------------------------------------- ";
    final static String END_DELIMITER   = "--------------------------------------------------------------------------------------------------------------------------------- ";

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
        System.out.print("MISSION CREATED  ");
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
        System.out.println(START_DELIMITER);
        MissionCrudImpl.MISSION_FACTORY.getFlightMissions()
                .forEach(x -> System.out.printf("ID#%1$-3d  MISSION: %2$-10s DISTANCE: %3$-7d STARSHIP: %4$-13s " +
                                "RANGE: %5$-7d START_DATE:%6$s  END_DATE:%7$s STATUS: %8$s\n"
                        , x.getId(), x.getMissionsName(), x.getDistance(),
                        x.getAssignedSpaceShift().getName(), x.getAssignedSpaceShift().getFlightDistance(),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getStartDateTime()),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getEndDateTime()), x.getMissionResult()));
        System.out.println(END_DELIMITER);
    }

    public void printDetailItem(AbstractBaseEntity obj) {
        FlightMission flightMission = null;
        if (obj instanceof FlightMission) flightMission = (FlightMission) obj;
        assert flightMission != null;
        System.out.print("MISSION ID#" + id + "  NAME:" + flightMission.getMissionsName() + "  DISTANCE:" + flightMission.getDistance());
        System.out.print("  STARSHIP:" + flightMission.getAssignedSpaceShift().getName() +
                "  START_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getStartDateTime()) +
                "  END_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(flightMission.getEndDateTime()) +
                "  RANGE: " + flightMission.getAssignedSpaceShift().getFlightDistance() + "  STATUS:" + flightMission.getMissionResult());

        Map<Role, Short> roleMap = flightMission.getAssignedSpaceShift().getCrew();
        for (Role role : roleMap.keySet()) {
            System.out.printf("\n%1$-3d %2$10s (s) ", roleMap.get(role), role.getName());
            flightMission.getAssignedCrew().stream()
                    .filter(f -> f.getRole().equals(role))
                    .forEach(f -> System.out.printf(" %18s |", f.getName()));
        }
    }

    public FlightMission getMission(Long id) {
        return flightMissions.stream()
                .filter(f -> Objects.equals(f.getId(), id))
                .findFirst().get();
    }

    public String getMissionName(Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId() == id)
                .findFirst().get();
        return flightMission.getMissionsName() + "  RANGE:" + flightMission.getDistance() + "  RESULT:" + flightMission.getMissionResult();
    }

    public Optional checkExist(Long id) {
        Optional mission = FindMissionImpl.FIND_MISSION.findMissionByCriteria(FlightMissionCriteriaBuilder.MISSION_CRITERIA.create(id));
        return mission;
    }
}
