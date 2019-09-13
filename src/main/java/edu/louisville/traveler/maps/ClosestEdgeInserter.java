package edu.louisville.traveler.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class ClosestEdgeInserter {
  private MapHelpers mapHelpers = new MapHelpers();

  WeightedRoute generateTourByInsertion(List<City> cities, City start) {
    List<City> remainingCities = new ArrayList<>(cities);
    List<City> route = new ArrayList<>(List.of(start));
    HashSet<Edge> edges = new HashSet<>();
    WeightedRoute weightedRoute = new WeightedRoute(route, 0);
    remainingCities.remove(start);

    City firstStop = mapHelpers.findNearestCity(start, remainingCities);
    Edge edge1 = new Edge(start, firstStop);
    edges.add(edge1);
    weightedRoute.route.add(firstStop);
    weightedRoute.setWeight(weightedRoute.getWeight() + edge1.getWeight());
    remainingCities.remove(firstStop);


    City secondStop = mapHelpers.findNearestCity(edges, remainingCities);
    Edge edge2 = new Edge(firstStop, secondStop);
    edges.add(edge2);
    weightedRoute.route.add(secondStop);
    weightedRoute.setWeight(weightedRoute.getWeight() + edge2.getWeight());
    remainingCities.remove(secondStop);

    City thirdStop = mapHelpers.findNearestCity(edges, remainingCities);
    Edge edge3 = new Edge(secondStop, thirdStop);
    edges.add(edge3);
    weightedRoute.route.add(thirdStop);
    weightedRoute.setWeight(weightedRoute.getWeight() + edge3.getWeight());
    remainingCities.remove(thirdStop);

    Edge edge4 = new Edge(thirdStop, start);
    weightedRoute.route.add(start);
    weightedRoute.setWeight(weightedRoute.getWeight() + edge4.getWeight());
    return weightedRoute;
  }
}
