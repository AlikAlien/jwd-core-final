package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.util.LoggerImpl;

public class InvalidStateException extends Exception {
    // todo
    public InvalidStateException (String msg) {
        super(msg);
        LoggerImpl.LOGGER.error(msg);
    }
}
