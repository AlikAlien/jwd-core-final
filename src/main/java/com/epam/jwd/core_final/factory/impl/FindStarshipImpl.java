package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;

public class FindStarshipImpl {
    public static final FindStarshipImpl FIND_STARSHIP = new FindStarshipImpl();
    private FindStarshipImpl(){}

    public Spaceship findStarship (SpaceshipCriteria criteria){            //used Optional for criteria by Figure
        Spaceship spaceship = criteria.getSpaceships().stream()
                .filter (f -> f.getFlightDistance() > criteria.byDistance())
                .filter (f -> f.getisReadyForNextMissions() == criteria.byIsReady())
                .findFirst().get();

        //list.stream().forEach(s->System.out.println(s));   // test for new Array List
        //if(list.size()==0) Logger.LOGGER.info ("FINDbyCriteria: Search result - not found");
        return spaceship;
    }
}