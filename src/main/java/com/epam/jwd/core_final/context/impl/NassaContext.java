package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContexStrategy;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ReadCrewStrategy;
import com.epam.jwd.core_final.context.ReadSpaceShipStrategy;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;
// todo
public class NassaContext implements ApplicationContext {
    // no getters/setters for them
    private Collection <CrewMember> crewMembers = new ArrayList<>();
    private Collection <Spaceship> spaceships = new ArrayList<>();

    public NassaContext() throws FileNotFoundException, IOException {}

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
        if (tClass== CrewMember.class) crewMembers = readCrewStrategy.readBaseEntityList(filePath);
        if (tClass== Spaceship.class) spaceships = readSpaceShipStrategy.readBaseEntityList(filePath);
        crewMembers.stream().forEach (x-> System.out.println("Name = " + x.getName()));
        spaceships.stream().forEach (x-> System.out.println("Name = " + x.getName()));
        return null;
    }

    @Override
    public void init() throws InvalidStateException, IOException {
        retrieveBaseEntityList(CrewMember.class, APP_PROPERTIES.getCrewFileName());
        retrieveBaseEntityList(Spaceship.class, APP_PROPERTIES.getSpaceshipsFileName());
    }
}