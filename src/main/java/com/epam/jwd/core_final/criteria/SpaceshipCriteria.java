package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.ArrayList;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    String type;
    int num;
    int index;

    public static class Builder {
        String type = "*";
        int num = 0;
        int index = 0;

        public Builder byType(String arg) {
            type = arg;
            return this;
        }

        public Builder byNumPoints(int arg) {
            num = arg;
            return this;
        }

        public Builder byIndex(int arg) {
            index = arg;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }
    }

    private SpaceshipCriteria (Builder builder) {
        type = builder.type;
        num = builder.num;
        index = builder.index;

    }

    public String getType() {
        return type;
    }
    public int getNumPoints() {
        return num;
    }

    public int getIndex() {
        return index;
    }
}
