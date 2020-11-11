package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberCrudImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCrewSearchByCriteria extends NassaMenu {
    public static final String CREWSEARCH = "\nSELECT CRITERIA FOR SEARCH CREWMEMBERS, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU\n " +
            "AVAILABLE OPTION: "+RED+"ID: NAME: ROLE: RANK: "+RST+"- TYPE OPTIONS AND ADD TO THIS YOUR STRING SEARCH LIKE AS \"NAME:Jo\"";

    static void menuSearch() {
        String findBy = "-1";
        Scanner scanner = new Scanner(System.in);
        System.out.println(CREWSEARCH);
        String[] words;
        while (!findBy.equals("0")) {
            try {
                findBy = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
            words = findBy.split(":");
            switch (words[0]) {
                case "ID":
                    CrewMemberCrudImpl.CREW_FACTORY.printListById(Long.valueOf(words[1]));
                    break;
                case "NAME":
                    CrewMemberCrudImpl.CREW_FACTORY.printListByName(words[1]);
                    break;
                case "ROLE":
                    CrewMemberCrudImpl.CREW_FACTORY.printListByRole(Role.getRoleById(Integer.valueOf(words[1])));
                    break;
                case "RANK":
                    CrewMemberCrudImpl.CREW_FACTORY.printListByRank(Rank.getRankById(Integer.valueOf(words[1])));
                    break;
                default:
                    System.out.println(BADOPTION);
            }
        }
    }
}