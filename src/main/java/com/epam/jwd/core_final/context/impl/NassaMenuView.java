package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.*;

public class NassaMenuView extends NassaMenu {
    static void menuView() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                printListAllMissions();
                System.out.println("\nSELECT AND TYPE ID MISSION FOR DETAIL VIEW, OR \"0\" FOR MAIN MENU");
                id = scanner.nextLong();
                if (id == 0) return;
                Long finalId = id;
                try {
                    Optional.ofNullable(MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                            .filter(f -> f.getId() == finalId)
                            .findFirst().get());
                    FlightMission flightMission = printDetailMissions(finalId);

                    NassaMenuViewUpdate.NassaMenuViewCancel(flightMission, finalId);  //MENU FOR UPDATE

                } catch (NoSuchElementException e) {
                    LoggerImpl.LOGGER.info(RED + "BAD ID MISSION, TRY AGAIN.." + RST);
                }
            } catch (
                    InputMismatchException e) {
                LoggerImpl.LOGGER.error(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }

    static void printListAllMissions() {
        System.out.println("-------------------------LIST OF ALL MISSIONS--------------------------------- ");
        MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .forEach(x -> System.out.println("ID:" + x.getId() + "  MISSION:" + x.getMissionsName() + "  DISTANCE:" + x.getDistance() +
                        "  STARSHIP:" + x.getAssignedSpaceShift().getName() + "  RANGE:" + x.getAssignedSpaceShift().getFlightDistance() +
                        "  START_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(x.getStartDateTime() ) +
                        "  END_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(x.getEndDateTime()) +
                        "  STATUS:" + x.getMissionResult()));
        System.out.println("------------------------------------------------------------------------------ ");
    }

    static FlightMission printDetailMissions(Long finalId) {
        FlightMission fligh = MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .filter(f -> f.getId() == finalId)
                .findFirst().get();

        System.out.println("ID:" + fligh.getId() + "  MISSION:" + fligh.getMissionsName() + "  DISTANCE:" + fligh.getDistance() +
                "  STARSHIP:" + fligh.getAssignedSpaceShift().getName() + "  RANGE:" + fligh.getAssignedSpaceShift().getFlightDistance() +
                "  START_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(fligh.getStartDateTime() ) +
                "  END_DATE:" + NassaContext.NASSA_CONTEXT.dateFormat.format(fligh.getEndDateTime()) +
                "  STATUS:" + fligh.getMissionResult());

        fligh.getAssignedCrew().stream()
                .forEach(f -> System.out.print(f.getRole().getName() + "  " + f.getName()));
        System.out.println();
    return fligh;
    }
}