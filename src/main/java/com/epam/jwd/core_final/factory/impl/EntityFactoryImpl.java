package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Route;
import com.epam.jwd.core_final.factory.EntityType;

public class EntityFactoryImpl {
    public static final EntityFactoryImpl FACTORY = new EntityFactoryImpl();

    private EntityFactoryImpl() {
    }

    public AbstractBaseEntity create(AbstractBaseEntity obj, EntityType type) {
        AbstractBaseEntity entity = null;
        switch (type) {
            case MISSION:
                entity = MissionCrudImpl.MISSION_FACTORY.create(obj);
                break;
            case SPACESHIP:
                entity = SpaceshipCrudImpl.SPACESHIP_FACTORY.create(obj);
                break;
            default:
                throw new IllegalArgumentException("Wrong entity type:" + type);
        }
        return entity;
    }
}
