package edu.louisville.project1.maps;

import edu.louisville.project1.maps.City;

import java.awt.geom.Point2D;
import java.util.List;

class RouteWeightCalculator {
  float calculateWeight(List<City> route) {
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
