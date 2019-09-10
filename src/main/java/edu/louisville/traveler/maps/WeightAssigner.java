package edu.louisville.traveler.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WeightAssigner {
  public HashMap<List<City>, Float> assignWeightsToRoutes(HashSet<List<City>> routes) {
    RouteWeightCalculator weightCalculator = new RouteWeightCalculator();
    HashMap<List<City>, Float> weightedMap = new HashMap<>();
    for (List<City> route : routes) {
      weightedMap.put(route, weightCalculator.calculateWeight(route));
    }
    return weightedMap;
  }
}
