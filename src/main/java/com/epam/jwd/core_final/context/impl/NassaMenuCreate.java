package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuCreate {
    static void nenuCreate(int option ) {
        int o = option;
        System.out.println("\n#ID  #ROUTE DESTINATION  #DISTANCE  ");
        NassaContext.NASSA_CONTEXT.getRoutes().stream()
                .forEach(x -> System.out.println(x.getIdRoute() + " " + x.getName() + " " + x.getRoureDistance()));
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                System.out.println("\nSELECT AND TYPE ID ROUTE, OR PRESS \"0\" FOR MAIN MENU");
                id = scanner.nextLong();
                if (id == 0) return;
                Long finalId = id;
                Route route = NassaContext.NASSA_CONTEXT.getRoutes().stream()
                        .filter(f -> f.getIdRoute() == finalId)
                        .findFirst().get();
                if (route!=null){
                    MissionFactory.MISSION_FACTORY.createMission(route.getRoureDistance(), route.getName());
                }
                else{
                    System.out.println("BAD ROUTE. TRY AGAIN..");
                }
            } catch (
                    InputMismatchException e) {
                System.out.println("BAD INPUT. TRY AGAIN..");
            }

        }
    }
}