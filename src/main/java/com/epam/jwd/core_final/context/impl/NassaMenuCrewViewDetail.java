package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.impl.CrewMemberCrudImpl;
import com.epam.jwd.core_final.factory.impl.SpaceshipCrudImpl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuCrewViewDetail extends NassaMenu {
    static void menuCrewDetail(Long id) {
        Scanner scanner = new Scanner(System.in);
        try {
            Long finalId = id;
            Optional.ofNullable(NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                    .filter(f -> f.getId() == finalId)
                    .findFirst().get());
            CrewMemberCrudImpl.CREW_FACTORY.printDetailItem(id);
        } catch (NoSuchElementException e) {
            System.out.println(BADID);
            scanner.nextLine();
            return;
        }
        while (id != 0) {
            id = scanner.nextLong();
            System.out.println(RETURN);
        }
    }
}
