package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashiSolutionCheckerTest extends BaseHashiTest {
  @Test
  void puzzleSolved() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    List<Bridge> bridges = List.of(
      new Bridge(islandCenter, islandEast)
    );
    islandCenter.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    assertTrue(HashiSolutionChecker.puzzleSolved(hashiMap, bridges));

    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    bridges = List.of(
      new Bridge(islandCenter, islandEast),
      new Bridge(islandCenter, islandEast)
    );
    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    assertTrue(HashiSolutionChecker.puzzleSolved(hashiMap, bridges));


    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    bridges = List.of(
      new Bridge(islandCenter, islandEast),
      new Bridge(islandCenter, islandNorth),
      new Bridge(islandCenter, islandWest),
      new Bridge(islandCenter, islandSouth)
    );

    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    islandNorth.decreaseAdjustedPopulation();
    islandWest.decreaseAdjustedPopulation();
    islandSouth.decreaseAdjustedPopulation();

    assertTrue(HashiSolutionChecker.puzzleSolved(hashiMap, bridges));
  }

  @Test
  void determinesIfAllIslandsConnect() {
    hashiMap = singleNeighborEastMap();
    List<Bridge> bridges = List.of(
      new Bridge(islandCenter, islandEast)
    );
    assertTrue(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));

    hashiMap = doubleNeighborEastNorthMap();
    bridges = List.of(
      new Bridge(islandCenter, islandEast),
      new Bridge(islandCenter, islandNorth)
    );
    assertTrue(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));

    hashiMap = tripleNeighborEastNorthWestMap();
    bridges = List.of(
      new Bridge(islandCenter, islandEast),
      new Bridge(islandCenter, islandNorth)
    );
    assertFalse(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));

    hashiMap = tripleNeighborEastNorthWestMap();
    bridges = List.of(
      new Bridge(islandNorth, islandEast),
      new Bridge(islandCenter, islandWest)
    );
    assertFalse(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));

    hashiMap = tripleNeighborEastNorthWestMap();
    bridges = List.of(
      new Bridge(islandNorth, islandEast),
      new Bridge(islandCenter, islandNorth),
      new Bridge(islandCenter, islandWest)
    );
    assertTrue(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));

    hashiMap = new HashiMap(
      7,
      List.of(
        island_3_2,
        island_3_3,
        island_4_2,
        island_4_3
      )
    );

    bridges = List.of(
      new Bridge(island_3_2, island_4_2),
      new Bridge(island_4_2, island_4_3),
      new Bridge(island_4_3, island_3_3),
      new Bridge(island_3_3, island_3_2)

    );
    assertTrue(HashiSolutionChecker.allIslandsConnect(hashiMap, bridges));
  }

  @Test
  void allBridgesBuilt() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    List<Bridge> bridges = List.of(
      new Bridge(islandCenter, islandEast),
      new Bridge(islandCenter, islandNorth),
      new Bridge(islandCenter, islandWest),
      new Bridge(islandCenter, islandSouth)
    );

    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandCenter.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    islandNorth.decreaseAdjustedPopulation();
    islandWest.decreaseAdjustedPopulation();
    islandSouth.decreaseAdjustedPopulation();

    assertTrue(HashiSolutionChecker.allBridgesBuilt(hashiMap, bridges));
  }
}