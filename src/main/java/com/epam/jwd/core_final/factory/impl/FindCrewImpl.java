package com.epam.jwd.core_final.factory.impl;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindCrewImpl {
    public static final FindCrewImpl FIND_CREW = new FindCrewImpl();
    private FindCrewImpl(){}

    public List <CrewMember> findCrew (CrewMemberCriteria criteria){            //used Optional for criteria
        Long limitCapacity = (long) Math.ceil(criteria.byNum()*MissionFactory.MISSION_FACTORY.capacityCrew);
        List <CrewMember> crewMembers =  new ArrayList<>(criteria.getCrewMembers());
        Collections.shuffle(crewMembers);
        List <CrewMember> crewMembersRnd = crewMembers.stream()
                .filter (f -> f.getRole().equals(criteria.byRole()))
                .filter (f -> f.isReadyForNextMissions() == criteria.byIsReady())
                .limit(limitCapacity)
                .collect (Collectors.toCollection (ArrayList::new));
        if (crewMembersRnd.size() < limitCapacity) {
            return null;
        }
        return crewMembersRnd;
    }
}