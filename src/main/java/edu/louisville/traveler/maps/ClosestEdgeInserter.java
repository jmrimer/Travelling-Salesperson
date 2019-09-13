package edu.louisville.traveler.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class ClosestEdgeInserter {
  private MapHelpers mapHelpers = new MapHelpers();
  private HashSet<Edge> edges = new HashSet<>();
  private List<City> route = new ArrayList<>();
  private List<City> remainingCities;
  private City lastCityVisited;
  private double weight = 0;

  WeightedRoute generateTour(List<City> cities, City start) {
    remainingCities = new ArrayList<>(cities);
    firstStopBasedOnPointDistanceFrom(start);
    visitRemainingCities();
    returnTo(start);
    return new WeightedRoute(route, weight);
  }

  private void returnTo(City start) {
    Edge returnToStart = new Edge(lastCityVisited, start);
    route.add(start);
    weight += returnToStart.getWeight();
  }

  private void visitRemainingCities() {
    while (remainingCities.size() > 1) {
      remainingCities.remove(lastCityVisited);
      addJourneyLegToTour(
        lastCityVisited,
        mapHelpers.findNearestCity(edges, remainingCities)
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
    Edge edge = new Edge(start, end);
    edges.add(edge);
    route.add(end);
    weight += edge.getWeight();
    lastCityVisited = end;
  }
}
