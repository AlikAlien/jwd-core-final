package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.RouteCriteria;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;

public class FlightMissionCriteriaBuilder {
    public static final FlightMissionCriteriaBuilder MISSION_CRITERIA = new FlightMissionCriteriaBuilder();
    private FlightMissionCriteriaBuilder() {}

    public FlightMissionCriteria create (Long id) {
        FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria.Builder(MissionCrudImpl.MISSION_FACTORY.getFlightMissions())
                .byId(id)
                .build();
        return flightMissionCriteria;
    }
}
