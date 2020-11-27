package com.epam.jwd.core_final.sql;

public enum SQLTable {
    ROUTES,
    SPACESHIPS,
    CREWMEMBERS;

    final static String sqlRoutes = "select idRoute, nameRoute, distanceRoute from routes";
    final static String sqlCrewMember = "select idCrewMember, nameCrewMember, roleId, rankId, status from CrewMembers";
    final static String sqlSpaceship = "select `idSpaceship`, `nameSpaceship`, `range`, status, `numRole1`, `numRole2`, `numRole3`,`numRole4`   from spaceships";

    public static String getSqlRoutes() {
        return sqlRoutes;
    }

    public static String getSqlSpaceship() {
        return sqlSpaceship;
    }

    public static String getSqlCrewMember() {
        return sqlCrewMember;
    }
}
