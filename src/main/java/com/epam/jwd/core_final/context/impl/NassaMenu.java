package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenu implements ApplicationMenu {
    public static final String RST = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    @Override
    public ApplicationContext getApplicationContext() {
        UpdateTask.UPDATER.upd();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 4){
            printAvailableOptions(0);
            try {
                option = scanner.nextInt();
                handleUserInput(option);
            }
            catch (InputMismatchException exception) {
               LoggerImpl.LOGGER.error(RED+"BAD INPUT, TRY AGAIN.."+RST);
               scanner.next();
            }
        }
        return null;
    }

    public int handleUserInput(int option) {
        int o = option;
        switch (o){
            case 1: //VIEW MISSION
                NassaMenuView.menuView();
                break;
            case 2: //CREATE MISSION
                NassaMenuCreate.nenuCreate(option);
                break;
            case 3: //SAVE
                NassaMenuSave.nassaSave();
                break;
            case 4: //EXIT
                LoggerImpl.LOGGER.info("EXIT..");
                UpdateTask.UPDATER.cancelUpd();
                break;
            default:
                LoggerImpl.LOGGER.info("BAD OPTIONS, PLEASE TRY AGAIN..");
                break;
        }
        return 0;
    }

    public void printAvailableOptions(int i)
{       String strMenu;
        switch (i) {
            case 0: strMenu = "PLEASE SELECT OPTION:\n1.VIEW MISSION\n2.CREATE MISSION\n3.SAVE MISSIONS\n4.EXIT\n";
                break;
            case 3: strMenu  = "INPUT FILE NAME FOR SAVE:";
                break;
            case 4: strMenu  = "YOU SELECTED EXIT";
                break;
            default: strMenu = "SELECTED MENU NOT FOUND";
                break;
        }
    System.out.println(strMenu);
    }
}
