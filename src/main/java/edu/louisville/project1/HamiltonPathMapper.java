package edu.louisville.project1;

import org.paukov.combinatorics3.Generator;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HamiltonPathMapper {
  public LinkedHashMap<List<City>, Float> map(List<City> cities) {
    LinkedHashMap<List<City>, Float> map = new LinkedHashMap<>();

    Generator.permutation(cities)
      .simple()
      .stream()
      .forEach(
        route -> {
          route.add(route.get(0));
          map.put(route, 0f);
        }
      );

    return dedupe(map);
  }

  private LinkedHashMap<List<City>, Float> dedupe(LinkedHashMap<List<City>, Float> map) {
    City startingCity = map.entrySet().iterator().next().getKey().get(0);
    map.keySet().removeIf(route -> route.get(0) != startingCity);
    return map;
  }

  public LinkedHashMap<List<City>, Float> weightedMap(List<City> cities) {
    LinkedHashMap<List<City>, Float> weightedMap = this.map(cities);

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
