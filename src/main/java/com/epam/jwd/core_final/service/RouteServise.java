package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Route;

public interface RouteServise {

    Route findAllRouteByCriteria(Criteria<? extends Route> criteria);
}
