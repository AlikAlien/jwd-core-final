package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.Route;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ReadRouteStrategy {
    public Collection<Route> readBaseEntityList (String filePath ) throws IOException{
        Collection <Route> routes = new ArrayList<>();
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine();
            String s;
            while(scanner.hasNext()){
                s = scanner.nextLine();
                Route route = new Route();
                String [] lineSplit = s.split(";", 3);
                route.setIdRoute(Integer.parseInt(lineSplit[0]));
                route.setName(lineSplit[1]);
                route.setRouteDistance(Long.parseLong(lineSplit[2]));
                routes.add (route);
            }
            scanner.close();

        return routes;
    }
}
