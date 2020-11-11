package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Collection;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Collection<CrewMember> crewMembers;
    private Role role;
    private Rank rank;
    private String name;
    private int num;
    private boolean isReady;

    public static class Builder {
        private Collection<CrewMember> crewMembers;
        private Role role;
        private Rank rank;
        private String name;
        private int id;
        private boolean isReady = true;

        public Builder(Collection<CrewMember> crewMembers) {
            this.crewMembers = crewMembers;
        }

        public CrewMemberCriteria.Builder crewMembers(Collection<CrewMember> arg) {
            crewMembers = arg;
            return this;
        }

        public CrewMemberCriteria.Builder byRank(Rank arg) {
            rank = arg;
            return this;
        }

        public CrewMemberCriteria.Builder byName(String arg) {
            name = arg;
            return this;
        }

        public CrewMemberCriteria.Builder byRole(Role arg) {
            role = arg;
            return this;
        }

        public CrewMemberCriteria.Builder byId(int arg) {
            id = arg;
            return this;
        }

        public CrewMemberCriteria.Builder byIsReady(boolean arg) {
            isReady = arg;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }
    }

    private CrewMemberCriteria(CrewMemberCriteria.Builder builder) {
        crewMembers = builder.crewMembers;
        num = builder.id;
        role = builder.role;
        isReady = builder.isReady;
    }

    public Collection<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public Role byRole() {
        return role;
    }

    public Rank byRank() {
        return rank;
    }

    public String byName() {
        return name;
    }

    public int byId() {
        return num;
    }

    public boolean byIsReady() {
        return isReady;
    }
}
