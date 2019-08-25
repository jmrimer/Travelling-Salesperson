package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HamiltonPathMapperTest {
  private City city1;
  private City city2;
  private City city3;
  private HamiltonPathMapper subject;

  @Before
  public void setup() {
    subject = new HamiltonPathMapper();
    city1 = new City(1, 0.0d, 0.0d);
    city2 = new City(2, 3.0d, 4.0d);
    city3 = new City(3, 0d, 8.0d);
  }

  @Test
  public void returnsHashMapWithAllCityRouteCombinationsFromAllStartingPoints() {
    HashMap<List<City>, Float> expectedHashMap = new HashMap<>();
    expectedHashMap.put(List.of(city1, city2, city3, city1), 0f);
    expectedHashMap.put(List.of(city1, city3, city2, city1), 0f);
    expectedHashMap.put(List.of(city2, city1, city3, city2), 0f);
    expectedHashMap.put(List.of(city2, city3, city1, city2), 0f);
    expectedHashMap.put(List.of(city3, city1, city2, city3), 0f);
    expectedHashMap.put(List.of(city3, city2, city1, city3), 0f);

    assertEquals(expectedHashMap, subject.map(List.of(city1, city2, city3)));
  }

  @Test
  public void returnsHashMapWithAllCityWeights() {
    HashMap<List<City>, Float> expectedWeightedMap = new HashMap<>();
    expectedWeightedMap.put(List.of(city1, city2, city3, city1), 18f);
    expectedWeightedMap.put(List.of(city1, city3, city2, city1), 18f);
    expectedWeightedMap.put(List.of(city2, city1, city3, city2), 18f);
    expectedWeightedMap.put(List.of(city2, city3, city1, city2), 18f);
    expectedWeightedMap.put(List.of(city3, city1, city2, city3), 18f);
    expectedWeightedMap.put(List.of(city3, city2, city1, city3), 18f);

    assertEquals(expectedWeightedMap, subject.weightedMap(List.of(city1, city2, city3)));
  }

}
