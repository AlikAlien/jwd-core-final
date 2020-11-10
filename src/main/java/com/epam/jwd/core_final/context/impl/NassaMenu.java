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
    public static final String BADINPUT = RED +"BAD INPUT. TRY AGAIN.."+ RST;
    public static final String BADOPTION = YELLOW+"BAD OPTIONS, PLEASE TRY AGAIN.."+RST;
    public static final String MAINNENU = "PLEASE SELECT OPTION:\n1.VIEW MISSIONS\n2.CREATE MISSION\n3.VIEW SPACESHIPS\n4.VIEW CREW\n5.EXIT\n";
    public static final String EXITNENU = GREEN+"EXIT.."+RST;
    public static final String RETURN = "\nSELECT "+RED+"0"+RST+" FOR RETURN TO MAIN MENU";
    public static final String BADID = YELLOW+"BAD ID, PLEASE TRY AGAIN.."+RST;

    @Override
    public ApplicationContext getApplicationContext() {
        UpdateTaskCompleted.UPDATER.updMission();
        UpdateTaskRandFailed.UPDATER.updMission();
        UpdaterRefreshInput.UPDATER.updInput();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 5){
            System.out.println(MAINNENU);
            try {
                option = scanner.nextInt();
                handleUserInput(option);
            }
            catch (InputMismatchException e) {
               System.out.println(BADINPUT);
               scanner.next();
            }
        }
        return null;
    }

    public int handleUserInput(int option) {
        int o = option;
        switch (o){
            case 1: //VIEW MISSION
                NassaMenuMissionView.menuView();
                break;
            case 2: //CREATE MISSION
                NassaMenuMissionCreate.nenuCreate(option);
                break;
            case 3: //VIEW SPACESHIPS
                NassaMenuShipsView.menuViewSpaceships();
                break;
            case 4: //VIEW CREW MEMBERS
                NassaMenuCrewView.menuViewCrew();
                break;
            case 5: //EXIT
                UpdateTaskCompleted.UPDATER.cancelUpd();
                UpdateTaskRandFailed.UPDATER.cancelUpd();
                UpdaterRefreshInput.UPDATER.cancelUpd();
                System.out.println(EXITNENU);
                LoggerImpl.LOGGER.info("EXIT..");
                System. exit(0);
                break;
            default:
                System.out.println(YELLOW+"BAD OPTIONS, PLEASE TRY AGAIN.."+RST);
                break;
        }
        return 0;
    }
}
