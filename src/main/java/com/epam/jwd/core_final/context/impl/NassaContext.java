package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.*;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import static com.epam.jwd.core_final.context.impl.NassaMenu.*;
import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

// todo
public class NassaContext implements ApplicationContext {
    public static final NassaContext NASSA_CONTEXT = new NassaContext(); // change private to public

    public NassaContext() {
    }

    // no getters/setters for them //temp
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Route> routes = new ArrayList<>();
    public DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(APP_PROPERTIES.getDateTimeFormat());
    public DateTimeFormatter dateFileFormat = DateTimeFormatter.ofPattern(APP_PROPERTIES.getDateTimeFileFormat());


    @Override
    public void init() throws IOException {
        retrieveBaseEntityList(CrewMember.class, APP_PROPERTIES.getCrewFileName());
        retrieveBaseEntityList(Spaceship.class, APP_PROPERTIES.getSpaceshipsFileName());
        retrieveBaseEntityList(Route.class, APP_PROPERTIES.getRouteFileName());
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass, String inputFile) throws IOException {
        /**
         * You have to read input files, populate collections
         * @throws InvalidStateException
         */
        String filePath = APP_PROPERTIES.getInputRootDir() + inputFile;
        //Strategy Pattern
        try {
            if (tClass == CrewMember.class) {
                ReadEntity readCrew = new ReadCrew();
                crewMembers = (Collection<CrewMember>) readCrew.readEntityList(filePath);
            }
            if (tClass == Spaceship.class) {
                ReadEntity readSpaceship = new ReadSpaceship();
                spaceships = (Collection<Spaceship>) readSpaceship.readEntityList(filePath);
            }

            if (tClass == Route.class) {
                ReadEntity readRoute = new ReadRoute();
                routes = (Collection<Route>) readRoute.readEntityList(filePath);
            }

        } catch (IOException e) {
            System.out.println(RED + "FILE ENTITY NOT FOUND " + e.getMessage() + RST);
            System.exit(0);
        }
        return null;
    }

    public Collection<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public Collection<Spaceship> getSpaceships() {
        return spaceships;
    }

    public Collection<Route> getRoutes() {
        return routes;
    }
}