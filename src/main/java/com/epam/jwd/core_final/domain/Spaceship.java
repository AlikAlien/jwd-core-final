package com.epam.jwd.core_final.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    public Map <Role, Short> crew ;
    Long flightDistance;
    List<Long> iDMission = new ArrayList<>();
    boolean isReadyForNextMissions = true;
    boolean hasFailedMissions = false;

    public Map  < Role, Short>  getCrew() {
        return crew;
    }

    public void setCrew (Map <Role, Short> crew) {
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public boolean getisReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public void setHasFailedMissions(boolean hasFailedMissions) {
        this.hasFailedMissions = hasFailedMissions;
    }

    public boolean isHasFailedMissions() {
        return hasFailedMissions;
    }

    public List<Long> getiDMission() {
        return iDMission;
    }

    public void addiDMission(Long id) {
        this.iDMission.add(id);
    }

    public String getIsBusy(){
        String isBusy;
        if (isReadyForNextMissions) isBusy = "FREE";
        else isBusy ="BUSY";
        return isBusy;
    }
    public String getIsHasFailed(){
        String status;
        if (hasFailedMissions) status = "YES,HAS FAILED";
        else status ="NO,ONLY SUCCESSFUL";
        return status;
    }
}
