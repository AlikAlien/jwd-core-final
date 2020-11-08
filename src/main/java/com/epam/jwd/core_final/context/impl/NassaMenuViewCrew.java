package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
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
                System.out.println("\nSELECT "+RED+"0"+RST+" FOR RETURN TO MAIN MENU");
                id = scanner.nextLong();
                if (id==0L) return;
            } catch (
                    InputMismatchException e) {
                System.out.println(RED + "BAD INPUT, TRY AGAIN.." + RST);
                LoggerImpl.LOGGER.error("BAD INPUT, TRY AGAIN..");
                scanner.next();
            }
        }
    }

    static void printListAllCrew() {
        System.out.println("-------------------------LIST OF ALL CREW MEMBERS------------------------------------- ");
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .forEach(x -> System.out.printf("ID:%1$3d NAME:%2$-18s ROLE:%3$-18s RANK:%4$-14s STATUS:%5$s\n",
                        x.getId(),x.getName(),x.getRole(),Rank.getRankById(x.getRank()),x.getIsBusy()));
        System.out.println("-------------------------------------------------------------------------------------- ");
    }

}
