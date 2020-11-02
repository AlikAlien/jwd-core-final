package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ReadCrewStrategy {
    public Collection <CrewMember> readBaseEntityList (String filePath ){
        Collection <CrewMember> crewMembers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.hasNext();
            String s = scanner.next();
            scanner.useDelimiter(";");
            while(scanner.hasNext()){
                s = scanner.next();
            CrewMember crewMember = new CrewMember();
            String[] lineSplit = s.split(",", 3);
            crewMember.setRole(lineSplit[0]);
            crewMember.setName(lineSplit[1]);
            crewMember.setRank(lineSplit[2]);
            crewMembers.add (crewMember);
            //System.out.println(crewMember.getRole()+" " + crewMember.getNameCrew()+" "+crewMember.getRank());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crewMembers;
    }
}
