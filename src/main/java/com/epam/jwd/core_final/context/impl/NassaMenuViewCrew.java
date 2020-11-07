package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuViewCrew extends NassaMenu{
    static void menuViewCrew() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                printListAllCrew();
                System.out.println("\nSELECT AND TYPE ID CREW FOR DETAIL VIEW, OR "+RED+"0"+RST+" FOR RETURN TO MAIN MENU");
                id = scanner.nextLong();
                if (id==0L) return;
                try {
                    Long finalId = id;
                    Optional.ofNullable(NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                            .filter(f -> f.getId() == finalId)
                            .findFirst().get());
                    CrewMember crewMember = printDetailCrewMember(finalId);
                    scanner.next();
                } catch (NoSuchElementException |NumberFormatException e) {
                    LoggerImpl.LOGGER.info(RED + "BAD ID CREW MEMBER, TRY AGAIN.." + RST+" "+id);
                }
            } catch (
                    InputMismatchException e) {
                LoggerImpl.LOGGER.error(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }

    static void printListAllCrew() {
        System.out.println("-------------------------LIST OF ALL CREW MEMBERS----------------------------- ");
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .forEach(x -> System.out.println("ID:" + x.getId() +" NAME:" + x.getName() + "  ROLE:"
                        + x.getRole() + "  RANK:" + x.getRank()+ "  STATUS:" + x.getIsBusy()));
        System.out.println("------------------------------------------------------------------------------ ");
    }

    static CrewMember printDetailCrewMember(Long id) {
        System.out.println("-------------------------DETAIL FOR CREW MEMBER ID:"+id+"---------------------- ");
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getId()==id)
                .forEach(x -> System.out.println("ID:" + x.getId() +" NAME:" + x.getName() + "  ROLE:"
                        + x.getRole() + "  RANK:" + x.getRank()+ "  STATUS:" + x.getIsBusy()));

        System.out.println("------------------------------------------------------------------------------ ");
        return null;
    }
}
