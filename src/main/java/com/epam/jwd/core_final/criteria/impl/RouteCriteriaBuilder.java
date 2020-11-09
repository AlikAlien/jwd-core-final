package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.RouteCriteria;

public class RouteCriteriaBuilder {
    public static final RouteCriteriaBuilder ROUTE_CRITERIA = new RouteCriteriaBuilder();
    private RouteCriteriaBuilder () {}

    public RouteCriteria create (Long id) {
        RouteCriteria routeCriteria = new RouteCriteria.Builder(NassaContext.NASSA_CONTEXT.getRoutes())
                .byId(id)
                .build();
        return routeCriteria;
    }
}
