package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.BaseGeneticsTest;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.MapHelpers;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TourAggregatorTest extends BaseGeneticsTest {

  @Test
  public void aggregate() {
    List<Edge> edges = List.of(
      new Edge(city2, city3),
      new Edge(city3, city4),
      new Edge(city7, city8),
      new Edge(city7, city2)
    );

    LivingTour livingTour = TourAggregator.aggregate(map100, edges);
    List<City> cycle = livingTour.getCycle();
    assertTrue(
      Collections.indexOfSubList(cycle, List.of(city2, city3, city4)) > -1
        || Collections.indexOfSubList(cycle, List.of(city4, city3, city2)) > -1
    );

    assertTrue(
      Collections.indexOfSubList(cycle, List.of(city8, city7, city2, city3, city4)) > -1
        || Collections.indexOfSubList(cycle, List.of(city4, city3, city2, city7, city8)) > -1
    );

    for (Edge edge : edges) {
      assertTrue(
        MapHelpers.cityBefore(edge.getStart(), cycle).equals(edge.getEnd()) ||
          MapHelpers.cityAfter(edge.getStart(), cycle).equals(edge.getEnd()),
        "Wisdom tour incorrectly separated a wisdom edge for: " + edge);
    }
  }

  @Test
  public void insertEdgesIntoTour() {

  }
}