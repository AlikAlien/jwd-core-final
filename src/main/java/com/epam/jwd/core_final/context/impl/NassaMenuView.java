package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionFactory;

import java.util.*;

public class NassaMenuView extends NassaMenu{
    static void nenuView() {
        System.out.println("---------------LIST OF ALL MISSIONS--------------------------------- ");
        MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .forEach (x -> System.out.println("ID:"+x.getId()+" MISSION:"+x.getMissionsName()+" DISTANCE:"+x.getDistance()+
                        " STARSHIP:"+x.getAssignedSpaceShift().getName()+ " RANGE:"+x.getAssignedSpaceShift().getFlightDistance()+
                        " START DATE:"+ x.getStartDate()+" STATUS:"+ x.getMissionResult()));
        System.out.println("-------------------------------------------------------------------- ");

        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                System.out.println("\nSELECT AND TYPE ID MISSION FOR DETAIL VIEW, OR PRESS \"0\" FOR MAIN MENU");
                id = scanner.nextLong();
                if (id == 0) return;
                Long finalId = id;
                try { Optional.ofNullable(MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                            .filter(f -> f.getId() == finalId)
                            .findFirst().get());
                    FlightMission flightMission = MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                            .filter(f -> f.getId() == finalId)
                            .findFirst().get();
                    System.out.println("SPACESHIP "+flightMission.getAssignedSpaceShift().getName()+" RANGE "+ flightMission.getAssignedSpaceShift().getFlightDistance());
                    flightMission.getAssignedCrew().stream()
                            .forEach(f -> System.out.println(f.getRole()+" "+f.getName()));
                }
                catch (NoSuchElementException e){
                    System.out.println(STR_RED+"BAD ID MISSION, TRY AGAIN.."+STR_RESET);
                }
            } catch (
                InputMismatchException e) {
                System.out.println(STR_RED+"BAD INPUT, TRY AGAIN.."+STR_RESET);
                scanner.next();
            }
        }
    }
}
