package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.CrewMemberCrudImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCrewView extends NassaMenu {
    public static final String IDCREW = "\nSELECT AND TYPE ID CREWMEMBER FOR DETAIL VIEW, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU";

    static void menuViewCrew() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                CrewMemberCrudImpl.CREW_FACTORY.printListAll();
                System.out.println(IDCREW);
                id = scanner.nextLong();
                if (id == 0L) return;
                MenuCrewViewDetail.menuCrewDetail(id);
            } catch (
                    InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
    }
}
