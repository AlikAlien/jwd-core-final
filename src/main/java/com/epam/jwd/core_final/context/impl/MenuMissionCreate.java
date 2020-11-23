package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.factory.EntityType;
import com.epam.jwd.core_final.factory.impl.EntityFactoryImpl;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.factory.impl.RouteCrudImpl;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuMissionCreate extends NassaMenu {
    public static final String OPTION = "\nSELECT AND TYPE ID ROUTE FOR CREATE MISSION, OR PRESS \"0\" FOR MAIN MENU\n";
    public static final String NOSHIP = "WARNING: NO SHIPS AVAILABLE FOR THIS ROUTE!";
    public static final String DETAILSHIP = "ID#    ROUTE         DISTANCE \n";
    public static final String DELIMETER = "------------------------------\n";


    static void nenuCreate(int option) {
        int o = option;
        System.out.print(DELIMETER+DETAILSHIP+DELIMETER);
        NassaContext.NASSA_CONTEXT.getRoutes().stream()
                .forEach(x -> System.out.printf("%1$-3d    %2$-12s  %3$d\n",
                        x.getIdRoute(), x.getName(), x.getRouteDistance()));
        System.out.print(DELIMETER);
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                System.out.print(OPTION);
                id = scanner.nextLong();
                if (id == 0) return;
                Long finalId = id;

                try {
                    RouteCrudImpl.ROUTE_CRUD.checkExist(finalId);

                    try {
                        Route route = RouteCrudImpl.ROUTE_CRUD.create(id);
                        FlightMission flightMission = (FlightMission) EntityFactoryImpl.FACTORY.create(route, EntityType.MISSION);
                        MissionCrudImpl.MISSION_FACTORY.printDetailItem(flightMission);
                    } catch (NoSuchElementException | NullPointerException e) {
                        System.out.println(YELLOW + NOSHIP + RST);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(BADID);
                }

            } catch (
                    InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
    }
}