package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.FindStarshipImpl;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

public class SpaceshipCrudImpl implements EntityFactory{
    public static final SpaceshipCrudImpl SPACESHIP_FACTORY = new SpaceshipCrudImpl();
    private SpaceshipCrudImpl() {}

    public Spaceship create (AbstractBaseEntity obj) {
        Route route = null;
        if (obj instanceof Route) route = (Route) obj;

        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findAllSpaceshipsByCriteria(
                SpaceshipCriteriaBuilder.SPACESHIP_CRITERIA.create(route.getRouteDistance(), true));
        if (spaceship==null) System.out.println(YELLOW+"NOT FREE SPACESHIP FOR ROUTE#"+route.getIdRoute()+RST);
        return spaceship;
    }

    public void printListAll() {
        System.out.println("-------------------------LIST OF ALL SPACESHIPS------------------------------------------------- ");
        NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .forEach(x -> System.out.printf("ID#%1$-3d STARSHIP: %2$-16s RANGE:%3$-8s STATUS:%4$-6s FAILED_MISSION: %5$s\n",
                        x.getId(),x.getName(),x.getFlightDistance(), x.getIsBusy(), x.getIsHasFailed()) );

        System.out.println("------------------------------------------------------------------------------------------------ ");
    }

    public void printDetailItem(Long id) {
        System.out.println("-------------------------DETAIL VIEW FOR SPACESHIP ID#"+id +"----------------- ");
        Spaceship spaceship = NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f->f.getId()==id)
                .peek(x -> System.out.println("ID#" + x.getId() +" STARSHIP NAME:" + x.getName() + "  RANGE:"
                        + x.getFlightDistance() + "  STATUS:" + x.getIsBusy()+ "  FAILED_MISSION: "+x.getIsHasFailed()))
                .findFirst().get();

        spaceship.getiDMission().stream().
                forEach(f-> System.out.println("MISSIONS ID#"+f+" "+ MissionCrudImpl.MISSION_FACTORY.getMissionName(f)));
        System.out.println("------------------------------------------------------------------------------ ");
    }

}
