package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TourAggregator {
  public static LivingTour aggregate(Map map, List<Edge> edges) {
//    get heuristic path
//    insert WOAC edges
    ClosestEdgeInserter closestEdgeInserter = new ClosestEdgeInserter();
    Tour tour = closestEdgeInserter.generateTour(map.getCities());
    List<List<Edge>> hunkedEdges = hunkEdges(edges);
//    insertEdgesIntoTour(edges, tour);

//    List<City> route = flattenEdgesIntoList(edges);
//
//    for (City city : map.getCities()) {
//      if (!route.contains(city)) {
//        route.add(city);
//      }
//    }
//
//    route.add(route.get(0));
    return insertEdgesIntoTour(edges, tour);
  }

  public static List<List<Edge>> hunkEdges(List<Edge> edges) {
    List<List<Edge>> hunkedEdges = new ArrayList<>();
    List<Edge> edgesClone = new ArrayList<>(edges);

    Iterator<Edge> edge1Iterator = edgesClone.iterator();

    loopLabel:
    while (edge1Iterator.hasNext()) {
      Edge edge1 = edge1Iterator.next();
      if (edgeAlreadyHunked(hunkedEdges, edge1)) {
        edge1Iterator.remove();
      } else {

        List<Edge> edgeHunk = new ArrayList<>();
        edgeHunk.add(edge1);
        for (Edge edge2 : edgesClone) {
          if (!edge1.equals(edge2) && edge1.isConnected(edge2)) {
            edgeHunk.add(edge2);
          }
        }
        edge1Iterator.remove();
        hunkedEdges.add(edgeHunk);
      }
    }

    return hunkedEdges;
  }

  private static boolean edgeAlreadyHunked(List<List<Edge>> hunkedEdges, Edge edge1) {
    for (List<Edge> edgeList : hunkedEdges) {
      if (edgeList.contains(edge1)) {
        return true;
      }
    }
    return false;
  }

  private static LivingTour insertEdgesIntoTour(List<Edge> edges, Tour tour) {
    List<City> route = tour.getCycle();
    route.remove(route.size() - 1);
    for (Edge edge : edges) {

      int startIndex = route.indexOf(edge.getStart());
      int endIndex = route.indexOf(edge.getEnd());
      City start = route.get(startIndex);

//      if (startIndex < endIndex) {
//        reverseSublist(route, startIndex, endIndex);
//      } else {
//        reverseSublist(route, endIndex + 1, startIndex);
//      }
      if (start.isOpenRight()) {
        if (startIndex == route.size() - 1) {
          route.add(edge.getEnd());
          route.remove(endIndex);
        } else if (startIndex < endIndex) {
          route.add(startIndex + 1, edge.getEnd());
          route.remove(endIndex + 1);
        } else {
          route.add(startIndex + 1, edge.getEnd());
          route.remove(endIndex);
        }
        start.setOpenRight(false);
      } else {
        if (startIndex == 0) {
          route.add(0, edge.getEnd());
          route.remove(endIndex + 1);
        } else if (startIndex < endIndex) {
          route.add(startIndex - 1, edge.getEnd());
          route.remove(endIndex + 1);
        } else {
          route.add(startIndex - 1, edge.getEnd());
          route.remove(endIndex);
        }
      }

    }
    List<City> cycle = route;
    cycle.add(cycle.get(0));
    return new LivingTour(route);
  }

//  private static List<City> flattenEdgesIntoList(List<Edge> edges) {
//    List<City> cities = new ArrayList<>();
//    for (Edge edge : edges) {
//      if (alreadyContainsEdgeCities(cities, edge)) {
//        int startIndex = cities.indexOf(edge.getStart());
//        int endIndex = cities.indexOf(edge.getEnd());
//        if (startIndex < endIndex) {
//          reverseSublist(cities, startIndex, endIndex);
//        } else {
//          reverseSublist(cities, endIndex, startIndex);
//        }
//        continue;
//      }
//
//      if (containsNeitherEdgeCity(cities, edge)) {
//        addBothCities(cities, edge);
//      }
//
//      if (containsStartNotEnd(cities, edge)) {
//        insertEndCityAfterStart(cities, edge);
//      }
//
//      if (containsEndNotStart(cities, edge)) {
//        insertStartCityBeforeEnd(cities, edge);
//      }
//    }
//    return cities;
//  }

//  private static void reverseSublist(List<City> cities, int startIndexInclusive, int endIndexExclusive) {
//    List<City> sublist = cities.subList(startIndexInclusive, endIndexExclusive);
//    Collections.reverse(sublist);
//  }

//  private static void insertEndCityAfterStart(List<City> cities, Edge edge) {
//    int indexOfSharedCity = cities.indexOf(edge.getStart());
//    if (indexOfSharedCity == cities.size() - 1) {
//      cities.add(edge.getEnd());
//    } else {
//      cities.add(indexOfSharedCity + 1, edge.getEnd());
//    }
//  }
//
//  private static void insertStartCityBeforeEnd(List<City> cities, Edge edge) {
//    int indexOfSharedCity = cities.indexOf(edge.getEnd());
//    cities.add(indexOfSharedCity, edge.getStart());
//  }
//
//  private static void addBothCities(List<City> cities, Edge edge) {
//    cities.add(edge.getStart());
//    cities.add(edge.getEnd());
//  }
//
//  private static boolean containsEndNotStart(List<City> cities, Edge edge) {
//    return cities.contains(edge.getEnd()) && !cities.contains(edge.getStart());
//  }
//
//  private static boolean containsStartNotEnd(List<City> cities, Edge edge) {
//    return cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
//  }
//
//  private static boolean containsNeitherEdgeCity(List<City> cities, Edge edge) {
//    return !cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
//  }
//
//  private static boolean alreadyContainsEdgeCities(List<City> cities, Edge edge) {
//    return cities.contains(edge.getStart()) && cities.contains(edge.getEnd());
//  }

}
