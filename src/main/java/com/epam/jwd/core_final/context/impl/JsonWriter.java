package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class JsonWriter {
    public static final JsonWriter JSON_WRITER = new JsonWriter();
    private JsonWriter(){};
        public boolean jsonWriter(String file){
            String filePath = APP_PROPERTIES.getOutputRootDir() + file;
            ObjectMapper mapper = new ObjectMapper();

            try {
                mapper.writeValue(new File(filePath), MissionFactory.MISSION_FACTORY.getFlightMissions());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
    }
}
