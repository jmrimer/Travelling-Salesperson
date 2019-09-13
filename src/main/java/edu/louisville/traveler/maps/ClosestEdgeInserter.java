package edu.louisville.traveler.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class ClosestEdgeInserter {
  MapHelpers mapHelpers = new MapHelpers();

  WeightedRoute generateTourByInsertion(List<City> cities, City start) {
    List<City> remainingCities = new ArrayList<>(cities);
    List<City> route = new ArrayList<>(List.of(start));
    remainingCities.remove(start);
    WeightedRoute weightedRoute = new WeightedRoute(route, 0);

    City firstStop = mapHelpers.findNearestCity(start, cities);
    weightedRoute.route.add(firstStop);
    remainingCities.remove(firstStop);

    HashSet<Edge> edges = new HashSet<>(List.of(new Edge(start, firstStop)));
    City secondStop = mapHelpers.findNearestCity(edges, remainingCities);

    return null;
  }
}
