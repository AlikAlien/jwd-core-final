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
    final static String CREW_DETAIL = "%1$-3d  %2$-18s  %3$-19s   %4$-15s   %5$s        %6$-3d\n";
    final static String DELIMITER       = "---------------------------------------------------------------------------------\n";
    final static String FIELDS          = "ID#     NAME                ROLE                  RANK          STATUS   MISSIONS\n";

    public List<CrewMember> create(Spaceship spaceship) throws NullPointerException {
        List<CrewMember> members = new ArrayList<>();
        Map<Role, Short> roleMap = spaceship.getCrew();
        boolean isEnough = true;
        for (Role role : roleMap.keySet()) {
            List<CrewMember> roles;
            roles = FindCrewImpl.FIND_CREW.findCrewByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.create(role,roleMap.get(role)));
            if (roles == null) {
                isEnough = false;
                System.out.println(YELLOW + "WARNING: NOT ENOUGH " + role.getName() + "S FOR MISSION!" + RST);
            }
            else members.addAll(roles);
        }
        if (isEnough) return members;
        else return null;
    }

    public void printListAll() {
        System.out.println(DELIMITER+FIELDS+DELIMITER);
        NassaContext.NASSA_CONTEXT.getCrewMembers()
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy(), x.getMissionsList().size()));
        System.out.println(DELIMITER);
    }

    public void printListByName(String name) {
        System.out.print(DELIMITER+FIELDS+DELIMITER);
        FindCrewImpl.FIND_CREW.findCrewByName(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByName(name)).stream()
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy(), x.getMissionsList().size()));
        System.out.println(DELIMITER);
    }

    public void printListByRole(Role role) {
        System.out.print(DELIMITER+FIELDS+DELIMITER);
        FindCrewImpl.FIND_CREW.findCrewByRole(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByRole(role)).stream()
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy(), x.getMissionsList().size()));
        System.out.println(DELIMITER);
    }

    public void printListByRank(Rank rank) {
        System.out.print(DELIMITER+FIELDS+DELIMITER);
        FindCrewImpl.FIND_CREW.findCrewByRank(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByRank(rank)).stream()
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy(), x.getMissionsList().size()));
        System.out.println(DELIMITER);
    }

    public void printListByStatus(Boolean status) {
        List<CrewMember> crewMembers = FindCrewImpl.FIND_CREW.findCrewByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createByIsReady(status));
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(x->x.getIsBusy().equals(status))
                .forEach(x -> System.out.printf(CREW_DETAIL,x.getId(), x.getName(), x.getRole(), x.getRank(), x.getIsBusy(), x.getMissionsList().size()));
    }

    public void printDetailItem(Long id) {
        System.out.print(DELIMITER+FIELDS+DELIMITER);
        CrewMember crewMember = FindCrewImpl.FIND_CREW.findCrewById(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.createById(id));
        System.out.printf(CREW_DETAIL,crewMember.getId(), crewMember.getName(), crewMember.getRole(), crewMember.getRank(), crewMember.getIsBusy(),crewMember.getMissionsList().size());
        System.out.print(DELIMITER+"\n");
        crewMember.getMissionsList().stream()
                .forEach(f -> System.out.print("MISSION:  "+MissionCrudImpl.MISSION_FACTORY.getPrintedMissionName(f)+"\n"));
    }

    public Optional checkExist (Long id) {
        Optional crew = Optional.ofNullable(NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(f -> f.getId() == id)
                .findFirst().get());
        return  crew;
    }
}
