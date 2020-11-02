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
        try {
            Scanner scanner = new Scanner(new File(filePath));
            String s;
            scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            //scanner.useDelimiter(";");
            while(scanner.hasNext()){
                s = scanner.next();
                System.out.println(s);
                Spaceship spaceship = new Spaceship();
                String[] lineSplit = s.split(";", 3);
                spaceship.setName(lineSplit[0]);
                spaceship.setFlightDistance(Long.valueOf(lineSplit[1]));
                //{1:5,2:6,3:2,4:3}
                String str = lineSplit[2].substring(1,lineSplit[2].length()-1);
                //System.out.println(str);
                //String[] lineSplit = lineSplit[2].split()
                //spaceship.setCrew(lineSplit[2]);
                spaceships.add (spaceship);
                //System.out.println(spaceship.getName()+" " + spaceship.getFlightDistance());
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
