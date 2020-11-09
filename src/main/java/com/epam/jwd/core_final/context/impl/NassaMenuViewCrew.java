package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuViewCrew extends NassaMenu{
    static void menuViewCrew() {
        Long id = -1L;
        Scanner scanner = new Scanner(System.in);
        while (id != 0) {
            try {
                CrewMemberFactory.CREW_FACTORY.printListAll();
                System.out.println("\nSELECT "+RED+"0"+RST+" FOR RETURN TO MAIN MENU");
                id = scanner.nextLong();
                if (id==0L) return;
            } catch (
                    InputMismatchException e) {
                System.out.println(RED + "BAD INPUT, TRY AGAIN.." + RST);
                scanner.next();
            }
        }
    }
}
