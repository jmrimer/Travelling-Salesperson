package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class HashiSolverSadPathTest extends BaseHashiTest {
  HashiSolver hashiSolver;

  @Test
  void notSolvableWhenAnyIslandWithoutNeighbors() {
    islandCenter.setPopulation(1);
    hashiMap = new HashiMap(7, List.of(islandCenter));
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void throwsErrorWhenNeighborCapacityNotEnough() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());


    islandCenter.setPopulation(3);
    islandEast.setPopulation(8);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_1_Impossible_Neighbor() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(0);
    islandEast.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_2_Impossible_Neighbors() {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_3_Impossible_Neighbors() {
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiMap = tripleNeighborEastNorthWestMap();
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());


    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    hashiMap = tripleNeighborEastNorthWestMap();
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_4_Impossible_Neighbors() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());


    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());


    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.solve();

    assertFalse(hashiSolver.isSolvable());
  }
}