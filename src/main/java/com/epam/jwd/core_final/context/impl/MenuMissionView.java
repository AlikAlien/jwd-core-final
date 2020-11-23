package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import java.util.*;

public class MenuMissionView extends NassaMenu {
    public static final String MISSIONNENU = "\nSELECT AND TYPE ID MISSION FOR DETAIL VIEW AND UPDATE, (" + RED + "S" + RST + ")AVE FOR SAVE ALL MISSIONS, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU";
    static void menuView() {
        Long id = -1L;

        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                MissionCrudImpl.MISSION_FACTORY.printListAll();
                System.out.println(MISSIONNENU);
                String opt = scanner.next();
                if (opt.equals("0")) return;
                if (opt.equals("S")) {JsonWriter.JSON_WRITER.nassaSave();return;}
                try {
                    Long finalId = Long.valueOf(opt);
                    FlightMission flightMission = MissionCrudImpl.MISSION_FACTORY.getMission(finalId);
                    MissionCrudImpl.MISSION_FACTORY.printDetailItem(flightMission);
                    MenuMissionUpdate.menuMissionUpdate(flightMission, finalId);  //MENU FOR UPDATE
                } catch (NoSuchElementException | NumberFormatException e) {
                    System.out.println(BADID);
                }
            } catch (
                    InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
    }
}