package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.impl.RouteCriteriaBuilder;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.service.impl.FindRouteImpl;

import java.util.Optional;

public class RouteCrudImpl {
    public static final RouteCrudImpl ROUTE_CRUD = new RouteCrudImpl();

    private RouteCrudImpl() {
    }

    public Route create(Long id) {
        Route route;
        route = FindRouteImpl.FIND_ROUTE.findRouteByCriteria(
                RouteCriteriaBuilder.ROUTE_CRITERIA.create(id));
        return route;
    }

    public Optional checkExist(Long id) {
        Optional route = FindRouteImpl.FIND_ROUTE.findExistRouteByCriteria(RouteCriteriaBuilder.ROUTE_CRITERIA.create(id));
        return route;
    }

    public BaseEntity create(AbstractBaseEntity obj) {
        return null;
    }
}
