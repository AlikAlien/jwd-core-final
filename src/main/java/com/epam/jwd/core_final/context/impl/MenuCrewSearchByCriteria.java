package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberCrudImpl;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuCrewSearchByCriteria extends NassaMenu {
    public static final String CREWSEARCH = "\nSELECT CRITERIA FOR SEARCH CREW MEMBERS, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU";
    public static final String OPTIONS =        "AVAILABLE OPTION: " +
            RED+"\n0    " +RST+ "RETURN TO MAIN MENU" +
            RED+"\n*    " +RST+ "FOR PRINT ALL CREW "+
            RED+"\nID   " +RST+ "LIKE " +GREEN+ " ID:13 " +RST+ "  (DETAIL VIEW FOR SELECTED CREW MEMBER)"+
            RED+"\nNAME " +RST+ "LIKE " +GREEN+ " NAME:Jo" + RST+ " (LIST OF CREW WHOSE NAMES INCLUDE THE SPECIFIED COMBINATION)"+
            RED+"\nROLE " +RST+ "LIKE " +GREEN+ " ROLE:1  " +RST+ "(1-MISSION SPECIALIST, 2-FLIGHT ENGINEER, 3-PILOT, 4-COMMANDER)"+
            RED+"\nRANK " +RST+ "LIKE " +GREEN+ " RANK:2  " +RST+ "(1-TRAINEE, 2-SECOND OFFICER, 3-FIRST OFFICER, 4-CAPTAIN)";

    static void menuSearch() {
        String findBy = "-1";
        Scanner scanner = new Scanner(System.in);
        String[] words;
        System.out.println(CREWSEARCH);
        while (!findBy.equals("0")) {
            System.out.println(OPTIONS);
            findBy = scanner.nextLine();
            String option = findBy.replaceAll("\\s","");
            if (findBy.equals("0")) continue;
            if (findBy.equals("*")) CrewMemberCrudImpl.CREW_FACTORY.printListAll();
            else if (!findBy.contains(":")) {
                System.out.println(BADINPUT);
                continue;
            }
            words = option.split(":",2);
            switch (words[0]) {
                case "*":
                    break;
                case "ID":
                    try{
                        CrewMemberCrudImpl.CREW_FACTORY.printDetailItem(Long.valueOf(words[1]));
                    }
                    catch (NumberFormatException | NoSuchElementException e)
                    {
                        System.out.println(BADID);
                    }
                    break;
                case "NAME":
                    System.out.println("FIND CREW NAME: "+ words[1]);
                    CrewMemberCrudImpl.CREW_FACTORY.printListByName(words[1]);
                    break;
                case "ROLE":
                    try {
                        System.out.println("FIND CREW ROLE: "+ Role.getRoleById(Integer.parseInt(words[1])));
                        CrewMemberCrudImpl.CREW_FACTORY.printListByRole(Role.getRoleById(Integer.parseInt(words[1])));
                    }
                    catch (RuntimeException e){
                    System.out.println(BADINPUT);
                    }
                    break;
                case "RANK":
                    try {
                        System.out.println("FIND CREW RANK: "+ Rank.getRankById(Integer.parseInt(words[1])));
                        CrewMemberCrudImpl.CREW_FACTORY.printListByRank(Rank.getRankById(Integer.parseInt(words[1])));
                    }
                    catch (RuntimeException e){
                        System.out.println(BADINPUT);
                    }
                    break;
                //case "STATUS":
                //    if (words[1].contains("FREE")) CrewMemberCrudImpl.CREW_FACTORY.printListByStatus(false);
                //    if (words[1].contains("BUSY")) CrewMemberCrudImpl.CREW_FACTORY.printListByStatus(true);
                //    break;
                default:
                    System.out.println(BADOPTION);
            }
        }
    }
}