package edu.louisville.project1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class TravelingSalespersonProblemSolver {
  WeightedRoute calculateShortestPath(List<City> cities) {
    LinkedHashMap<List<City>, Float> routes = new HamiltonPathMapper().mapWeightedRoutes(cities);
    Map.Entry<List<City>, Float> shortestRoute = null;

    for (Map.Entry<List<City>, Float> route : routes.entrySet()) {
      if (shortestRoute == null || route.getValue() < shortestRoute.getValue()) {
        shortestRoute = route;
      }
    }

    return new WeightedRoute(shortestRoute.getKey(), shortestRoute.getValue());
  }
}
