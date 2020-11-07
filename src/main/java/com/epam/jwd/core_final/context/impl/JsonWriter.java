package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.util.LoggerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class JsonWriter {
    public static final JsonWriter JSON_WRITER = new JsonWriter();
    private JsonWriter(){};

    public void nassaSave() {
        String fileDir;
        String fileName ;
        fileDir = ApplicationProperties.APP_PROPERTIES.getOutputRootDir();
        fileName =  ApplicationProperties.APP_PROPERTIES.getMissionsFileName() + NassaContext.NASSA_CONTEXT.dateFileFormat.format(LocalDateTime.now());
        File dir = new File(fileDir);
        File file = new File(fileDir + fileName);
        dir.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggerImpl.LOGGER.info("SAVED LIST MISSIONS TO FILE: " +fileName);
        JsonWriter.JSON_WRITER.jsonWriter(file);
    }

    public boolean jsonWriter(File file){
         ObjectMapper mapper = new ObjectMapper();
         try {
             mapper.writeValue(file, MissionFactory.MISSION_FACTORY.getFlightMissions());
         } catch (IOException e) {
            e.printStackTrace();
         }
         return false;
    }
}
