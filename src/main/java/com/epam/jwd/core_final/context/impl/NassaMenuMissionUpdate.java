package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;

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
                    MissionUdpFreeResources(flightMission,MissionResult.CANCELLED);
                    break;
                case "F":
                    MissionUdpFreeResources(flightMission,MissionResult.FAILED);
                    flightMission.getAssignedSpaceShift().setHasFailedMissions(true);
                    break;
                case "D":
                    MissionUdpFreeResources(flightMission, MissionResult.COMPLETED);
                    break;
                default:
                    System.out.println("BAD OPTION, SELECT ("+RED+"C"+RST+")ANCEL, ("+RED+"F"+RST+")AIL, ("+RED+"D"+RST+")ONE, OR \"0\" AND TRY AGAIN..");
            }
        }
    }

    public static void MissionUdpFreeResources(FlightMission flightMission, MissionResult result ){
        flightMission.setMissionResult(result);
        flightMission.setEndDateTime();
        flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
        flightMission.getAssignedCrew().stream()
                .forEach(f->f.setReadyForNextMissions(true));
        System.out.println("MISSION ID#"+flightMission.getId()+" "+flightMission.getMissionsName()+" " + result.toString());
    }
}