package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.FindStarshipImpl;

import java.util.Objects;
import java.util.Optional;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

public class SpaceshipCrudImpl implements EntityFactory{
    public static final SpaceshipCrudImpl SPACESHIP_FACTORY = new SpaceshipCrudImpl();
    private SpaceshipCrudImpl() {}
    final static String START_DELIMITER = "--------------------------------------- SPACESHIPS ---------------------------------------------- ";
    final static String END_DELIMITER   = "------------------------------------------------------------------------------------------------- ";

    public Spaceship create (AbstractBaseEntity obj) {
        Route route = null;
        if (obj instanceof Route) route = (Route) obj;
        assert route != null;
        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findAllSpaceshipsByCriteria(
                SpaceshipCriteriaBuilder.SPACESHIP_CRITERIA.create(route.getRouteDistance(), true));
        if (spaceship==null) System.out.println(YELLOW+"NOT FREE SPACESHIP FOR ROUTE#"+route.getIdRoute()+RST);
        return spaceship;
    }

    public void printListAll() {
        System.out.println(START_DELIMITER);
        NassaContext.NASSA_CONTEXT.getSpaceships()
                .forEach(x -> System.out.printf("ID#%1$-3d STARSHIP: %2$-16s RANGE:%3$-8s STATUS:%4$-6s FAILED_MISSION: %5$s\n",
                        x.getId(),x.getName(),x.getFlightDistance(), x.getIsBusy(), x.getIsHasFailed()) );

        System.out.println(END_DELIMITER);
    }

    public void printDetailItem(Long id) {
        System.out.println(START_DELIMITER);
        Spaceship spaceship = NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f-> Objects.equals(f.getId(), id))
                .peek(x -> System.out.println("ID#" + x.getId() +" STARSHIP NAME:" + x.getName() + "  RANGE:"
                        + x.getFlightDistance() + "  STATUS:" + x.getIsBusy()+ "  FAILED_MISSION: "+x.getIsHasFailed()))
                .findFirst().get();

        spaceship.getiDMission().stream().
                forEach(f-> System.out.println("MISSIONS ID#"+f+" "+ MissionCrudImpl.MISSION_FACTORY.getMissionName(f)));
        System.out.println(END_DELIMITER);
    }
    public Optional checkExist (Long id) {
        Optional spaceship = Optional.ofNullable(NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f -> f.getId() == id)
                .findFirst().get());
        return  spaceship;
    }
}
