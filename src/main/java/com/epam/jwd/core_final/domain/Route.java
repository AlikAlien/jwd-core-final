package com.epam.jwd.core_final.domain;

public class Route extends AbstractBaseEntity{
    int idRoute;
    String name;
    long roureDistance;

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoureDistance() {
        return roureDistance;
    }

    public void setRoureDistance(Long roureDistance) {
        this.roureDistance = roureDistance;
    }
}
