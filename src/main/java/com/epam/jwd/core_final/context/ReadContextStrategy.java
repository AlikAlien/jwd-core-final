package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public interface ReadContextStrategy {
    Collection <? extends AbstractBaseEntity> readEntityList (String filePath) throws IOException;
}
