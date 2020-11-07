package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class NassaMenuViewShipsDetail extends NassaMenu{
    static void menuShipsDetail() {
        Long id =-1L;
        Scanner scanner = new Scanner(System.in);
         while (id != 0){
            id = scanner.nextLong();
            try {
                Long finalId = id;
                Optional.ofNullable(NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                        .filter(f -> f.getId() == finalId)
                        .findFirst().get());
            } catch ( NoSuchElementException e) {
                LoggerImpl.LOGGER.info(YELLOW + "BAD ID MISSION, TRY AGAIN.. OR TYPE "+RED+"0"+RST+" FOR EXIT TO MAIN MENU" + RST + " " + id);
                scanner.next();
                return;
            }
            printDetailSpaceship(id);
        }
    }

    static void printDetailSpaceship(Long id) {
        System.out.println("-------------------------DETAIL VIEW FOR SPACESHIP ID:"+id +"----------------- ");
        Spaceship spaceship = NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f->f.getId()==id)
                .peek(x -> System.out.println("ID:" + x.getId() +" STARSHIP NAME:" + x.getName() + "  RANGE:"
                        + x.getFlightDistance() + "  STATUS:" + x.getIsBusy()+ "  FAILED_MISSION: "+x.getIsHasFailed()))
                .findFirst().get();

        spaceship.getiDMission().stream().
                forEach(f-> System.out.println(f+".MISSIONS:"+ MissionFactory.MISSION_FACTORY.getMissionName(f)));
        System.out.println("------------------------------------------------------------------------------ ");
    }
}
