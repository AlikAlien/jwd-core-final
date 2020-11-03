package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.Spaceship;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ReadSpaceShipStrategy{
    public Collection <Spaceship> readBaseEntityList (String filePath){
        Collection <Spaceship> spaceships = new ArrayList<>();
        Long id = 0L;
        try {
            Scanner scanner = new Scanner(new File(filePath));
            String s;
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            while(scanner.hasNext()){
                s = scanner.nextLine();
                //System.out.println(s);
                Spaceship spaceship = new Spaceship();
                String[] lineSplit = s.split(";", 3);
                spaceship.setId(++id);
                spaceship.setName(lineSplit[0]);
                //System.out.println(lineSplit[0]+ " "+ lineSplit[1]+" "+lineSplit[2]);
                spaceship.setFlightDistance(Long.valueOf(lineSplit[1]));
                //{1:5,2:6,3:2,4:3}
                String str = lineSplit[2].substring(1,lineSplit[2].length()-1);
                //1:5,2:6,3:2,4:3
                String[] crew = str.split(",", 4);

                //String[] lineSplit = lineSplit[2].split()
                //spaceship.setCrew(lineSplit[2]);
                spaceships.add (spaceship);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spaceships;
    }
}
