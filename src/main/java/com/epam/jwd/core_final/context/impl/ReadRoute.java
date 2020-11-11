package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ReadEntity;
import com.epam.jwd.core_final.context.ReadRouteStrategy;

public class ReadRoute extends ReadEntity {
    public ReadRoute() {
        this.readContextStrategy = new ReadRouteStrategy();
    }
}
