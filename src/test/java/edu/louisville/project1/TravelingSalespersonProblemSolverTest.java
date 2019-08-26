package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TravelingSalespersonProblemSolverTest {
  private HamiltonPathMapper subject;
  private City city1 = new City(1, 0.0d, 0.0d);
  private City city2 = new City(2, 3.0d, 4.0d);
  private City city3 = new City(3, 0d, 8.0d);
  private City city4 = new City(4, -3.0d, 4.0d);

  @Before
  public void setup() {
    subject = new HamiltonPathMapper();
  }

  @Test
  public void returnsShortestPathGivenListOfCities() {
    TravelingSalespersonProblemSolver subject = new TravelingSalespersonProblemSolver();
    assertEquals(
      new WeightedRoute(List.of(city1, city4, city3, city2, city1), 20f),
      subject.calculateShortestPath(List.of(city1, city2, city3, city4))
    );
  }
}