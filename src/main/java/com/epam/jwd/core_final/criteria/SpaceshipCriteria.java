package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Collection;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Collection<Spaceship> spaceships;
    private Long flightDistance;
    private boolean isReadyForNextMissions;

    public static class Builder {
        private Collection<Spaceship> spaceships;
        private Long flightDistance = 0L;
        private boolean isReadyForNextMissions = true;

        public Builder(Collection<Spaceship> spaceships) {
            this.spaceships = spaceships;
        }

        public Builder spaceships(Collection<Spaceship> arg) {
            spaceships = arg;
            return this;
        }

        public Builder byDist(Long arg) {
            flightDistance = arg;
            return this;
        }

        public Builder byIsReady(boolean arg) {
            isReadyForNextMissions = arg;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }
    }

    private SpaceshipCriteria(Builder builder) {
        spaceships = builder.spaceships;
        flightDistance = builder.flightDistance;
        isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    public Collection<Spaceship> getSpaceships() {
        return spaceships;
    }

    public Long byDistance() {
        return flightDistance;
    }

    public boolean byIsReady() {
        return isReadyForNextMissions;
    }
}