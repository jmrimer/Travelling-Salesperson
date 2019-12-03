package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashiMapTest extends BaseHashiTest {
   @Test
  void cloneReservesOriginalValues() {
    islandCenter.setPopulation(1);
    islandEast.setPopulation(2);
    islandEast.decreaseAdjustedPopulation();

    hashiMap = new HashiMap(
      7,
      List.of(islandCenter, islandEast)
    );

    HashiMap clone = hashiMap.clone();

    hashiMap.getIslands().get(0).setPopulation(3);
    hashiMap.getIslands().get(1).decreaseAdjustedPopulation();
    assertEquals(3, hashiMap.getIslands().get(0).getPopulation());
    assertEquals(3, islandCenter.getPopulation());
    assertEquals(1, clone.getIslands().get(0).getPopulation());

    hashiMap = clone;
    assertEquals(1, hashiMap.getIslands().get(0).getPopulation());
    assertEquals(1, hashiMap.getIslands().get(1).getAdjustedPopulation());
  }
}