package edu.louisville.traveler.hashi;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class BaseHashiTest {
  HashiSolver hashiSolver;
  HashiMap hashiMapSolvable_2Island;
  HashiMap hashiMapSolvable_3Island;
  HashiMap hashiMapSolvable_4Island_3Neighbor;
  HashiMap hashiMapSolvable_5Island_4Neighbor;
  HashiMap hashiMap7x7Easy;
  HashiMap hashiMap7x7Empty;
  HashiMap hashiMapUnsolvableSingleIsland;
  HashiMap hashiMap_Unsolvable_IslandWithoutNeighbor;
  HashiMap hashiMap_Unsolvable_IslandWithTooFewNeighbors;
  Island island_0_0_3;
  Island island_0_3_2;
  Island island_2_0_2;
  Island island_5_1_3;
  Island island_6_2_3;
  Island island_6_4_3;
  Island islandTopRight_Pop1;
  Island islandBottomRight;
  Island islandBottomLeft;
  Island islandTopRight_Pop3;
  Island islandTopLeft;
  List<Island> easyIslands;
  Island island_5_5_1;
  Island island_6_6_2;
  Island island_3_6_4;
  Island island_3_2_1;
  Island island_1_6_3;
  Island island_5_2_8;

  @Before
  public void setup() {
    hashiSolver = new HashiSolver();
    hashiMap7x7Empty = new HashiMap(7, new ArrayList<>());

    islandTopRight_Pop1 = new Island(new Coordinates(6, 6), 1);
    islandTopRight_Pop3 = new Island(new Coordinates(6, 6), 3);
    islandBottomRight = new Island(new Coordinates(6, 0), 1);
    islandBottomLeft = new Island(new Coordinates(0, 0), 1);
    islandTopLeft = new Island(new Coordinates(0, 6), 1);

    island_0_0_3 = new Island(new Coordinates(0, 0), 3);
    island_0_3_2 = new Island(new Coordinates(0, 3), 2);
    island_1_6_3 = new Island(new Coordinates(1, 6), 3);
    island_2_0_2 = new Island(new Coordinates(2, 0), 2);
    island_5_1_3 = new Island(new Coordinates(5, 1), 3);
    island_3_2_1 = new Island(new Coordinates(3, 2), 1);
    island_3_6_4 = new Island(new Coordinates(3, 6), 4);
    island_5_5_1 = new Island(new Coordinates(5, 5), 1);
    island_6_2_3 = new Island(new Coordinates(6, 2), 3);
    island_6_4_3 = new Island(new Coordinates(6, 4), 3);
    island_6_6_2 = new Island(new Coordinates(6, 6), 2);
    easyIslands = List.of(
      island_0_0_3,
      island_0_3_2,
      new Island(new Coordinates(1, 1), 3),
      island_1_6_3,
      island_2_0_2,
      island_3_2_1,
      island_3_6_4,
      island_5_1_3,
      island_5_5_1,
      new Island(new Coordinates(6, 0), 2),
      island_6_2_3,
      island_6_4_3,
      island_6_6_2
    );

    hashiMapUnsolvableSingleIsland = new HashiMap(
      7,
      List.of(islandTopRight_Pop1)
    );

    hashiMap_Unsolvable_IslandWithoutNeighbor =
      new HashiMap(
        7,
        List.of(
          islandTopRight_Pop1,
          islandBottomLeft
        )
      );

    hashiMap_Unsolvable_IslandWithTooFewNeighbors = new HashiMap(
      7,
      List.of(
        islandTopRight_Pop3,
        islandBottomRight
      )
    );

    hashiMapSolvable_2Island = new HashiMap(
      7,
      List.of(
        islandTopRight_Pop1,
        islandBottomRight
      )
    );

    hashiMapSolvable_3Island = new HashiMap(
      7,
      List.of(
        islandTopLeft,
        islandBottomLeft,
        islandBottomRight
      )
    );

    hashiMapSolvable_4Island_3Neighbor = new HashiMap(
      7,
      List.of(
        island_3_6_4,
        island_3_2_1,
        island_1_6_3,
        island_6_6_2
      )
    );

    island_5_2_8 = new Island(new Coordinates(5, 2), 8);
    hashiMapSolvable_5Island_4Neighbor = new HashiMap(
      7,
      List.of(
        island_3_2_1,
        island_5_1_3,
        island_5_5_1,
        island_6_2_3,
        island_5_2_8
      )
    );
    hashiMap7x7Easy = new HashiMap(
      7,
      easyIslands
    );
  }
}
