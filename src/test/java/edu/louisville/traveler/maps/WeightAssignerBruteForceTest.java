package edu.louisville.traveler.maps;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeightAssignerBruteForceTest extends BaseBruteForceTest {
  @Test
  public void assignsWeightsToAllRoutes() {
    WeightAssigner subject = new WeightAssigner();
    HashSet<List<City>> routes = new HashSet<>();
    routes.add(route1);
    routes.add(route2);
    routes.add(route3);

    HashMap<List<City>, Float> expectedWeightedMap = new HashMap<>();
    expectedWeightedMap.put(route1, 20f);
    expectedWeightedMap.put(route2, 24f);
    expectedWeightedMap.put(route3, 24f);

    assertEquals(
      expectedWeightedMap,
      subject.assignWeightsToRoutes(routes)
    );
  }

}