package edu.louisville.traveler.maps;

import org.paukov.combinatorics3.Generator;

import java.util.*;

class TravelingSalespersonProblemSolver {
  private double bestWeight = Double.MAX_VALUE;
  private Tour bestTour = new Tour();

  Tour discoverBestTour(List<City> cities) {
    this.mapRoutes(cities);
    return this.bestTour;
  }

  HashSet<List<City>> mapRoutes(List<City> cities) {
    RouteWeightCalculator routeWeightCalculator = new RouteWeightCalculator();
    List<City> citiesBeyondTheFirst = this.citiesBeyondTheFirst(cities);
    HashSet<List<City>> routes = new HashSet<>();
    Generator.permutation(citiesBeyondTheFirst)
      .simple()
      .stream()
      .forEach(
        route -> {
          route.add(0, cities.get(0));
          route.add(cities.get(0));
          double weight = routeWeightCalculator.calculateWeight(route);
          if (weight < bestWeight) {
            bestWeight = weight;
            this.updateBestTour(route, weight);
          }
        }
      );
    return routes;
  }

  private void updateBestTour(List<City> route, double weight) {
    this.bestTour = new Tour(route, weight);
  }

  private List<City> citiesBeyondTheFirst(List<City> cities) {
    List<City> citiesBeyondTheFirst = new LinkedList<>(cities);
    citiesBeyondTheFirst.remove(0);
    return citiesBeyondTheFirst;
  }

  private HashSet<List<City>> removeMirrorRoutes(HashSet<List<City>> routes) {
    Iterator<List<City>> iterator = routes.iterator();
    while (iterator.hasNext()) {
      List<City> mirrorRoute = mirrorRoute(iterator.next());
      if (routes.contains(mirrorRoute)) {
        iterator.remove();
      }
    }
    return routes;
  }

  private List<City> mirrorRoute(List<City> route) {
    List<City> mirrorRoute = new ArrayList<>();
    for (int city = route.size() - 1; city > -1; city--) {
      mirrorRoute.add((route.get(city)));
    }
    return mirrorRoute;
  }

  private HashSet<List<City>> dedupe(HashSet<List<City>> routes) {
    City startingCity = routes.iterator().next().get(0);
    routes.removeIf(route -> route.get(0) != startingCity);
    return routes;
  }
}
