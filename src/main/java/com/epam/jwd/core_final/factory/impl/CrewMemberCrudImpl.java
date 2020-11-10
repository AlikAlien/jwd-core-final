package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.FindCrewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.jwd.core_final.context.impl.NassaMenu.RST;
import static com.epam.jwd.core_final.context.impl.NassaMenu.YELLOW;

// do the same for other entities
public class CrewMemberCrudImpl {
    public static final CrewMemberCrudImpl CREW_FACTORY = new CrewMemberCrudImpl();
    private CrewMemberCrudImpl() {}

    public List <CrewMember> create (Map <Role, Short> roleMap ) throws NullPointerException{
        List <CrewMember> members = new ArrayList<>();
        boolean isEnough = true;
        for (Role role: roleMap.keySet()){
            List <CrewMember> roles;
            roles = FindCrewImpl.FIND_CREW.findAllCrewMembersByCriteria(CrewMemberCriteriaBuilder.CREW_CRITERIA_BUILDER.create(role, roleMap.get(role)));
            if (roles == null){
                isEnough = false;
                System.out.println(YELLOW+"WARNING: NOT ENOUGH " +role.getName()+ "S FOR MISSION!"+RST);
            }
            else members.addAll(roles);
        }
        if (isEnough) return members;
        else return null;
    }

    public void printListAll(){
        System.out.println("-------------------------LIST OF ALL CREW MEMBERS------------------------------------- ");
        NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .forEach(x -> System.out.printf("ID:%1$3d NAME:%2$-18s ROLE:%3$-18s RANK:%4$-14s STATUS:%5$s\n",
                        x.getId(),x.getName(),x.getRole(), x.getRank(),x.getIsBusy()));
        System.out.println("-------------------------------------------------------------------------------------- ");
    }

    public void printDetailItem(Long id) {
        System.out.println("-------------------------DETAIL VIEW FOR SPACESHIP ID:"+id +"----------------- ");
        CrewMember crewMember = NassaContext.NASSA_CONTEXT.getCrewMembers().stream()
                .filter(f->f.getId()==id)
                .peek(x -> System.out.println("ID:" + x.getId() +" NAME:" + x.getName() + " ROLE:"
                        + x.getRole() + "  RANK:" + x.getRank()))
                .findFirst().get();

        crewMember.getiDMission().stream().
                forEach(f-> System.out.println("MISSIONS ID#"+f+" " + MissionCrudImpl.MISSION_FACTORY.getMissionName(f)));
        System.out.println("------------------------------------------------------------------------------ ");
    }

}
