package com.epam.jwd.core_final.criteria;
import com.epam.jwd.core_final.domain.Route;
import java.util.Collection;

public class RouteCriteria {
    private Collection <Route> routes;
    private Long id;
    private Long distance;

    public static class Builder {
        private Collection<Route> routes;
        private Long id;
        private Long distance = 0L;

        public Builder(Collection<Route> routes) {
            this.routes = routes;
        }

        public RouteCriteria.Builder routes(Collection<Route> arg) {
            routes = arg;
            return this;
        }

        public RouteCriteria.Builder byId(Long arg) {
            id = arg;
            return this;
        }

        public RouteCriteria.Builder byDist (Long arg){
                distance = arg;
                return this;
        }

        public RouteCriteria build () {
            return new RouteCriteria(this);
        }
    }

    private RouteCriteria (RouteCriteria.Builder builder) {
        routes = builder.routes;
        distance = builder.distance;
        id = builder.id;
    }

    public Collection <Route> getRoutes() {
        return routes;
    }

    public Long byDist() {
        return distance;
    }

    public Long byId () {
        return id;
    }
}
