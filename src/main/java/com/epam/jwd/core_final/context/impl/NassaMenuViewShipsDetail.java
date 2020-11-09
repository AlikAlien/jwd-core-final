package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.SpaceshipCrudImpl;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuViewShipsDetail extends NassaMenu{
    static void menuShipsDetail(Long id) {
        Scanner scanner = new Scanner(System.in);
             try {
                 Long finalId = id;
                 Optional.ofNullable(NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                         .filter(f -> f.getId() == finalId)
                         .findFirst().get());
                 SpaceshipCrudImpl.SPACESHIP_FACTORY.printDetailItem(id);
             } catch (NoSuchElementException e) {
                 System.out.println (YELLOW + "BAD ID SPACESHIP #" + id+ ", TYPE 0 FOR EXIT TO MAIN MENU" + RST);
                 scanner.nextLine();
                 return;
             }
             while (id != 0) {
                 id = scanner.nextLong();
                 System.out.println("TYPE " + RED + "0" + RST + " FOR EXIT TO MAIN MENU");
             }
    }
}
