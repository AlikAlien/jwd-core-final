package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;

public class FindMissionImpl implements MissionService {

    public static final FindMissionImpl FIND_MISSION = new FindMissionImpl();
    private FindMissionImpl(){}

    public Optional findExistByCriteria (FlightMissionCriteria criteria){
        Optional mission = Optional.ofNullable(MissionCrudImpl.MISSION_FACTORY.getFlightMissions().stream()
                .filter(f -> f.getId() == criteria.byId())
                .findFirst().get());
        return  mission;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return null;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return null;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return Optional.empty();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        return null;
    }
}
