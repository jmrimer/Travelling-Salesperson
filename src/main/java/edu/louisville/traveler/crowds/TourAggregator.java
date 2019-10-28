package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TourAggregator {
  public static LivingTour aggregate(Map map, List<Edge> edges) {
//    get heuristic path
//    insert WOAC edges
    ClosestEdgeInserter closestEdgeInserter = new ClosestEdgeInserter();
    Tour tour = closestEdgeInserter.generateTour(map.getCities());

    List<City> route = tour.getCycle();
    route.remove(route.size() - 1);
    List<List<City>> cityHunks = initializeCityHunks(route);
    fuseWisdomWithHeuristicTour(edges, cityHunks);
    route = flatten(cityHunks);

    route.add(route.get(0));
    return new LivingTour(route);
  }

  private static void fuseWisdomWithHeuristicTour(List<Edge> edges, List<List<City>> hunks) {
    for (Edge edge : edges) {
      List<City> startHunk = findHunkContaining(edge.getStart(), hunks);
      List<City> endHunk = findHunkContaining(edge.getEnd(), hunks);
//      assert endHunk != null;
//      assert startHunk != null;
//      if (hunks.indexOf(startHunk) < hunks.indexOf(endHunk)) {
        fuse(startHunk, endHunk, edge);
        hunks.remove(endHunk);
//      } else {
//        endHunk.addAll(startHunk);
//        hunks.remove(startHunk);
//      }
    }
  }

  private static void fuse(List<City> sourceHunk, List<City> hunkToAdd, Edge edge) {
    if (sourceHunk.get(0).equals(edge.getStart())) {
      Collections.reverse(sourceHunk);
    }
    if (hunkToAdd.get(hunkToAdd.size() - 1).equals(edge.getEnd())) {
      Collections.reverse(hunkToAdd);
    }
    sourceHunk.addAll(hunkToAdd);
  }

  private static List<City> findHunkContaining(City city, List<List<City>> hunks) {
    for (List<City> hunk : hunks) {
      if (hunk.contains(city)) {
        return hunk;
      }
    }
    return null;
  }

  private static List<City> flatten(List<List<City>> hunks) {
    return hunks.stream()
      .flatMap(List::stream)
      .collect(Collectors.toList());
  }

  private static List<List<City>> initializeCityHunks(List<City> route) {
    List<List<City>> cityHunks = new ArrayList<>();
    for (City city : route) {
      cityHunks.add(new ArrayList<>(List.of(city)));
    }
    return cityHunks;
  }
}
