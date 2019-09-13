package edu.louisville.traveler.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClosestEdgeInserterTest {
  @Test
  public void findsPathByInsertingNextPointClosestToExistingEdge() {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 2);
    City city4 = new City(4, 1, 3);
    List<City> cities = List.of(city1, city2, city3, city4);
    List<City> route = new ArrayList<>(cities);
    route.add(city1);
    WeightedRoute expectedRoute = new WeightedRoute(
      route,
      (float) (Math.sqrt(2)*3 + Math.sqrt(10))
    );

    assertEquals(
      expectedRoute.route,
      new ClosestEdgeInserter().generateTourByInsertion(cities, city1).route
    );
    assertEquals(
      expectedRoute.weight,
      new ClosestEdgeInserter().generateTourByInsertion(cities, city1).weight,
      0.0005
    );
  }
}