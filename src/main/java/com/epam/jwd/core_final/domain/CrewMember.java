package com.epam.jwd.core_final.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    Role role;
    //String nameCrew;
    String rank;
    boolean isReadyForNextMissions = true;
    List <Long> iDMission = new ArrayList<>();

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRank() {
        return Integer.valueOf(rank);
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
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
}
