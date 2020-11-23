package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.*;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

public class FindStarshipImpl implements SpaceshipService {
    public static final FindStarshipImpl FIND_STARSHIP = new FindStarshipImpl();

    private FindStarshipImpl() {
    }

    public Spaceship findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {            //used Optional for find
        List<Spaceship> spaceships = new ArrayList<>(criteria.getSpaceships());
        Collections.shuffle(spaceships);
        Spaceship spaceship = spaceships.stream()
                .filter(f -> (f.getFlightDistance() > criteria.byDistance()) && f.getisReadyForNextMissions() == criteria.byIsReady())
                .min(Comparator.comparing(Spaceship::getFlightDistance)).get();     //min FlightDistance
                //.limit(1).findAny().get();                                        //Random manner
        if (spaceship == null) System.out.println(YELLOW + "from find -NOT FREE SPACESHIP FOR ROUTE#" + RST);
        return spaceship;
    }

    public Spaceship findShipById(CrewMemberCriteria criteria) {
        return NassaContext.NASSA_CONTEXT.getSpaceships().stream()
                .filter(f -> Objects.equals(f.getId(), criteria.byId()))
                .findFirst().get();
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return null;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}