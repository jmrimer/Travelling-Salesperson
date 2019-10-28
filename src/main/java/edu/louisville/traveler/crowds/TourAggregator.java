package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TourAggregator {
  public LivingTour aggregate(Map map, List<Edge> edges) {
    List<City> route = flattenEdgesIntoList(edges);

    for (City city : map.getCities()) {
      if (!route.contains(city)) {
        route.add(city);
      }
    }

    route.add(route.get(0));
//    get all unconnected cities
//    connect each city to the closest each (see if heuristic can help)
//    make
    return new LivingTour(route);
  }

  private List<City> flattenEdgesIntoList(List<Edge> edges) {
    List<City> cities = new ArrayList<>();
    for (Edge edge : edges) {
      if (alreadyContainsEdgeCities(cities, edge)) {
        int startIndex = cities.indexOf(edge.getStart());
        int endIndex = cities.indexOf(edge.getEnd());
        if (startIndex < endIndex) {
          reverseSublist(cities, startIndex, endIndex);
        } else {
          reverseSublist(cities, endIndex, startIndex);
        }
        continue;
      }

      if (containsNeitherEdgeCity(cities, edge)) {
        addBothCities(cities, edge);
      }

      if (containsStartNotEnd(cities, edge)) {
        insertEndCityAfterStart(cities, edge);
      }

      if (containsEndNotStart(cities, edge)) {
        insertStartCityBeforeEnd(cities, edge);
      }
    }
    return cities;
  }

  private void reverseSublist(List<City> cities, int startIndexInclusive, int endIndexExclusive) {
    List<City> sublist = cities.subList(startIndexInclusive, endIndexExclusive);
    Collections.reverse(sublist);
  }

  private void insertEndCityAfterStart(List<City> cities, Edge edge) {
    int indexOfSharedCity = cities.indexOf(edge.getStart());
    if (indexOfSharedCity == cities.size() - 1) {
      cities.add(edge.getEnd());
    } else {
      cities.add(indexOfSharedCity + 1, edge.getEnd());
    }
  }

  private void insertStartCityBeforeEnd(List<City> cities, Edge edge) {
    int indexOfSharedCity = cities.indexOf(edge.getEnd());
    cities.add(indexOfSharedCity, edge.getStart());
  }

  private void addBothCities(List<City> cities, Edge edge) {
    cities.add(edge.getStart());
    cities.add(edge.getEnd());
  }

  private boolean containsEndNotStart(List<City> cities, Edge edge) {
    return cities.contains(edge.getEnd()) && !cities.contains(edge.getStart());
  }

  private boolean containsStartNotEnd(List<City> cities, Edge edge) {
    return cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
  }

  private boolean containsNeitherEdgeCity(List<City> cities, Edge edge) {
    return !cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
  }

  private boolean alreadyContainsEdgeCities(List<City> cities, Edge edge) {
    return cities.contains(edge.getStart()) && cities.contains(edge.getEnd());
  }

}
