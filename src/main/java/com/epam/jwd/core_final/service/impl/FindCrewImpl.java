package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.impl.MissionCrudImpl;
import com.epam.jwd.core_final.service.CrewService;

import java.util.*;
import java.util.stream.Collectors;

public class FindCrewImpl implements CrewService {
    public static final FindCrewImpl FIND_CREW = new FindCrewImpl();

    private FindCrewImpl() {
    }

    public List<CrewMember> findCrewByCriteria(CrewMemberCriteria criteria) {            //used Optional for criteria
        Long limitCapacity = (long) Math.ceil(criteria.byNum() * MissionCrudImpl.MISSION_FACTORY.capacityCrew);
        List<CrewMember> crewMembers = new ArrayList<>(criteria.getCrewMembers());
        Collections.shuffle(crewMembers);
        List<CrewMember> crewMembersRnd = crewMembers.stream()
                .filter(f -> f.getRole().equals(criteria.byRole()))
                .filter(f -> f.isReadyForNextMissions() == criteria.byIsReady())
                .limit(limitCapacity)
                .collect(Collectors.toCollection(ArrayList::new));
        if (crewMembersRnd.size() < limitCapacity) {
            return null;
        }
        return crewMembersRnd;
    }

    public List<CrewMember> findCrewByName(CrewMemberCriteria criteria) {
        return criteria.getCrewMembers().stream()
                .filter(f -> criteria.byName().equals("*")||f.getName().contains(criteria.byName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CrewMember> findCrewByRole(CrewMemberCriteria criteria) {
        return criteria.getCrewMembers().stream()
                .filter(f -> f.getRole().equals(criteria.byRole()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CrewMember> findCrewByRank(CrewMemberCriteria criteria) {
        return criteria.getCrewMembers().stream()
                .filter(f -> f.getRank().equals(criteria.byRank()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public CrewMember findCrewById(CrewMemberCriteria criteria) {
        return criteria.getCrewMembers().stream()
                .filter(f -> Objects.equals(f.getId(), criteria.byId()))
                .findFirst().get();
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return null;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return null;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {

    }

    @Override
    public CrewMember createCrewMember(CrewMember spaceship) throws RuntimeException {
        return null;
    }

}