package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.*;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;
// todo
public class NassaContext implements ApplicationContext {
    public static final NassaContext NASSA_CONTEXT = new NassaContext(); // change private to public
    public NassaContext(){}

    // no getters/setters for them //temporaly
    private Collection <CrewMember> crewMembers = new ArrayList<>();
    private Collection <Spaceship> spaceships = new ArrayList<>();
    private Collection <Route> routes = new ArrayList<>();

    ApplicationContexStrategy applicationContexStrategy = new ApplicationContexStrategy() {
    @Override
    public void readBaseEntityList(String filePath) {}
    };

    @Override
    public <T extends BaseEntity> Collection <T> retrieveBaseEntityList (Class <T> tClass, String inputFile) throws IOException {
        /**
         * You have to read input files, populate collections
         * @throws InvalidStateException
         */
        String filePath = APP_PROPERTIES.getInputRootDir() + inputFile;
        ReadCrewStrategy readCrewStrategy = new ReadCrewStrategy();
        ReadSpaceShipStrategy readSpaceShipStrategy = new ReadSpaceShipStrategy();
        ReadRouteStrategy readRouteStrategy = new ReadRouteStrategy();
        if (tClass == CrewMember.class) crewMembers = readCrewStrategy.readBaseEntityList(filePath);
        if (tClass == Spaceship.class) spaceships = readSpaceShipStrategy.readBaseEntityList(filePath);
        if (tClass == Route.class) routes = readRouteStrategy.readBaseEntityList(filePath);
        //crewMembers.stream().forEach (x-> System.out.println("id = "+x.getId()+" Name = " + x.getName()));
        //spaceships.stream().forEach (x-> System.out.println("id = "+x.getId()+" Name = " + x.getCrew()));
        //routes.stream().forEach (x-> System.out.println("idRoute = "+x.getIdRoute()+" Name = " + x.getName()));
        return null;
    }

    @Override
    public void init() throws InvalidStateException, IOException {
        retrieveBaseEntityList(CrewMember.class, APP_PROPERTIES.getCrewFileName());
        retrieveBaseEntityList(Spaceship.class, APP_PROPERTIES.getSpaceshipsFileName());
        retrieveBaseEntityList(Route.class, APP_PROPERTIES.getRouteFileName());
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