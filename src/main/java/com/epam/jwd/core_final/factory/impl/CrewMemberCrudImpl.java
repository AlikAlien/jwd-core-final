package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.FindCrewImpl;

import java.util.*;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

// do the same for other entities
public class CrewMemberCrudImpl {
    public static final CrewMemberCrudImpl CREW_FACTORY = new CrewMemberCrudImpl();
    private CrewMemberCrudImpl() {}
    final static String CREW_DETAIL = "ID#%1$-3d NAME:%2$-18s ROLE:%3$-18s RANK:%4$-14s STATUS:%5$s\n";
    final static String START_DELIMITER = "--------------------------------------- CREW MEMBERS --------------------------------- ";
    final static String END_DELIMITER   = "-------------------------------------------------------------------------------------- ";

    public List<CrewMember> create(Spaceship spaceship) throws NullPointerException {
        List<CrewMember> members = new ArrayList<>();
        Map<Role, Short> roleMap = spaceship.getCrew();
        boolean isEnough = true;
        for (Role role : roleMap.keySet()) {
            List<CrewMember> roles;
            roles = FindCrewImpl.FIND_CREW.findAllCrewMembersByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.create(role,roleMap.get(role)));
            if (roles == null) {
                isEnough = false;
                System.out.println(YELLOW + "WARNING: NOT ENOUGH " + role.getName() + "S FOR MISSION!" + RST);
            } else members.addAll(roles);
        }
        if (isEnough) return members;
        else return null;
    }

    public void printListAll() {
        System.out.println(START_DELIMITER);
        NassaContext.NASSA_CONTEXT.getCrewMembers()
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()));
        System.out.println(END_DELIMITER);
    }

    public void printListById(Long id) {
        System.out.println(START_DELIMITER);
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getId()==id)
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()));
        System.out.println(END_DELIMITER);
    }

    public void printListByName(String name) {
        List<CrewMember> crewMembers = FindCrewImpl.FIND_CREW.findAllCrewMembersByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByName(name));
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getName().contains(name))
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()));
    }

    public void printListByRole(Role role) {
        List<CrewMember> crewMembers = FindCrewImpl.FIND_CREW.findAllCrewMembersByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByRole(role));
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getRole().equals(role))
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()));
    }

    public void printListByRank(Rank rank) {
        List<CrewMember> crewMembers = FindCrewImpl.FIND_CREW.findAllCrewMembersByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByRank(rank));
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getRank().equals(rank))
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()));
    }
    public void printDetailItem(Long id) {
        System.out.println(START_DELIMITER);
        CrewMember crewMember = NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(f -> Objects.equals(f.getId(), id))
                .peek(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy()))
                .findFirst().get();

        crewMember.getiDMission().stream().
                forEach(f -> System.out.println("MISSIONS ID#" + f + " " + MissionCrudImpl.MISSION_FACTORY.getMissionName(f)));
        System.out.println(END_DELIMITER);
    }

    public Optional checkExist (Long id) {
        Optional crew = Optional.ofNullable(NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(f -> f.getId() == id)
                .findFirst().get());
        return  crew;
    }
}
