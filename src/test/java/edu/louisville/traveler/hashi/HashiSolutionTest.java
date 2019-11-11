package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashiSolutionTest extends BaseHashiTest {
  @Test
  void updateMap_AdjustsConstraints() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    HashiSolution hashiSolution = new HashiSolution(hashiMap);

    assertEquals(0, hashiSolution.getHashiMap().getIslands().get(0).getConstraints().size());
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);

    hashiSolution.updateMap();

    assertEquals(
      List.of(1),
      hashiSolution.getHashiMap().getIslands().get(0).getConstraints().get(Direction.EAST)
    );

    assertEquals(
      List.of(1),
      islandCenter.getConstraints().get(Direction.EAST)
    );
  }

  @Test
  void addBridge_UpdatesMap() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    HashiSolution hashiSolution = new HashiSolution(hashiMap);

    assertEquals(0, hashiSolution.getHashiMap().getIslands().get(0).getConstraints().size());
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);

    hashiSolution.addBridge(new Bridge(islandCenter, islandEast));

    assertEquals(
      List.of(1),
      islandCenter.getConstraints().get(Direction.NORTH)
    );

    assertEquals(1, islandCenter.getAdjustedPopulation());
    assertEquals(0, islandEast.getAdjustedPopulation());
    assertEquals(1, islandCenter.getConstraints().size());
  }
}