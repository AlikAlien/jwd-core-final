package com.epam.jwd.core_final.sql;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReadObject {
    public static final ReadObject READ_SQL = new ReadObject();

    private ReadObject() {
    }

    public Collection<Route> routesFromSQL(ResultSet rs) throws SQLException {
        //final String sqlRoutes = "select idRoute, nameRoute, distanceRoute from routes";
        Collection<Route> routes = new ArrayList<>();
        while (rs.next()) {
            Route route = new Route();
            route.setIdRoute(rs.getInt(1));
            route.setName(rs.getString(2));
            route.setRouteDistance(rs.getLong(3));
            routes.add(route);
        }
        return routes;
    }

    public Collection<Spaceship> spaceshipsFromSQL(ResultSet rs) throws SQLException {
        //final String sqlSpaceship = "select idSpaceship, nameSpaceship, range, status, role1..,2,3,4  from Spaceships";
        Collection<Spaceship> spaceships = new ArrayList<>();
        while (rs.next()) {
            Spaceship spaceship = new Spaceship();
            spaceship.setId(rs.getLong(1));
            spaceship.setName(rs.getString(2));
            spaceship.setFlightDistance(rs.getLong(3));
            spaceship.setReadyForNextMissions(rs.getBoolean(4));

            Map<Role, Short> mapship = new HashMap<>();
            for (int i = 1; i < 5; i++) mapship.put(Role.getRoleById(i), rs.getShort(i+4));

            spaceship.setCrew(mapship);
            spaceships.add(spaceship);
        }
        return spaceships;
    }

    public Collection<CrewMember> crewMembersFromSQL(ResultSet rs) throws SQLException {
        //final String sqlCrewMember = "select idCrewMembers, nameCrewMembers, roleId, rankId, status from CrewMembers";
        Collection<CrewMember> crewMembers = new ArrayList<>();
        while (rs.next()) {
            CrewMember crewMember = new CrewMember();
            crewMember.setId(rs.getLong(1));
            crewMember.setName(rs.getString(2));
            crewMember.setRole(rs.getInt(3));
            crewMember.setRank(rs.getInt(4));
            crewMember.setReadyForNextMissions(rs.getBoolean(5));
            crewMembers.add(crewMember);
        }
        return crewMembers;
    }
}
