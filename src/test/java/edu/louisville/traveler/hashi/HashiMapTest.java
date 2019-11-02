package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashiMapTest {
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
}