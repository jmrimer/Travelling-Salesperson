package edu.louisville.project1;

import org.paukov.combinatorics3.Generator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HamiltonPathMapper {
  public HashMap<List<City>, Float> map(List<City> cities) {
    HashMap<List<City>, Float> routes = new HashMap<>();

    Generator.permutation(cities)
      .simple()
      .stream()
      .forEach(route -> {
          route.add(route.get(0));
          routes.put(route, 0f);
        }
      );
    return routes;
  }

  public HashMap<List<City>, Float> weightedMap(List<City> cities) {
    List<Integer> cityNames = new ArrayList<>();
    for (City city : cities) {
      cityNames.add(city.name);
    }
    HashMap<List<City>, Float> weightedMap = this.map(cities);
    for (Map.Entry<List<City>, Float> route : weightedMap.entrySet()) {
//      String[] routeStops = route.getKey().split("-");
//      int[] routeStopNames = new int[routeStops.length];
//      for (int i = 0; i < routeStops.length; i++) {
//        routeStopNames[i] = Integer.parseInt(routeStops[i]);
//      }

      float weight = 0f;
      List<City> routeList = route.getKey();
      for (int cityIndex = 0; cityIndex < routeList.size() - 1; cityIndex++) {
        weight += Point2D.distance(
          routeList.get(cityIndex).latitude,
          routeList.get(cityIndex).longitude,
          routeList.get(cityIndex + 1).latitude,
          routeList.get(cityIndex + 1).longitude
        );
      }
      weightedMap.replace(route.getKey(), weight);
//      for (int cityIndex = 0; cityIndex < routeStops.length; cityIndex++) {
////        weight += Point2D.distance()
//      }
    }

    return weightedMap;
  }
}
