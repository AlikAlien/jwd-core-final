package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuCreate extends NassaMenu{
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
                try { Optional.ofNullable(NassaContext.NASSA_CONTEXT.getRoutes().stream()
                        .filter(f -> f.getIdRoute() == finalId)
                        .findFirst().get());
                    Route route = NassaContext.NASSA_CONTEXT.getRoutes().stream()
                            .filter(f -> f.getIdRoute() == finalId)
                            .findFirst().get();
                    MissionFactory.MISSION_FACTORY.createMission(route.getRoureDistance(), route.getName());
                }
                catch (NoSuchElementException e){
                    System.out.println(RED +"BAD ID ROUTE \""+ finalId+"\", OR NO SHIPS AVAILABLE FOR THIS ROUTE, TRY AGAIN.."+ RST);
                }
            } catch (
                    InputMismatchException e) {
                System.out.println(RED +"BAD INPUT. TRY AGAIN.."+ RST);
                scanner.next();
            }
        }
    }
}