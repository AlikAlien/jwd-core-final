package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.FindStarshipImpl;

public class SpaceshipCrudImpl {
    public static final SpaceshipCrudImpl SPACESHIP_FACTORY = new SpaceshipCrudImpl();
    private SpaceshipCrudImpl() {}

    public Spaceship create (Route route) {
        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findAllSpaceshipsByCriteria(
                SpaceshipCriteriaBuilder.SPACESHIP_CRITERIA.create(route.getRouteDistance(), true));
        return spaceship;
    }

    public void printListAll() {
        System.out.println("-------------------------LIST OF ALL SPACESHIPS------------------------------------------------- ");
        NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .forEach(x -> System.out.printf("ID:%1$-3d STARSHIP: %2$-16s RANGE:%3$-8s STATUS:%4$-6s FAILED_MISSION: %5$s\n",
                        x.getId(),x.getName(),x.getFlightDistance(), x.getIsBusy(), x.getIsHasFailed()) );

        System.out.println("------------------------------------------------------------------------------------------------ ");
    }

    public void printDetailItem(Long id) {
        System.out.println("-------------------------DETAIL VIEW FOR SPACESHIP ID:"+id +"----------------- ");
        Spaceship spaceship = NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f->f.getId()==id)
                .peek(x -> System.out.println("ID:" + x.getId() +" STARSHIP NAME:" + x.getName() + "  RANGE:"
                        + x.getFlightDistance() + "  STATUS:" + x.getIsBusy()+ "  FAILED_MISSION: "+x.getIsHasFailed()))
                .findFirst().get();

        spaceship.getiDMission().stream().
                forEach(f-> System.out.println(f+".MISSIONS:"+ MissionCrudImpl.MISSION_FACTORY.getMissionName(f)));
        System.out.println("------------------------------------------------------------------------------ ");
    }

}
