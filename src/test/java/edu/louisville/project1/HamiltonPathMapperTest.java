package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HamiltonPathMapperTest {
  private HamiltonPathMapper subject;
  private City city1;
  private City city2;
  private City city3;
  private City city4;
  private List<City> route1;
  private List<City> route2;
  private List<City> route3;

  @Before
  public void setup() {
    subject = new HamiltonPathMapper();
    city1 = new City(1, 0.0d, 0.0d);
    city2 = new City(2, 3.0d, 4.0d);
    city3 = new City(3, 0d, 8.0d);
    city4 = new City(4, -3.0d, 4.0d);
    route1 = List.of(city1, city4, city3, city2, city1);
    route2 = List.of(city1, city3, city4, city2, city1);
    route3 = List.of(city1, city3, city2, city4, city1);
  }

  @Test
  public void returnsHashMapWithAllCityWeights() {
    HashMap<List<City>, Float> expectedWeightedMap = new HashMap<>();
    expectedWeightedMap.put(route1, 20f);
    expectedWeightedMap.put(route2, 24f);
    expectedWeightedMap.put(route3, 24f);

    assertEquals(expectedWeightedMap, subject.mapWeightedRoutes(List.of(city1, city2, city3, city4)));
  }

}
