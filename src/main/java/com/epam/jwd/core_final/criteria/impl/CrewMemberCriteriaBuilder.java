package com.epam.jwd.core_final.criteria.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

public class CrewMemberCriteriaBuilder {
    public static final CrewMemberCriteriaBuilder CREW_CRITERIA_BUILDER = new CrewMemberCriteriaBuilder();
    private CrewMemberCriteriaBuilder () {}

    public CrewMemberCriteria create (Role role, int id) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                .byNum(id)
                .byIsReady(true)
                .build();
    }

    public CrewMemberCriteria createByName (String name) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byName(name)
                .build();
    }

    public CrewMemberCriteria createByRole (Role role) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                .build();
    }

    public CrewMemberCriteria createByRank (Rank rank) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRank(rank)
                .build();
    }

    public CrewMemberCriteria createById (Long id) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byId(id)
                .build();
    }

    public CrewMemberCriteria createByIsReady (boolean arg) {
        return new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byIsReady(arg)
                .build();
    }

}