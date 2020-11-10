package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.io.IOException;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws IOException {
        final Supplier <ApplicationContext> applicationContextSupplier = null; // todo
        LoggerImpl.LOGGER.info("APPLICATION START..");
        NassaContext.NASSA_CONTEXT.init();
        NassaMenu applicationMenu = new NassaMenu();
        applicationMenu.getApplicationContext();
        return null;//applicationContextSupplier::get;
    }
}
