package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */

    @Override
    public String getName() {
        return getName(this.id);
    }

    public static String getName(Long id) {
        String roleName = null;
        if (id == 1L) roleName = "SPECIALIST";
        if (id == 2L) roleName = "ENGINEER";
        if (id == 3L) roleName = "PILOT";
        if (id == 4L) roleName = "COMMANDER";
        if (roleName == null) System.out.println("incorrect iD");
        return roleName;
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */

    public static Role getRoleById(int id) {
        Role role;
        switch (id) {
            case 1:
                role = Role.MISSION_SPECIALIST;
                break;
            case 2:
                role = Role.FLIGHT_ENGINEER;
                break;
            case 3:
                role = Role.PILOT;
                break;
            case 4:
                role = Role.COMMANDER;
                break;
            default:
                throw new RuntimeException("Unknown index:" + id);
        }
        return role;
    }
}
