package edu.louisville.traveler.maps;

import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HamiltonPathMapperBruteForceTest extends BaseBruteForceTest {
  private HamiltonPathMapper subject;

@BeforeEach
  public void setup() {
    subject = new HamiltonPathMapper();
  }

  @Test
  public void returnsHashMapWithAllCityWeights() {
    HashSet<List<City>> expectedWeightedMap = new HashSet<>();
    expectedWeightedMap.add(route1);
    expectedWeightedMap.add(route2);
    expectedWeightedMap.add(route3);

    assertEquals(expectedWeightedMap, subject.mapRoutes(List.of(city1, city2, city3, city4)));
  }

  @Test
  public void getWorst() {
    City city1 = new City(1, 1, 1);
    City city2 = new City(2, 1, 2);
    City city3 = new City(3, 1, 3);
    City city4 = new City(4, 3, 3);
    City city5 = new City(5, 3, 2);
    List<City> route1 = List.of(city1, city4, city2, city5, city3, city1);
    List<City> route2 = List.of(city1, city4, city2, city3, city5, city1);

    HashSet<List<City>> routes = subject.mapRoutes(List.of(city1, city2, city3, city4, city5));
    for (List<City> route : routes) {

    System.out.println(route);
    System.out.println(RouteWeightCalculator.calculateWeight(route));
    }
  }
}
