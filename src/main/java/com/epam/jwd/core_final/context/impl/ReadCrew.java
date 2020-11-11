package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ReadCrewStrategy;
import com.epam.jwd.core_final.context.ReadEntity;

public class ReadCrew extends ReadEntity {
    public ReadCrew() {
        this.readContextStrategy = new ReadCrewStrategy();
    }
}
