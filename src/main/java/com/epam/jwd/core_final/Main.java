package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.util.ApplicationProperties;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationProperties.APP_PROPERTIES.getCrewFileName();
        Application.start();
    }
}