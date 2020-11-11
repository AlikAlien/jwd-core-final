package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;

import java.io.IOException;
import java.util.Collection;

public class ReadEntity {
    public ReadContextStrategy readContextStrategy;

    public Collection<? extends AbstractBaseEntity> readEntityList (String s) throws IOException {
    return readContextStrategy.readEntityList(s);
    }
}
