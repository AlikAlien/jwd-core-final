package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;

public class SpaceshipFactory {
    public static final SpaceshipFactory SPACESHIP_FACTORY = new SpaceshipFactory();
    private SpaceshipFactory () {}

    public SpaceshipCriteria create (Long dist, boolean isready) {
        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria.Builder(NassaContext.NASSA_CONTEXT.getSpaceships())
                .byDist(dist)
                .byIsReady(isready)
                .build();

        return spaceshipCriteria;
    }
}
