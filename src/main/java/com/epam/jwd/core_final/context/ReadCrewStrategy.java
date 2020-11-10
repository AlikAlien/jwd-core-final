package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ReadCrewStrategy {
    public Collection <CrewMember> readBaseEntityList (String filePath ) throws IOException  {
        Collection <CrewMember> crewMembers = new ArrayList<>();
        Long id = 0L;
        //try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine();
            String s;
            scanner.useDelimiter(";");
            while(scanner.hasNext()){
                s = scanner.next();
            CrewMember crewMember = new CrewMember();
            String[] lineSplit = s.split(",", 3);
            crewMember.setId(++id);
            crewMember.setRole(Role.getRoleById(Integer.valueOf(lineSplit[0])));
            crewMember.setName(lineSplit[1]);
            crewMember.setRank(lineSplit[2]);
            crewMembers.add (crewMember);
            }
            scanner.close();

        return crewMembers;
    }
}
