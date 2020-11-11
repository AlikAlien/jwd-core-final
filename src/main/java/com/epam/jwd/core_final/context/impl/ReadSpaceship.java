package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ReadEntity;
import com.epam.jwd.core_final.context.ReadSpaceshipStrategy;

public class ReadSpaceship extends ReadEntity {
    public ReadSpaceship() {
        this.readContextStrategy = new ReadSpaceshipStrategy();
    }
}