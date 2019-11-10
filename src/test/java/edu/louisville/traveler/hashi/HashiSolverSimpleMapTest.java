package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class HashiSolverSimpleMapTest extends BaseHashiTest {
  HashiSolver hashiSolver;

  @Test
  void constructionErrsWhenAnyIslandWithoutNeighbors() {
    islandCenter.setPopulation(1);
    hashiMap = new HashiMap(7, List.of(islandCenter));
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionErrsWhenNeighborCapacityNotEnough() {
    hashiMap = singleNeighborEastMap();

    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(3);
    islandEast.setPopulation(8);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionErrsOnMapsWithFailedConstraints() {
    island_2_3.setPopulation(1);
    island_2_4.setPopulation(2);
    island_4_2.setPopulation(1);
    island_4_3.setPopulation(2);
    island_4_4.setPopulation(2);

    List<Island> islands = List.of(
      island_2_3,
      island_2_4,
      island_4_2,
      island_4_3,
      island_4_4
    );
    hashiMap = new HashiMap(7, islands);
    try {
      hashiSolver = new HashiSolver(hashiMap);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      assertTrue(false, "Construction threw error");
      unsolvableHashiMap.printStackTrace();
    }

    island_2_3.setPopulation(1);
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(5);
    island_3_4.setPopulation(1);
    island_4_2.setPopulation(3);
    island_4_3.setPopulation(1);
    islands = new ArrayList<>(List.of(
      island_2_3,
      island_3_2,
      island_3_3,
      island_3_4,
      island_4_2,
      island_4_3
    ));
    hashiMap = new HashiMap(7, islands);
    try {
      hashiSolver = new HashiSolver(hashiMap);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      assertTrue(false, "Construction threw error");
      unsolvableHashiMap.printStackTrace();
    }

    island_5_2.setPopulation(3);
    islands.add(island_5_2);
    hashiMap = new HashiMap(7, islands);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void solves_1_Feasible_Neighbor() throws UnsolvableHashiMap {
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    hashiMap = singleNeighborEastMap();

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    assertTrue(hashiSolver.isSolvable());

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    hashiMap = singleNeighborEastMap();

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void solves_2_Feasible_Neighbors() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void solves_3_Feasible_Neighbors() throws UnsolvableHashiMap {
//    Root 3
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

//    Root 4
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void solves_4_Feasible_Neighbors() throws UnsolvableHashiMap {
//    Root 4
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

//  Root 5
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

//  Root 6
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());

    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    checkAdjustPopulationsZero();
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void fails_1_Impossible_Neighbor() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(0);
    islandEast.setPopulation(1);

    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void fails_2_Impossible_Neighbors() throws UnsolvableHashiMap {

    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    hashiMap = doubleNeighborEastNorthMap();
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMap);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_3_Impossible_Neighbors() throws UnsolvableHashiMap {
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiMap = tripleNeighborEastNorthWestMap();
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    hashiMap = tripleNeighborEastNorthWestMap();
    hashiSolver = new HashiSolver(hashiMap);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  void fails_4_Impossible_Neighbors() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolver = new HashiSolver(hashiMap);
    assertFalse(hashiSolver.isSolvable());

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    assertFalse(hashiSolver.isSolvable());
  }

  private void checkAdjustPopulationsZero() {
    assertEquals(0, islandCenter.getAdjustedPopulation());
    assertEquals(0, islandEast.getAdjustedPopulation());
    assertEquals(0, islandNorth.getAdjustedPopulation());
    assertEquals(0, islandWest.getAdjustedPopulation());
    assertEquals(0, islandSouth.getAdjustedPopulation());
  }
}