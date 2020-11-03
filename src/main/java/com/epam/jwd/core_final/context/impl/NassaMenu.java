package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NassaMenu implements ApplicationMenu {
    @Override
    public ApplicationContext getApplicationContext() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        int menu = 0;
        while (option != 4){
            printAvailableOptions(menu);
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("BAD INPUT. TRY AGAIN..");
            }
            handleUserInput(option);
        }
        return null;
    }

    public int handleUserInput(int option) {
        int o = option;
        Scanner scanner = new Scanner(System.in);
        switch (o){
            case 1:
                System.out.println("all missions: ");
                Mission.MISSION.getFlightMissions().stream()
                        .forEach (x-> System.out.println("Name = " + x.getName()));
                break;
            case 2: //CREATE MISSION
                NassaContext.NASSA_CONTEXT.getRoutes().stream()
                        .forEach (x-> System.out.println(x.getIdRoute()+" "+x.getName()+" "+ x.getRoureDistance()));
                System.out.println("\nSELECT AND TYPE ID ROUTE..");
                Long id = null;
                //right
                //while (right)
                try {
                    id = scanner.nextLong();
                }
                catch (InputMismatchException e) {
                    System.out.println("BAD INPUT. TRY AGAIN..");
                }
                Long finalId = id;
                Long dist;
                dist = NassaContext.NASSA_CONTEXT.getRoutes().stream()
                    .filter (f -> f.getIdRoute() == finalId)
                    .findFirst().get().getRoureDistance();

                System.out.println(dist);
                Mission.MISSION.createMission(2222l);

                break;
            case 3:
                System.out.println("PRINT FILE NAME FOR SAVE: ");
                 String fileName = null;
                try {
                        fileName = scanner.next();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("BAD INPUT. TRY AGAIN..");
                    }
                        System.out.println("FILE NAME: \n" +fileName+"\n");
                break;
            case 4:
                System.out.println("EXIT..");
                break;
            default:
                System.out.println("BAD OPTIONS. PLEASE TRY AGAIN..");
                break;
        }
        return 0;
    }

    public void printAvailableOptions(int i) {
        System.out.println(stringUserMenu(i));
    }

    private String stringUserMenu (int i){
        String strMenu;
        switch (i) {
            case 0: strMenu = "PLEASE SELECT OPTION:\n1.VIEW MISSION\n2.CREATE MISSION\n3.SAVE MISSIONS\n4.EXIT\n";
                break;
            case 1: strMenu  = "SELECT VIEW OPTION:\n1.VIEW ALL MISSION\n2.VIEW ACTIVE MISSION\n3.MAIN MENU\n";
                break;
            case 2: strMenu  = "SELECT CREATE OPTION:\n1.VIEW ALL ROUTE\n1.VIEW AVAILABLE ROUTE\n3.MAIN MENU\n";
                break;
            case 23: strMenu  = "SELECT CREATE OPTION:\n1.ACCEPT MISSION\n2.CANCEL SELECT\n3.MAIN MENU\n";;
                break;
            case 3: strMenu  = "INPUT FILE NAME FOR SAVE:";
                break;
            case 4: strMenu  = "YOU SELECTED EXIT";
                break;
            default: strMenu = "TYPE NOT FOUND";
                break;
        }
        return strMenu;
    }
}
