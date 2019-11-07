package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

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


  @Test
  public void constructorAssignsConstraints_1Neighbor_1Population() {
    Island islandCenter;
    Island islandNorth;
    islandNorth = new Island(new Coordinates(0, 3), 1);
    islandCenter = new Island(new Coordinates(0, 0), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth
      )
    );

    assertEquals(List.of(1), islandNorth.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(1), islandCenter.getConstraints().get(Direction.NORTH));
  }

  @Test
  public void constructorAssignsConstraints_1Neighbor_2Population() {
    Island islandCenter;
    Island islandNorth;
    islandNorth = new Island(new Coordinates(0, 3), 2);
    islandCenter = new Island(new Coordinates(0, 0), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth
      )
    );

    assertEquals(List.of(2), islandNorth.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(2), islandCenter.getConstraints().get(Direction.NORTH));
  }

  @Test
  public void constructorAssignsConstraints_2Neighbors_1Population() {
    Island islandCenter;
    Island islandNorth;
    Island islandEast;
    islandNorth = new Island(new Coordinates(0, 3), 2);
    islandCenter = new Island(new Coordinates(0, 0), 1);
    islandEast = new Island(new Coordinates(2, 0), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth,
        islandEast
      )
    );

    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.NORTH));
    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.EAST));
  }

  @Test
  public void constructorAssignsConstraints_2Neighbors_2Population() {
    Island islandCenter;
    Island islandNorth;
    Island islandEast;
    islandNorth = new Island(new Coordinates(0, 3), 1);
    islandCenter = new Island(new Coordinates(0, 0), 2);
    islandEast = new Island(new Coordinates(2, 0), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth,
        islandEast
      )
    );

    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.NORTH));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.EAST));
  }

  @Test
  public void constructorAssignsConstraints_2Neighbors_3Population() {
    Island islandCenter;
    Island islandNorth;
    Island islandEast;
    islandNorth = new Island(new Coordinates(0, 3), 2);
    islandCenter = new Island(new Coordinates(0, 0), 3);
    islandEast = new Island(new Coordinates(2, 0), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandNorth,
        islandEast
      )
    );

    assertEquals(List.of(2), islandNorth.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(1, 2), islandCenter.getConstraints().get(Direction.NORTH));
    assertEquals(List.of(1, 2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(1), islandEast.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_2Neighbors_4Population() {
    Island island_0_0_4;
    Island islandNorth;
    Island islandEast;
    islandNorth = new Island(new Coordinates(0, 3), 2);
    island_0_0_4 = new Island(new Coordinates(0, 0), 4);
    islandEast = new Island(new Coordinates(2, 0), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        island_0_0_4,
        islandNorth,
        islandEast
      )
    );

    assertEquals(List.of(2), island_0_0_4.getConstraints().get(Direction.NORTH));
    assertEquals(List.of(2), island_0_0_4.getConstraints().get(Direction.EAST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_1Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 1);
    islandEast = new Island(new Coordinates(4, 2), 1);
    islandSouth = new Island(new Coordinates(2, 0), 1);
    islandWest = new Island(new Coordinates(0, 2), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_2Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 2);
    islandEast = new Island(new Coordinates(4, 2), 1);
    islandSouth = new Island(new Coordinates(2, 0), 1);
    islandWest = new Island(new Coordinates(0, 2), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_3Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 3);
    islandEast = new Island(new Coordinates(4, 2), 2);
    islandSouth = new Island(new Coordinates(2, 0), 2);
    islandWest = new Island(new Coordinates(0, 2), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_4Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 4);
    islandEast = new Island(new Coordinates(4, 2), 1);
    islandSouth = new Island(new Coordinates(2, 0), 1);
    islandWest = new Island(new Coordinates(0, 2), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(0, 1, 2), islandCenter.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_5Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 5);
    islandEast = new Island(new Coordinates(4, 2), 2);
    islandSouth = new Island(new Coordinates(2, 0), 2);
    islandWest = new Island(new Coordinates(0, 2), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(1, 2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(1, 2), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(1, 2), islandCenter.getConstraints().get(Direction.WEST));
  }

  @Test
  public void constructorAssignsConstraints_3Neighbors_6Population() {
    Island islandCenter;
    Island islandEast;
    Island islandSouth;
    Island islandWest;

    islandCenter = new Island(new Coordinates(2, 2), 6);
    islandEast = new Island(new Coordinates(4, 2), 2);
    islandSouth = new Island(new Coordinates(2, 0), 2);
    islandWest = new Island(new Coordinates(0, 2), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandSouth,
        islandWest
      )
    );

    assertEquals(List.of(2), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(2), islandCenter.getConstraints().get(Direction.SOUTH));
    assertEquals(List.of(2), islandCenter.getConstraints().get(Direction.WEST));
  }

}