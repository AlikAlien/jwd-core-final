package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.RouteCriteria;
import com.epam.jwd.core_final.domain.Route;

import java.util.Optional;

public class FindRouteImpl {
    public static final FindRouteImpl FIND_ROUTE = new FindRouteImpl();

    private FindRouteImpl() {
    }

    public Route findRouteByCriteria(RouteCriteria criteria) {            //used Optional for criteria by Figure
        return NassaContext.NASSA_CONTEXT.getRoutes().stream()
                .filter(f -> f.getIdRoute() == criteria.byId())
                .findFirst().get();
    }

    public Optional findExistRouteByCriteria(RouteCriteria criteria) {
        Optional route = Optional.ofNullable(NassaContext.NASSA_CONTEXT.getRoutes().stream()
                .filter(f -> f.getIdRoute() == criteria.byId())
                .findFirst().get());
        return route;
    }

}
