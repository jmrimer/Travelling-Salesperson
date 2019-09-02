package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HamiltonPathMapperTest extends BaseTest {
  private HamiltonPathMapper subject;

  @Before
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

}
