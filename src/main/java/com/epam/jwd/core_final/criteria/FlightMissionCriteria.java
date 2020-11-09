package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;

import java.util.Collection;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private Collection <FlightMission> missions;
    private Long id;
    MissionResult result;

    public static class Builder {
        private Collection <FlightMission> missions;
        private Long id;
        MissionResult result = null;

        public Builder(Collection <FlightMission> missions) {
            this.missions = missions;
        }

        public FlightMissionCriteria.Builder routes(Collection <FlightMission> arg) {
            missions = arg;
            return this;
        }

        public FlightMissionCriteria.Builder byId(Long arg) {
            id = arg;
            return this;
        }

        public FlightMissionCriteria.Builder byResult (MissionResult arg){
            result = arg;
            return this;
        }

        public FlightMissionCriteria build () {
            return new FlightMissionCriteria(this);
        }
    }

    private FlightMissionCriteria (FlightMissionCriteria.Builder builder) {
        missions = builder.missions;
        result = builder.result;
        id = builder.id;
    }

    public Collection <FlightMission> getMissions() {
        return missions;
    }

    public MissionResult byResult() {
        return result;
    }

    public Long byId () {
        return id;
    }
}
