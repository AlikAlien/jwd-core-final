package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.util.ApplicationProperties;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.util.LoggerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class JsonWriter extends NassaMenu{
    public static final JsonWriter JSON_WRITER = new JsonWriter();

    private JsonWriter() {
    }

    public void nassaSave() {
        String fileDir;
        String fileName;
        fileDir = ApplicationProperties.APP_PROPERTIES.getOutputRootDir();
        fileName = ApplicationProperties.APP_PROPERTIES.getMissionsFileName() + NassaContext.NASSA_CONTEXT.dateFileFormat.format(LocalDateTime.now());
        File dir = new File(fileDir);
        File file = new File(fileDir + fileName);
        try {
            dir.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
        LoggerImpl.LOGGER.info("SAVED LIST MISSIONS TO FILE: " + fileName);
        System.out.println(GREEN+"SAVED LIST MISSIONS TO FILE: " + fileName+RST);
        JsonWriter.JSON_WRITER.jsonWriter(file);
    }

    public void jsonWriter(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, MissionCrudImpl.MISSION_FACTORY.getFlightMissions());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
