package com.epam.jwd.core_final.context.impl;
import com.epam.jwd.core_final.factory.impl.SpaceshipCrudImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuShipsView extends NassaMenu{
    public static final String IDSHIP = "\nSELECT AND TYPE ID SPACESHIP FOR DETAIL VIEW, OR " + RED + "0" + RST + " FOR RETURN TO MAIN MENU";

    static void menuViewSpaceships() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                SpaceshipCrudImpl.SPACESHIP_FACTORY.printListAll();
                System.out.println(IDSHIP);
                id = scanner.nextLong();
                if (id == 0L) return;
                NassaMenuShipsViewDetail.menuShipsDetail(id);
                }
            catch (InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
    }
}