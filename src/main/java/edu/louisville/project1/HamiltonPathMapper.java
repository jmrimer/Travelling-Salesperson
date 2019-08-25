package edu.louisville.project1;

import org.paukov.combinatorics3.Generator;

import java.awt.geom.Point2D;
import java.util.*;

class HamiltonPathMapper {
  LinkedHashMap<List<City>, Float> mapWeightedRoutes(List<City> cities) {
    LinkedHashMap<List<City>, Float> weightedMap = this.mapUnweightedRoutes(cities);
    for (Map.Entry<List<City>, Float> route : weightedMap.entrySet()) {
      weightedMap.replace(route.getKey(), calculateWeight(route.getKey()));
    }
    return weightedMap;
  }

  private LinkedHashMap<List<City>, Float> mapUnweightedRoutes(List<City> cities) {
    LinkedHashMap<List<City>, Float> routes = new LinkedHashMap<>();
    Generator.permutation(cities)
      .simple()
      .stream()
      .forEach(
        route -> {
          route.add(route.get(0));
          routes.put(route, 0f);
        }
      );
    return removeMirrorRoutes(dedupe(routes));
  }

  private LinkedHashMap<List<City>, Float> removeMirrorRoutes(LinkedHashMap<List<City>, Float> map) {
    Iterator<List<City>> routes = map.keySet().iterator();
    while (routes.hasNext()) {
      List<City> mirrorRoute = mirrorRoute(routes.next());
      if (map.keySet().contains(mirrorRoute)) {
        routes.remove();
      }
    }
    return map;
  }

  private List<City> mirrorRoute(List<City> route) {
    List<City> mirrorRoute = new ArrayList<>();
    for (int city = route.size() - 1; city > -1; city--) {
      mirrorRoute.add((route.get(city)));
    }
    return mirrorRoute;
  }

  private LinkedHashMap<List<City>, Float> dedupe(LinkedHashMap<List<City>, Float> map) {
    City startingCity = map.entrySet().iterator().next().getKey().get(0);
    map.keySet().removeIf(route -> route.get(0) != startingCity);
    return map;
  }

  private float calculateWeight(List<City> route) {
    float weight = 0f;
    for (int cityIndex = 0; cityIndex < route.size() - 1; cityIndex++) {
      weight += getDistance(route, cityIndex);
    }
    return weight;
  }

  private double getDistance(List<City> route, int cityIndex) {
    City startingCity = route.get(cityIndex);
    City endingCity = route.get(cityIndex + 1);
    return Point2D.distance(
      startingCity.latitude,
      startingCity.longitude,
      endingCity.latitude,
      endingCity.longitude
    );
  }
}
