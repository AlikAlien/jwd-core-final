package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Collection;
import java.util.Map;
// do the same for other entities
public class CrewMemberFactory /*implements EntityFactory<CrewMember>*/ {
    public static final CrewMemberFactory CREW_FACTORY = new CrewMemberFactory();
    private CrewMemberFactory () {}

    public CrewMemberCriteria create ( Role role, int num) {
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder(NassaContext.NASSA_CONTEXT.getCrewMembers())
                .byRole(role)
                .byNum(num)
                .byIsReady(true)
                .build();
        return crewMemberCriteria;
    }
}
