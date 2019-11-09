package edu.louisville.traveler.maps;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelingSalespersonProblemSolverTest {

  @Test
  public void returnsShortestPathGivenListOfCities() {
    TravelingSalespersonProblemSolver subject = new TravelingSalespersonProblemSolver();
    City city1 = new City(1, 0.0d, 0.0d);
    City city2 = new City(2, 3.0d, 4.0d);
    City city3 = new City(3, 0d, 8.0d);
    City city4 = new City(4, -3.0d, 4.0d);

    assertEquals(
      new Tour(List.of(city1, city4, city3, city2, city1), 20f),
      subject.calculateShortestPath(List.of(city1, city2, city3, city4))
    );
  }
}