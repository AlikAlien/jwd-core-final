package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContexStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

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
