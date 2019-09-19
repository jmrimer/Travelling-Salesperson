package edu.louisville.traveler.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ClosestEdgeInserter {
  private MapHelpers mapHelpers = new MapHelpers();
  private HashSet<Edge> edges = new HashSet<>();
  private List<City> route = new ArrayList<>();
  private List<City> remainingCities;
  private City lastCityVisited;
  private double weight = 0;

  Tour generateTour(List<City> cities) {
    remainingCities = new ArrayList<>(cities);
    City start = cities.get(0);
    remainingCities = sortRemainingCitiesByDistanceFromStart(remainingCities, start);
    firstStopBasedOnPointDistanceFrom(start);
    visitRemainingCities();
    returnTo(start);
    return new Tour(route, weight);
  }

  private List<City> sortRemainingCitiesByDistanceFromStart(List<City> remainingCities, City start) {
    LinkedHashMap<City, Double> cityDistanceFromStart = new LinkedHashMap<>();
    for (City city : remainingCities) {
      cityDistanceFromStart.put(city, mapHelpers.calculateDistance(start, city));
    }
    cityDistanceFromStart = cityDistanceFromStart.entrySet().stream()
      .sorted(Map.Entry.comparingByValue())
      .collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (x,y)-> {throw new AssertionError();},
        LinkedHashMap::new
      ));
    return new ArrayList<>(cityDistanceFromStart.keySet());
  }

  private void returnTo(City start) {
    Edge returnToStart = new Edge(lastCityVisited, start);
    route.add(start);
    weight += returnToStart.getWeight();
  }

  private void visitRemainingCities() {
    while (remainingCities.size() > 1) {
      remainingCities.remove(lastCityVisited);
      CityAndEdge cityAndEdge = mapHelpers.findNearestCity(edges, remainingCities);
      addJourneyLegToTour(cityAndEdge.getCity(), cityAndEdge.getEdge());
//      addJourneyLegToTour(
//        lastCityVisited,
//        cityAndEdge.getCity()
//      );
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
    edges.add(new Edge(closestEdge.getStart(), newCity));
    edges.add(new Edge(newCity, closestEdge.getEnd()));
//    edges.add(new Edge(closestEdge.getStart(), closestEdge.getEnd()));

    System.out.println(("1: " + closestEdge.getStart() + " | 2: " + newCity + " | 3: " + closestEdge.getEnd()));
    route.add(route.indexOf(closestEdge.getEnd()) + 1, newCity);
    System.out.println(route);
    lastCityVisited = newCity;
//    change route by inserting new
  }

  private void addJourneyLegToTour(City start, City end) {
    Edge edge = new Edge(start, end);
    edges.add(edge);
    route.add(end);
    weight += edge.getWeight();
    lastCityVisited = end;
  }
}
