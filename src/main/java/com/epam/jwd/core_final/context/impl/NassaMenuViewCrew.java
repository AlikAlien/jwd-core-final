package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuViewCrew extends NassaMenu{
    static void menuViewCrew() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                CrewMemberFactory.CREW_FACTORY.printListAll();
                System.out.println(RETURN);
                id = scanner.nextLong();
                if (id==0L) return;
            } catch (
                    InputMismatchException e) {
                System.out.println(BADINPUT);
                scanner.next();
            }
        }
    }
}
