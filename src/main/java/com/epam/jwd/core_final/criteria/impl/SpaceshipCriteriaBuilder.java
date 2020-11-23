package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;

public class SpaceshipCriteriaBuilder {
    public static final SpaceshipCriteriaBuilder SPACESHIP_CRITERIA = new SpaceshipCriteriaBuilder();
    private SpaceshipCriteriaBuilder () {}

    public SpaceshipCriteria create (Long dist, boolean isReady) {
        return new SpaceshipCriteria.Builder(NassaContext.NASSA_CONTEXT.getSpaceships())
                .byDist(dist)
                .byIsReady(isReady)
                .build();
    }
    public SpaceshipCriteria createById (Long id) {
        return new SpaceshipCriteria.Builder(NassaContext.NASSA_CONTEXT.getSpaceships())
                .byId(id)
                .build();
    }
}
