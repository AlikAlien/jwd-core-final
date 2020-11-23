package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMissionUpdate extends NassaMenu {

    public static final String MISSIONUDP = "\nFOR STATUS UPDATE THIS MISSIONS SELECT AND TYPE NEXT OPTION: \n(" + RED + "C" + RST + ")ancel, (" + RED + "F" + RST + ")ail, (" + RED + "D" + RST + ")one, OR TYPE " + RED + "0 " + RST + " FOR EXIT PREVIOUS MENU ";

    static void menuMissionUpdate(FlightMission flightMission, Long id) {
        String o = "-1";
        Scanner scanner = new Scanner(System.in);
        System.out.println(MISSIONUDP);
        while (!o.equals("0")) {
            try {
                o = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
            switch (o) {
                case "C":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission, MissionResult.CANCELLED);
                    break;
                case "F":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission, MissionResult.FAILED);
                    break;
                case "D":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission, MissionResult.COMPLETED);
                    break;
                default:
                    System.out.println(BADOPTION);
            }
        }
    }
}