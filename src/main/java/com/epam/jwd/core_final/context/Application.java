package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.Mission;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import java.io.IOException;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException, IOException {
        final Supplier <ApplicationContext> applicationContextSupplier = null; // todo
        //final NassaContext nassaContext = new NassaContext();
        NassaContext.NASSA_CONTEXT.init();
        //nassaContext.init();
        NassaMenu applicationMenu = new NassaMenu();
        applicationMenu.getApplicationContext();
        return null;//applicationContextSupplier::get;
    }
}
