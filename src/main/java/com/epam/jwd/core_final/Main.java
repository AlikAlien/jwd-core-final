package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.exception.InvalidStateException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
        ApplicationProperties.APP_PROPERTIES.getCrewFileName();
        Application.start();
    }
}