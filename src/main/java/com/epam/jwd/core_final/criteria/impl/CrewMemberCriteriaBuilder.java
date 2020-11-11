package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

public class CrewMemberCriteriaBuilder {
    public static final CrewMemberCriteriaBuilder CREW_CRITERIA_BUILDER = new CrewMemberCriteriaBuilder();
    private CrewMemberCriteriaBuilder () {}

    public CrewMemberCriteria create (Role role, int id) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                //.byRank(rank)
                //.byName(name)
                .byId(id)
                .byIsReady(true)
                .build();
        return crewMemberCriteria;
    }

    public CrewMemberCriteria createByName (String name) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byName(name)
                .build();
        return crewMemberCriteria;
    }

    public CrewMemberCriteria createByRole (Role role) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                .build();
        return crewMemberCriteria;
    }

    public CrewMemberCriteria createByRank (Rank rank) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRank(rank)
                .build();
        return crewMemberCriteria;
    }
    public CrewMemberCriteria createById (int id) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byId(id)
                .build();
        return crewMemberCriteria;
    }
}
