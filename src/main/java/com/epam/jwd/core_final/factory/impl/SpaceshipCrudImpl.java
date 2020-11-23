package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.criteria.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.FindStarshipImpl;

import java.util.Optional;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

public class SpaceshipCrudImpl implements EntityFactory{
    public static final SpaceshipCrudImpl SPACESHIP_FACTORY = new SpaceshipCrudImpl();
    private SpaceshipCrudImpl() {}
    final static String SHIP_DETAIL = " %1$-3d   %2$-16s  %3$-8s  %4$-6s     %5$-3d       %6$s\n";
    final static String MISSION_DETAIL = "MISSION %1$-16s \n";
    final static String DELIMITER       = "---------------------------------------------------------------";
    final static String FIELDS          = " ID#    STARSHIP         RANGE    STATUS  MISSIONS  HAS FAILED?";

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
        System.out.println(DELIMITER+"\n"+FIELDS+"\n"+DELIMITER);
        NassaContext.NASSA_CONTEXT.getSpaceships()
                .forEach(x -> System.out.printf(SHIP_DETAIL,x.getId(),x.getName(),x.getFlightDistance(), x.getIsBusy(), x.getiDMission().size(),x.getIsHasFailed()) );
        System.out.println(DELIMITER);
    }

    public void printDetailItem(Long id) {
        System.out.println(DELIMITER+"\n"+FIELDS+"\n"+DELIMITER);
        Spaceship spaceship = FindStarshipImpl.FIND_STARSHIP.findShipById(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createById(id));
        System.out.printf(SHIP_DETAIL,spaceship.getId(), spaceship.getName(), spaceship.getFlightDistance(), spaceship.getIsBusy(), spaceship.getiDMission().size(),spaceship.getIsHasFailed());

        spaceship.getiDMission().
                forEach(f -> System.out.printf(MISSION_DETAIL, MissionCrudImpl.MISSION_FACTORY.getPrintedMissionName(f)));
        System.out.println(DELIMITER);

    }
}
