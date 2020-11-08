package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindStarshipImpl {
    public static final FindStarshipImpl FIND_STARSHIP = new FindStarshipImpl();
    private FindStarshipImpl(){}

    public Spaceship findStarship (SpaceshipCriteria criteria){            //used Optional for criteria by Figure
        List<Spaceship> spaceships =  new ArrayList<>(criteria.getSpaceships());
        Collections.shuffle(spaceships);
        Spaceship spaceship = spaceships.stream()
                .filter (f -> f.getFlightDistance() > criteria.byDistance())
                .filter (f -> f.getisReadyForNextMissions() == criteria.byIsReady())
                .findFirst().get();
        return spaceship;
    }
}