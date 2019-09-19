package edu.louisville.traveler.maps;

import org.junit.Test;

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
  public void determinesClosestEdgeWhenEdgeEndpointIsTheClosestPoint() {
    City city1 = new City(1, 22.549020, 89.029536);
    City city2 = new City(2, 23.039216, 81.434599);
    City city3 = new City(3, 30.392157, 79.324895);
    City city4 = new City(4, 40.277778, 80.379747);

    Edge edge1_3 = new Edge(city1, city3);
    Edge edge3_2 = new Edge(city3, city2);
    List<Edge> edgesThatShareEndpoint = List.of(edge1_3, edge3_2);

    assertEquals(
      new MapHelpers().determineClosestEdgeWhenSharedEndpoints(edgesThatShareEndpoint, city4),
      edge1_3
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
      new MapHelpers().findNearestCity(edges, cities).getCity()
    );
  }

  @Test
  public void generatesNewPointOnLine() {
    City city1 = new City(1, 0, 1);
    City city2 = new City(2, 4, 1);
    Edge edge = new Edge(city1, city2);
    assertEquals(
      new Point2D.Double(3, 1),
      new MapHelpers().generateNewPointOnLineFromEndpoint(edge, city2)
    );
    assertEquals(
      new Point2D.Double(1, 1),
      new MapHelpers().generateNewPointOnLineFromEndpoint(edge, city1)
    );

    City city3 = new City(2, 0, 4);
    City city4 = new City(1, 3, 0);
    edge = new Edge(city3, city4);
    assertEquals(
      new Point2D.Double(2.4, 0.8),
      new MapHelpers().generateNewPointOnLineFromEndpoint(edge, city4)
    );
    assertEquals(
      new Point2D.Double(0.6, 3.2),
      new MapHelpers().generateNewPointOnLineFromEndpoint(edge, city3)
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

  @Test
  public void weightOfFullPath() {
    City city1 = new City(1, 87.951292, 2.658162);
    City city2 = new City(2, 33.466597, 66.682943);
    City city3 = new City(3, 91.778314, 53.807184);
    City city4 = new City(4, 20.526749, 47.633290);
    MapHelpers mapHelpers = new MapHelpers();
    assertEquals(
      215.08553303209044,
      mapHelpers.calculateDistance(city1, city4) +
      mapHelpers.calculateDistance(city4, city2) +
      mapHelpers.calculateDistance(city2, city3) +
      mapHelpers.calculateDistance(city3, city1),
      0.000005
    );
  }
}