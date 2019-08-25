package edu.louisville.project1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HamiltonPathMapperTest {
  @Test
  public void returnsHashMapWithAllCityRouteCombinationsFromAllStartingPoints() {
    HamiltonPathMapper subject = new HamiltonPathMapper();
    HashMap<String, Integer> expectedHashMap = new HashMap<>();
    expectedHashMap.put("1-2-3", 0);
    expectedHashMap.put("1-3-2", 0);
    expectedHashMap.put("2-3-1", 0);
    expectedHashMap.put("2-1-3", 0);
    expectedHashMap.put("3-1-2", 0);
    expectedHashMap.put("3-2-1", 0);

    List<Integer> cities = new ArrayList<>();
    cities.add(1);
    cities.add(2);
    cities.add(3);
    assertEquals(expectedHashMap, subject.map(cities));
  }

}
