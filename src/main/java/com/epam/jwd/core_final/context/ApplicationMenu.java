package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.exception.BadInputException;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext() throws BadInputException;

    default int handleUserInput(int i) {
        return 0;
    }
}