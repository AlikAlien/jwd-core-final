package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Role;

public class CrewMemberCriteriaBuilder {
    public static final CrewMemberCriteriaBuilder CREW_CRITERIA_BUILDER = new CrewMemberCriteriaBuilder();
    private CrewMemberCriteriaBuilder () {}

    public CrewMemberCriteria create (Role role, int num) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                .byNum(num)
                .byIsReady(true)
                .build();
        return crewMemberCriteria;
    }
}
