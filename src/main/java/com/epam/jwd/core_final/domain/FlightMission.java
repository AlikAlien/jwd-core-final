package com.epam.jwd.core_final.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    String missionsName;
    //LocalDate startDate;
    LocalDateTime startDateTime; //additonal field, replace startDate
    //LocalDate endDate;
    LocalDateTime endDateTime; //additonal field, replace endDate
    Long distance;
    Spaceship assignedSpaceShift;
    List <CrewMember> assignedCrew;
    MissionResult missionResult;
    Route route;              //additional field

    public String getMissionsName() {
        return missionsName;
    }

    public void setMissionsName(String missionsName) {
        this.missionsName = missionsName;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime() {
        this.startDateTime = LocalDateTime.now();
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Long dist) {
        this.endDateTime = LocalDateTime.now().plusSeconds(dist/1000);
    }

    public void setEndDateTime() {
        this.endDateTime = LocalDateTime.now();
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public void setAssignedSpaceShift(Spaceship assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
