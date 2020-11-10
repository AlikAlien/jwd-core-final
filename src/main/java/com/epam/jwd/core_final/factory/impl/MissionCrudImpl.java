package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.impl.FlightMissionCriteriaBuilder;
import com.epam.jwd.core_final.criteria.impl.RouteCriteriaBuilder;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.FindMissionImpl;
import com.epam.jwd.core_final.service.impl.FindRouteImpl;

import java.util.*;

import static com.epam.jwd.core_final.context.impl.NassaMenu.*;

public class MissionCrudImpl implements EntityFactory {
    public static final MissionCrudImpl MISSION_FACTORY = new MissionCrudImpl();
    private MissionCrudImpl() {}
    Long id = 0L;
    //boolean isReady = true;
    private Collection <FlightMission> flightMissions = new ArrayList<>();
    public float capacityCrew = ApplicationProperties.APP_PROPERTIES.getCapacityCrew();

    public FlightMission create (AbstractBaseEntity obj) throws NullPointerException{
        Route route = null;
        if (obj instanceof Route) route = (Route) obj;
        Spaceship spaceship = null;

        //try {
            spaceship = SpaceshipCrudImpl.SPACESHIP_FACTORY.create(route);
        //}
        //catch (NoSuchElementException | NullPointerException e){
        //    System.out.println(YELLOW +"NO SHIPS AVAILABLE FOR THIS ROUTE, TRY AGAIN.."+ RST);
         //   return null;
       // }
        List <CrewMember> members = CrewMemberFactory.CREW_FACTORY.create(spaceship.getCrew());
        FlightMission flightMission = new FlightMission();
        flightMission.setId (++id);
        flightMission.setMissionsName (route.getName() +" "+ id);
        flightMission.setDistance(route.getRouteDistance());
        flightMission.setStartDateTime();
        flightMission.setEndDateTime(route.getRouteDistance());
        flightMission.setMissionResult(MissionResult.IN_PROGRESS);
        flightMission.setAssignedSpaceShift(spaceship);
        flightMission.setAssignedCrew (members);

        spaceship.setReadyForNextMissions(false);
        spaceship.addiDMission(id);
        members.stream().forEach(f->f.setReadyForNextMissions(false));
        flightMissions.add(flightMission);
        System.out.print("MISSION CREATED  ");
        return flightMission;
    }

    public void delete (FlightMission flightMission, MissionResult result ){
        flightMission.setMissionResult(result);
        flightMission.setEndDateTime();
        flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
        flightMission.getAssignedCrew().stream()
                .forEach(f->f.setReadyForNextMissions(true));
        if (result==MissionResult.FAILED)flightMission.getAssignedSpaceShift().setHasFailedMissions(true);
        System.out.println("MISSION ID#"+flightMission.getId()+" "+flightMission.getMissionsName()+" " + result.toString());
    }

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }

    public void printListAll() {
        System.out.println("-------------------------LIST OF ALL MISSIONS----------------------------------------------------- ");
        MissionCrudImpl.MISSION_FACTORY.getFlightMissions().stream()
                .forEach(x -> System.out.printf("ID#%1$-3d  MISSION: %2$-10s DISTANCE: %3$-7d STARSHIP: %4$-13s " +
                                "RANGE: %5$-7d START_DATE:%6$s  END_DATE:%7$s STATUS: %8$s\n"
                        ,x.getId(), x.getMissionsName(), x.getDistance(),
                        x.getAssignedSpaceShift().getName(), x.getAssignedSpaceShift().getFlightDistance(),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getStartDateTime()),
                        NassaContext.NASSA_CONTEXT.dateFormat.format(x.getEndDateTime()),x.getMissionResult()));
        System.out.println("-------------------------------------------------------------------------------------------------- ");
    }

    public void printDetailItem(FlightMission flightMission){
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

    public FlightMission getMission(Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId() == id)
                .findFirst().get();
        return flightMission;
    }

        public String getMissionName (Long id) {
        FlightMission flightMission = flightMissions.stream()
                .filter(f -> f.getId() == id)
                .findFirst().get();
        return flightMission.getMissionsName() +"  RANGE:"+ flightMission.getDistance() +"  RESULT:"+flightMission.getMissionResult();
    }

    public Optional checkExist (Long id) {
        Optional mission = FindMissionImpl.FIND_MISSION.findMissionByCriteria (FlightMissionCriteriaBuilder.MISSION_CRITERIA.create(id));
        return  mission;
    }
}
