package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.util.Logger;

public class InvalidStateException extends Exception {
    // todo
    public InvalidStateException (String msg) {
        super(msg);
        System.out.println(msg);
        //Logger.LOGGER.error(msg);
    }
}
