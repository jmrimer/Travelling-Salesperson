package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class BaseHashiTest {
  public Island island_0_0;
  public Island island_0_1;
  public Island island_0_2;
  public Island island_0_3;
  public Island island_0_4;
  public Island island_0_5;
  public Island island_0_6;
  public Island island_1_0;
  public Island island_1_1;
  public Island island_1_2;
  public Island island_1_3;
  public Island island_1_4;
  public Island island_1_5;
  public Island island_1_6;
  public Island island_2_0;
  public Island island_2_1;
  public Island island_2_2;
  public Island island_2_3;
  public Island island_2_4;
  public Island island_2_5;
  public Island island_2_6;
  public Island island_3_0;
  public Island island_3_1;
  public Island island_3_2;
  public Island island_3_3;
  public Island island_3_4;
  public Island island_3_5;
  public Island island_3_6;
  public Island island_4_0;
  public Island island_4_1;
  public Island island_4_2;
  public Island island_4_3;
  public Island island_4_4;
  public Island island_4_5;
  public Island island_4_6;
  public Island island_5_0;
  public Island island_5_1;
  public Island island_5_2;
  public Island island_5_3;
  public Island island_5_4;
  public Island island_5_5;
  public Island island_5_6;
  public Island island_6_0;
  public Island island_6_1;
  public Island island_6_2;
  public Island island_6_3;
  public Island island_6_4;
  public Island island_6_5;
  public Island island_6_6;

  public Island islandCenter;
  public Island islandNorth;
  public Island islandEast;
  public Island islandSouth;
  public Island islandWest;
  public HashiMap hashiMap;

  @BeforeEach
  public void setup() {
    islandCenter = new Island(new Coordinates(4, 4), 0);
    islandNorth = new Island(new Coordinates(4, 6), 0);
    islandEast = new Island(new Coordinates(6, 4), 0);
    islandSouth = new Island(new Coordinates(4, 2), 0);
    islandWest = new Island(new Coordinates(2, 4), 0);

    island_0_0 = new Island(new Coordinates(0, 0), 0);
    island_0_1 = new Island(new Coordinates(0, 1), 0);
    island_0_2 = new Island(new Coordinates(0, 2), 0);
    island_0_3 = new Island(new Coordinates(0, 3), 0);
    island_0_4 = new Island(new Coordinates(0, 4), 0);
    island_0_5 = new Island(new Coordinates(0, 5), 0);
    island_0_6 = new Island(new Coordinates(0, 6), 0);
    island_1_0 = new Island(new Coordinates(1, 0), 0);
    island_1_1 = new Island(new Coordinates(1, 1), 0);
    island_1_2 = new Island(new Coordinates(1, 2), 0);
    island_1_3 = new Island(new Coordinates(1, 3), 0);
    island_1_4 = new Island(new Coordinates(1, 4), 0);
    island_1_5 = new Island(new Coordinates(1, 5), 0);
    island_1_6 = new Island(new Coordinates(1, 6), 0);
    island_2_0 = new Island(new Coordinates(2, 0), 0);
    island_2_1 = new Island(new Coordinates(2, 1), 0);
    island_2_2 = new Island(new Coordinates(2, 2), 0);
    island_2_3 = new Island(new Coordinates(2, 3), 0);
    island_2_4 = new Island(new Coordinates(2, 4), 0);
    island_2_5 = new Island(new Coordinates(2, 5), 0);
    island_2_6 = new Island(new Coordinates(2, 6), 0);
    island_3_0 = new Island(new Coordinates(3, 0), 0);
    island_3_1 = new Island(new Coordinates(3, 1), 0);
    island_3_2 = new Island(new Coordinates(3, 2), 0);
    island_3_3 = new Island(new Coordinates(3, 3), 0);
    island_3_4 = new Island(new Coordinates(3, 4), 0);
    island_3_5 = new Island(new Coordinates(3, 5), 0);
    island_3_6 = new Island(new Coordinates(3, 6), 0);
    island_4_0 = new Island(new Coordinates(4, 0), 0);
    island_4_1 = new Island(new Coordinates(4, 1), 0);
    island_4_2 = new Island(new Coordinates(4, 2), 0);
    island_4_3 = new Island(new Coordinates(4, 3), 0);
    island_4_4 = new Island(new Coordinates(4, 4), 0);
    island_4_5 = new Island(new Coordinates(4, 5), 0);
    island_4_6 = new Island(new Coordinates(4, 6), 0);
    island_5_0 = new Island(new Coordinates(5, 0), 0);
    island_5_1 = new Island(new Coordinates(5, 1), 0);
    island_5_2 = new Island(new Coordinates(5, 2), 0);
    island_5_3 = new Island(new Coordinates(5, 3), 0);
    island_5_4 = new Island(new Coordinates(5, 4), 0);
    island_5_5 = new Island(new Coordinates(5, 5), 0);
    island_5_6 = new Island(new Coordinates(5, 6), 0);
    island_6_0 = new Island(new Coordinates(6, 0), 0);
    island_6_1 = new Island(new Coordinates(6, 1), 0);
    island_6_2 = new Island(new Coordinates(6, 2), 0);
    island_6_3 = new Island(new Coordinates(6, 3), 0);
    island_6_4 = new Island(new Coordinates(6, 4), 0);
    island_6_5 = new Island(new Coordinates(6, 5), 0);
    island_6_6 = new Island(new Coordinates(6, 6), 0);
  }

  public HashiMap singleNeighborEastMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast
      )
    );
  }

  public HashiMap doubleNeighborEastNorthMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth
      )
    );
  }

  public HashiMap tripleNeighborEastNorthWestMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth,
        islandWest
      )
    );
  }

  public HashiMap quadrupleNeighborEastNorthWestSouthMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth,
        islandWest,
        islandSouth
      )
    );
  }

}
