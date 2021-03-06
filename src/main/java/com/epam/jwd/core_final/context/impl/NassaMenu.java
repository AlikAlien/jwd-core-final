package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.impl.UpdateTaskCompleted;
import com.epam.jwd.core_final.service.impl.UpdateTaskRandFailed;
import com.epam.jwd.core_final.service.impl.UpdaterRefreshInput;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenu implements ApplicationMenu {
    public static final String RST = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static final String BADINPUT     = RED + "BAD INPUT. TRY AGAIN.." + RST;
    public static final String BADID     = YELLOW + "BAD ID, PLEASE TRY AGAIN.." + RST;
    public static final String BADOPTION = YELLOW + "BAD OPTIONS, PLEASE TRY AGAIN.." + RST;
    public static final String MAINNENU = GREEN+"PLEASE SELECT OPTION:\n1.VIEW AND UPDATE MISSIONS\n2.CREATE MISSION\n3.VIEW SPACESHIPS\n4.SEARCH AND VIEW CREW\n5.EXIT\n"+RST;
    public static final String EXITNENU = GREEN + "EXIT.." + RST;
    public static final String RETURN = "\nSELECT " + GREEN + "0" + RST + " FOR RETURN TO MAIN MENU";

    @Override
    public ApplicationContext getApplicationContext() {
        UpdateTaskCompleted.UPDATER.updMission();
        UpdateTaskRandFailed.UPDATER.updMission();
        UpdaterRefreshInput.UPDATER.updInput();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 5) {
            System.out.println(MAINNENU);
            try {
                option = scanner.nextInt();
                handleUserInput(option);
            } catch (InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
        return null;
    }

    public int handleUserInput(int o) {
        switch (o) {
            case 1: //VIEW MISSION
                MenuMissionView.menuView();
                break;
            case 2: //CREATE MISSION
                MenuMissionCreate.nenuCreate(0);
                break;
            case 3: //VIEW SPACESHIPS
                MenuShipsView.menuViewSpaceships();
                break;
            case 4: //SEARCH AND VIEW CREW MEMBERS
                MenuCrewSearchByCriteria.menuSearch();
                break;
            case 5: //EXIT
                UpdateTaskCompleted.UPDATER.cancelUpd();
                UpdateTaskRandFailed.UPDATER.cancelUpd();
                UpdaterRefreshInput.UPDATER.cancelUpd();
                System.out.println(EXITNENU);
                LoggerImpl.LOGGER.info("EXIT..");
                System.exit(0);
                break;
            default:
                System.out.println(BADOPTION);
                break;
        }
        return 0;
    }
}
