package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuViewUpdate extends NassaMenu{
    static void NassaMenuViewCancel(FlightMission flightMission, Long id) {
        String o = "-1";
        Scanner scanner = new Scanner(System.in);
        System.out.println("FOR STATUS UPDATE THIS MISSIONS SELECT AND TYPE NEXT OPTION: ("+RED+"C"+RST+")ANCEL, ("+RED+"F"+RST+")AIL, ("+RED+"D"+RST+")ONE, OR TYPE \"0\" FOR EXIT PREVIOUS MENU ");
        while (!o.equals("0")){
            try {
                o = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println(RED+"BAD INPUT. TRY AGAIN.."+RST);
                scanner.next();
            }
            switch (o){
                case "C":
                    flightMission.setMissionResult(MissionResult.CANCELLED);
                    flightMission.setEndDateTime();
                    flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
                    System.out.println("MISSION ID "+id+ " CANCELED");
                    break;
                case "F":
                    flightMission.setMissionResult(MissionResult.FAILED);
                    flightMission.setEndDateTime();
                    flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
                    flightMission.getAssignedSpaceShift().setHasFailedMissions(true);
                    System.out.println("MISSION ID "+id+ " FAILED");
                    break;
                case "D":
                    flightMission.setMissionResult(MissionResult.COMPLETED);
                    flightMission.setEndDateTime();
                    flightMission.getAssignedSpaceShift().setReadyForNextMissions(true);
                    System.out.println("MISSION ID "+id+ " COMPLETED");
                    break;
                default:
                    System.out.println("BAD OPTION, SELECT ("+RED+"C"+RST+")ANCEL, ("+RED+"F"+RST+")AIL, ("+RED+"D"+RST+")ONE, OR \"0\" AND TRY AGAIN..");
            }
        }
    }
}
