package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashiMapTest extends BaseHashiTest {
  @Test
  public void constructorAssignsNeighbors() {
    Island islandCenter;
    Island islandNorth;
    Island islandEast;
    islandCenter = new Island(new Coordinates(0, 0), 3);
    islandNorth = new Island(new Coordinates(0, 3), 2);
    islandEast = new Island(new Coordinates(2, 0), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth,
        islandEast
      )
    );

    assertEquals(
      islandNorth,
      islandCenter.getNeighbors().get(Direction.NORTH),
      "HashiMap constructor failed to assign northern neighbor"
    );

    assertEquals(
      islandEast,
      islandCenter.getNeighbors().get(Direction.EAST),
      "HashiMap constructor failed to assign eastern neighbor"
    );

    assertEquals(
      islandCenter,
      islandNorth.getNeighbors().get(Direction.SOUTH),
      "HashiMap constructor failed to assign southern neighbor"
    );

    assertEquals(
      islandCenter,
      islandEast.getNeighbors().get(Direction.WEST),
      "HashiMap constructor failed to assign western neighbor"
    );
  }

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