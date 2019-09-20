package edu.louisville.traveler.maps;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapHelpersTest {
  @Test
  public void getNearestCityFromCity() {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 2);

    List<City> cities = List.of(city1, city2, city3);

    assertEquals(
      city2,
      new MapHelpers().findNearestCity(city1, cities)
    );
  }

  @Test
  public void getNearestCityFromEdges() {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 1);
    City city4 = new City(4, 1, 1.8);

    List<City> cities = List.of(city3, city4);
    HashSet<Edge> edges = new HashSet<>(List.of(new Edge(city1, city2)));


    assertEquals(
      city3,
      new MapHelpers().findNearestCity(edges, cities)
    );
  }

  @Test
  public void generatesNewPointOnLine() {
    Edge edge = new Edge(new City(1, 0, 1), new City(2, 4, 1));
    assertEquals(
      new Point2D.Double(3, 1),
      new MapHelpers().generateNewPointOnLine(edge, 1)
    );

    edge = new Edge(new City(2, 0, 4), new City(1, 3, 0));
    assertEquals(
      new Point2D.Double(2.4, 0.8),
      new MapHelpers().generateNewPointOnLine(edge, 1)
    );
  }

  @Test
  public void calculatesDistanceFromCityToEdge() {
    assertEquals(
      Math.sqrt(2) / 2,
      new MapHelpers().calculateDistance(
        new City(3, 0, 1),
        new Edge(
          new City(1, 0, 0),
          new City(2, 1, 1)
        )
      ),
      0.005
    );
    assertEquals(
      1,
      new MapHelpers().calculateDistance(
        new City(3, 1, 2),
        new Edge(
          new City(1, 0, 0),
          new City(2, 1, 1)
        )
      ),
      0.005
    );
  }
}