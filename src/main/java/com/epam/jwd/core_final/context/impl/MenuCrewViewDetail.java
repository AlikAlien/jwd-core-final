package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.CrewMemberCrudImpl;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuCrewViewDetail extends NassaMenu {
    static void menuCrewDetail(Long id) {
        Scanner scanner = new Scanner(System.in);
        try {
            Long finalId = id;
            CrewMemberCrudImpl.CREW_FACTORY.checkExist(id);
            CrewMemberCrudImpl.CREW_FACTORY.printDetailItem(id);
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
