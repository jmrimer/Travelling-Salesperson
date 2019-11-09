package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
      "HashiMap constructor failed to assign northern neighbor",
      islandNorth,
      islandCenter.getNeighbors().get(Direction.NORTH)
    );

    assertEquals(
      "HashiMap constructor failed to assign eastern neighbor",
      islandEast,
      islandCenter.getNeighbors().get(Direction.EAST)
    );

    assertEquals(
      "HashiMap constructor failed to assign southern neighbor",
      islandCenter,
      islandNorth.getNeighbors().get(Direction.SOUTH)
    );

    assertEquals(
      "HashiMap constructor failed to assign western neighbor",
      islandCenter,
      islandEast.getNeighbors().get(Direction.WEST)
    );
  }
}