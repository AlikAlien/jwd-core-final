package com.epam.jwd.core_final.context.impl;
import com.epam.jwd.core_final.factory.impl.SpaceshipCrudImpl;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuViewShips extends NassaMenu{
    static void menuViewSpaceships() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                SpaceshipCrudImpl.SPACESHIP_FACTORY.printListAll();
                System.out.println("\nSELECT AND TYPE ID SPACESHIP FOR DETAIL VIEW, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU");
                id = scanner.nextLong();
                if (id == 0L) return;
                NassaMenuViewShipsDetail.menuShipsDetail(id);
                }
            catch (InputMismatchException e) {
                System.out.println(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }
}