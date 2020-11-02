package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContexStrategy;
import com.epam.jwd.core_final.context.ReadCrewStrategy;
import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class ReadCrew extends NassaContext implements ApplicationContexStrategy {
    public ReadCrew() throws FileNotFoundException, IOException {
    }

    @Override
    public void readBaseEntityList( String filePath){
        this.applicationContexStrategy = new ApplicationContexStrategy() {
            @Override
            public void readBaseEntityList(String filePath) {

            }
        };
    }
}
