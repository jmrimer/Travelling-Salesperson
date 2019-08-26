package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TravelingSalespersonProblemSolverTest {
  private HamiltonPathMapper subject;
  private City city1;
  private City city2;
  private City city3;
  private City city4;
  private List<City> route1;

  @Before
  public void setup() {
    subject = new HamiltonPathMapper();
    city1 = new City(1, 0.0d, 0.0d);
    city2 = new City(2, 3.0d, 4.0d);
    city3 = new City(3, 0d, 8.0d);
    city4 = new City(4, -3.0d, 4.0d);
    route1 = List.of(city1, city4, city3, city2, city1);
  }

  @Test
  public void returnsShortestPathGivenListOfCities() {
    TravelingSalespersonProblemSolver subject = new TravelingSalespersonProblemSolver();
    assertEquals([route1, 20f], subject.calculateShortestPath(cityList));
  }
}