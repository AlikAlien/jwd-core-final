package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadSpaceshipStrategy implements ReadContextStrategy{
    public Collection<Spaceship> readEntityList(String filePath) throws IOException {
        Collection<Spaceship> spaceships = new ArrayList<>();
        Long id = 0L;
        Scanner scanner = new Scanner(new File(filePath));
        String s;
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
        while (scanner.hasNext()) {
            s = scanner.nextLine();
            Spaceship spaceship = new Spaceship();
            Map<Role, Short> mapship = new HashMap<>();
            //SC Dakota;318118;{1:4,2:5,3:2,4:1}
            String[] lineSplit = s.split(";", 3);
            spaceship.setId(++id);
            spaceship.setName(lineSplit[0]);
            spaceship.setFlightDistance(Long.valueOf(lineSplit[1]));
            //{1:4,2:5,3:2,4:1}
            String str = lineSplit[2].substring(1, lineSplit[2].length() - 1);
            //1:4,2:5,3:2,4:1
            String[] crew = str.split(",", 4);
            //[1:4] [2:5] [3:2] [4:1]
            for (String string : crew) {
                String[] str3 = string.split(":", 2);
                mapship.put(Role.getRoleById(Short.valueOf(str3[0])), Short.valueOf(str3[1]));
            }
            spaceship.setCrew(mapship);
            spaceships.add(spaceship);
        }
        scanner.close();
        return spaceships;
    }
}
