package edu.louisville.traveler.maps;

import java.util.List;

class RouteWeightCalculator {
  float calculateWeight(List<City> route) {
    MapHelpers mapHelpers = new MapHelpers();
    float weight = 0f;
    for (int cityIndex = 0; cityIndex < route.size() - 1; cityIndex++) {
      weight += mapHelpers.calculateDistance(
        route.get(cityIndex),
        route.get(cityIndex+1)
      );
    }
    return weight;
  }
}
