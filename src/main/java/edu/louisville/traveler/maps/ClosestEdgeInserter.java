package edu.louisville.traveler.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClosestEdgeInserter {
  private MapHelpers mapHelpers = new MapHelpers();
  private HashSet<Edge> edges = new HashSet<>();
  private List<City> route = new ArrayList<>();
  private List<City> remainingCities;
  private double weight = 0;

  public Tour generateTour(List<City> cities) {
    remainingCities = new ArrayList<>(cities);
    City start = cities.get(0);
    firstStopBasedOnPointDistanceFrom(start);
    visitRemainingCities();
    returnTo(start);
    return new Tour(route, weight);
  }

  private void returnTo(City start) {
    addJourneyLegToTour(route.get(route.size() - 1), start);
  }

  private void visitRemainingCities() {
    while (remainingCities.size() > 0) {
      CityAndEdge cityAndEdge = mapHelpers.findNearestCity(edges, remainingCities);
      addJourneyLegToTour(cityAndEdge.getCity(), cityAndEdge.getEdge());
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

  private void addJourneyLegToTour(City newCity, Edge closestEdge) {
    edges.remove(closestEdge);
    weight -= closestEdge.getWeight();

    Edge firstLeg = new Edge(closestEdge.getStart(), newCity);
    edges.add(firstLeg);
    weight += firstLeg.getWeight();

    Edge secondLeg = new Edge(newCity, closestEdge.getEnd());
    edges.add(secondLeg);
    weight += secondLeg.getWeight();

    route.add(route.indexOf(closestEdge.getEnd()), newCity);
    remainingCities.remove(newCity);
  }

  private void addJourneyLegToTour(City start, City end) {
    Edge edge = new Edge(start, end);
    edges.add(edge);
    route.add(end);
    weight += edge.getWeight();
    remainingCities.remove(end);
  }
}
