package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.SpaceshipCrudImpl;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuShipsViewDetail extends NassaMenu {
    static void menuShipsDetail(Long id) {
        Scanner scanner = new Scanner(System.in);
        try {
            SpaceshipCrudImpl.SPACESHIP_FACTORY.printDetailItem(id);
        } catch (NoSuchElementException e) {
            System.out.println(BADID);
            scanner.nextLine();
            return;
        }
        while (id != 0) {
            System.out.println(RETURN);
            id = scanner.nextLong();
        }
    }
}
