package com.epam.jwd.core_final.context.impl;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuViewShips extends NassaMenu{
    static void menuViewSpaceships() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                printListAllSpaceships();
                System.out.println("\nSELECT AND TYPE ID SPACESHIP FOR DETAIL VIEW, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU");
                id = scanner.nextLong();
                if (id == 0L) return;
                NassaMenuViewShipsDetail.menuShipsDetail();
                }
            catch (InputMismatchException e) {
                LoggerImpl.LOGGER.error(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }

    static void printListAllSpaceships() {
        System.out.println("-------------------------LIST OF ALL SPACESHIPS------------------------------- ");
        NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .forEach(x -> System.out.println("ID:" + x.getId() +" STARSHIP NAME:" + x.getName() + "  RANGE:"
                        + x.getFlightDistance() + "  STATUS:" + x.getIsBusy()+ "  FAILED_MISSION: "+x.getIsHasFailed()));
        System.out.println("------------------------------------------------------------------------------ ");
    }
}