package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FindCrewImpl {
    public static final FindCrewImpl FIND_CREW = new FindCrewImpl();
    private FindCrewImpl(){}

    public List <CrewMember> findCrew (CrewMemberCriteria criteria){            //used Optional for criteria
        //Optional<> spaceship = Optional.ofNullable(criteria.getFigure());

        List <CrewMember> crewMembers =
                criteria.getCrewMembers().stream()
                .filter (f -> f.getRole().equals(criteria.byRole()))
                .filter (f -> f.isReadyForNextMissions() == criteria.byIsReady())
                .limit(criteria.byNum())
                .peek(f -> f.setReadyForNextMissions(false))
                .collect (Collectors.toCollection (ArrayList::new));

        return crewMembers;
    }
}