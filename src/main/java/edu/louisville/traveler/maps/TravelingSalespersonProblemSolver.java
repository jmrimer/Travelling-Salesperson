package edu.louisville.traveler.maps;

import java.util.*;
import java.util.Map;

class TravelingSalespersonProblemSolver {
  Tour calculateShortestPath(List<City> cities) {
    HashSet<List<City>> routes = new HamiltonPathMapper().mapRoutes(cities);
    HashMap<List<City>, Float> weightedRoutes = new WeightAssigner().assignWeightsToRoutes(routes);
    Map.Entry<List<City>, Float> shortestRoute = null;

    for (Map.Entry<List<City>, Float> route : weightedRoutes.entrySet()) {
      if (shortestRoute == null || route.getValue() < shortestRoute.getValue()) {
        shortestRoute = route;
      }
    }

    return new Tour(shortestRoute.getKey(), shortestRoute.getValue());
  }
}
