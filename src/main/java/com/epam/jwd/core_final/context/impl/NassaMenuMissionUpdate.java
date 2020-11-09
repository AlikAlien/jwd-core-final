package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuMissionUpdate extends NassaMenu{
    static void menuMissionUpdate(FlightMission flightMission, Long id) {
        String o = "-1";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFOR STATUS UPDATE THIS MISSIONS SELECT AND TYPE NEXT OPTION: ("+RED+"C"+RST+")ANCEL, ("+RED+"F"+RST+")AIL, ("+RED+"D"+RST+")ONE, OR TYPE \"0\" FOR EXIT PREVIOUS MENU ");
        while (!o.equals("0")){
            try {
                o = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println(RED+"BAD INPUT. TRY AGAIN.."+RST);
                scanner.next();
            }
            switch (o){
                case "C":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission,MissionResult.CANCELLED);
                    break;
                case "F":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission,MissionResult.FAILED);
                    break;
                case "D":
                    MissionCrudImpl.MISSION_FACTORY.delete(flightMission, MissionResult.COMPLETED);
                    break;
                default:
                    System.out.println("BAD OPTION, SELECT ("+RED+"C"+RST+")ANCEL, ("+RED+"F"+RST+")AIL, ("+RED+"D"+RST+")ONE, OR \"0\" AND TRY AGAIN..");
            }
        }
    }
}