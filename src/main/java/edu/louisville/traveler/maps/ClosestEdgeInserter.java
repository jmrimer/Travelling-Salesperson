package edu.louisville.traveler.maps;

import java.util.*;

class ClosestEdgeInserter {
  private HashMap<CityToEdge, Double> cityToEdgeDistances = new HashMap<>();
  private MapHelpers mapHelpers = new MapHelpers();
  private HashSet<Edge> edges = new HashSet<>();
  private List<City> route = new ArrayList<>();
  private List<City> remainingCities;
  private City lastCityVisited;
  private double weight = 0;
  private int count = 0;

  Tour generateTour(List<City> cities) {
    remainingCities = new ArrayList<>(cities);
    City start = cities.get(0);
    firstStopBasedOnPointDistanceFrom(start);
//    visitRemainingCities();
    visitRemainingCitiesMemoized();
    returnTo(start);
    System.out.println(count);
    return new Tour(route, weight);
  }

  private void returnTo(City start) {
    Edge returnToStart = new Edge(lastCityVisited, start);
    route.add(start);
    weight += returnToStart.getWeight();
  }

  private void visitRemainingCitiesMemoized() {
    while (remainingCities.size() > 0) {
      addJourneyLegToTour(
        lastCityVisited,
        findNearestCity()
      );
    }
  }

  private City findNearestCity() {
    CityToEdge bestCityToEdge = null;
    double bestDistance = Double.MAX_VALUE;
    for (CityToEdge cityToEdge : cityToEdgeDistances.keySet()) {
      count++;
      double distance = cityToEdgeDistances.get(cityToEdge);
      if (distance < bestDistance) {
        bestCityToEdge = cityToEdge;
        bestDistance = distance;
      }
    }
    assert bestCityToEdge != null;
    cityToEdgeDistances.remove(bestCityToEdge);
    return bestCityToEdge.getCity();
  }

//  private void visitRemainingCities() {
//    while (remainingCities.size() > 1) {
//      remainingCities.remove(lastCityVisited);
//      addJourneyLegToTour(
//        lastCityVisited,
//        mapHelpers.findNearestCity(edges, remainingCities)
//      );
//    }
//  }

  private void addNewCityToEdgeDistancesToCollector(Edge edge) {
    for (City city : remainingCities) {
      cityToEdgeDistances.put(
        new CityToEdge(edge, city),
        mapHelpers.calculateDistance(city, edge)
      );
    }
  }

  private void firstStopBasedOnPointDistanceFrom(City start) {
    route.add(start);
    remainingCities.remove(start);
    addJourneyLegToTour(
      start,
      mapHelpers.findNearestCity(start, remainingCities)
    );
  }

  private void addJourneyLegToTour(City start, City end) {
    remainingCities.remove(end);
    Edge edge = new Edge(start, end);
    edges.add(edge);
    route.add(end);
    weight += edge.getWeight();
    lastCityVisited = end;
    removeAllCityToEdgeDistancesRelatedToTraveledCity(end);
    addNewCityToEdgeDistancesToCollector(edge);
  }

  private void removeAllCityToEdgeDistancesRelatedToTraveledCity(City cityToRemove) {
    cityToEdgeDistances.keySet().removeIf(cityToEdge -> {
      count++;
      return cityToEdge.getCity().equals(cityToRemove);
    });
  }
}
