package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.factory.impl.MissionFactory;

import java.util.Scanner;

public class NassaMenuView {

    static void nenuView() {
        System.out.println("-----LIST OF ALL MISSIONS------- ");
        MissionFactory.MISSION_FACTORY.getFlightMissions().stream()
                .forEach (x -> System.out.println("ID "+x.getId()+" MISSION: "+x.getMissionsName()+" DISTANCE: "+x.getDistance()+
                        " STARSHIP: "+x.getAssignedSpaceShift().getName()+ " RANGE:"+x.getAssignedSpaceShift().getFlightDistance() +
                        " CREW: "+ x.getAssignedCrew()+
                        " START DATE: "+ x.getStartDate()+" STATUS: "+ x.getMissionResult()));
        System.out.println("------------------------------- ");
    }
}
