package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ReadCrewStrategy implements ReadContextStrategy {

    public Collection<CrewMember> readEntityList(String filePath) throws IOException {
        Collection<CrewMember> crewMembers = new ArrayList<>();
        Long id = 0L;
        Scanner scanner = new Scanner(new File(filePath));
        scanner.nextLine();
        String s;
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            s = scanner.next();
            CrewMember crewMember = new CrewMember();
            String[] lineSplit = s.split(",", 3);
            crewMember.setId(++id);
            crewMember.setRole(Integer.parseInt(lineSplit[0]));
            crewMember.setName(lineSplit[1]);
            crewMember.setRank(Integer.parseInt(lineSplit[2]));
            crewMembers.add(crewMember);
        }
        scanner.close();
        return crewMembers;
    }
}
