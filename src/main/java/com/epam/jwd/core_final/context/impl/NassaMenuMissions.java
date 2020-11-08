package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.*;

public class NassaMenuMissions extends NassaMenu {
    static void menuView() {
        Long id = -1L;

        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                printListAllMissions();
                System.out.println("\nSELECT AND TYPE ID MISSION FOR DETAIL VIEW, ("+RED+"S"+RST+")AVE FOR SAVE ALL MISSIONS, OR "+RED+"0"+RST+" FOR RETURN TO MAIN MENU");
                String opt = scanner.next();
                if (opt.equals("0")) return;
                if (opt.equals("S")) {JsonWriter.JSON_WRITER.nassaSave();return;}
                try {
                    Long finalId = Long.valueOf(opt);
                    Optional.ofNullable(MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                            .filter(f -> f.getId() == finalId)
                            .findFirst().get());
                    FlightMission flightMission = selectMission(finalId);
                    MissionFactory.MISSION_FACTORY.printDetailMission(flightMission);
                    NassaMenuMissionUpdate.menuMissionUpdate(flightMission, finalId);  //MENU FOR UPDATE
                } catch (NoSuchElementException |NumberFormatException e) {
                    LoggerImpl.LOGGER.info(RED + "BAD ID MISSION, TRY AGAIN.." + RST+" "+id);
                }
            } catch (
                    InputMismatchException e) {
                LoggerImpl.LOGGER.error(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }

    static void printListAllMissions() {
        System.out.println("-------------------------LIST OF ALL MISSIONS----------------------------------------------------- ");
        MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .forEach(x -> System.out.printf("ID#%1$-3d  MISSION: %2$-10s DISTANCE: %3$-7d STARSHIP: %4$-13s " +
                                "RANGE: %5$-7d START_DATE:%6$s  END_DATE:%7$s STATUS: %8$s\n"
                        ,x.getId(), x.getMissionsName(), x.getDistance(),
                         x.getAssignedSpaceShift().getName(), x.getAssignedSpaceShift().getFlightDistance(),
                         NassaContext.NASSA_CONTEXT.dateFormat.format(x.getStartDateTime()),
                         NassaContext.NASSA_CONTEXT.dateFormat.format(x.getEndDateTime()),x.getMissionResult()));
        System.out.println("\n------------------------------------------------------------------------------------------------ ");
    }

    static FlightMission selectMission(Long finalId) {
        FlightMission fligh = MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .filter(f -> f.getId() == finalId)
                .findFirst().get();
    return fligh;
    }
}