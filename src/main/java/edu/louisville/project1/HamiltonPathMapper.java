package edu.louisville.project1;

import org.paukov.combinatorics3.Generator;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HamiltonPathMapper {
  public HashMap<List<City>, Float> map(List<City> cities) {
    HashMap<List<City>, Float> map = new HashMap<>();

    Generator.permutation(cities)
      .simple()
      .stream()
      .forEach(
        route -> {
          route.add(route.get(0));
          map.put(route, 0f);
        }
      );

    return map;
  }

  public HashMap<List<City>, Float> weightedMap(List<City> cities) {
    HashMap<List<City>, Float> weightedMap = this.map(cities);

    for (Map.Entry<List<City>, Float> route : weightedMap.entrySet()) {
      weightedMap.replace(route.getKey(), getWeight(route.getKey()));
    }

    return weightedMap;
  }

  private float getWeight(List<City> routeList) {
    float weight = 0f;

    for (int cityIndex = 0; cityIndex < routeList.size() - 1; cityIndex++) {
      weight += Point2D.distance(
        routeList.get(cityIndex).latitude,
        routeList.get(cityIndex).longitude,
        routeList.get(cityIndex + 1).latitude,
        routeList.get(cityIndex + 1).longitude
      );
    }

    return weight;
  }
}
