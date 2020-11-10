package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.*;

public class NassaMenuMissions extends NassaMenu {
    static void menuView() {
        Long id = -1L;

        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                MissionCrudImpl.MISSION_FACTORY.printListAll();
                System.out.println("\nSELECT AND TYPE ID MISSION FOR DETAIL VIEW, ("+RED+"S"+RST+")AVE FOR SAVE ALL MISSIONS, OR "+RED+"0"+RST+" FOR RETURN TO MAIN MENU");
                String opt = scanner.next();
                if (opt.equals("0")) return;
                if (opt.equals("S")) {JsonWriter.JSON_WRITER.nassaSave();return;}
                try {
                    Long finalId = Long.valueOf(opt);
                    MissionCrudImpl.MISSION_FACTORY.checkExist(id);
                    FlightMission flightMission = MissionCrudImpl.MISSION_FACTORY.getMission(finalId);
                    MissionCrudImpl.MISSION_FACTORY.printDetailItem(flightMission);
                    NassaMenuMissionUpdate.menuMissionUpdate(flightMission, finalId);  //MENU FOR UPDATE

                } catch (NoSuchElementException | NumberFormatException e) {
                    System.out.println(RED + "BAD ID MISSION, TRY AGAIN.." + RST+" "+id);
                }
            } catch (
                    InputMismatchException e) {
                System.out.println(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }
}