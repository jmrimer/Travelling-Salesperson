package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class HashiSolverTest extends BaseHashiTest {
  Island islandCenter;
  Island islandNorth;
  Island islandEast;
  Island islandSouth;
  Island islandWest;
  HashiMap hashiMap;
  HashiSolver hashiSolver;

  @BeforeEach
  public void setup() {
    super.setup();
    islandCenter = new Island(new Coordinates(4, 4), 0);
    islandNorth = new Island(new Coordinates(4, 6), 0);
    islandEast = new Island(new Coordinates(6, 4), 0);
    islandSouth = new Island(new Coordinates(4, 2), 0);
    islandWest = new Island(new Coordinates(2, 4), 0);
  }

  @Test
  void constructionErrsWhenAnyIslandWithoutNeighbors() {
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
  void buildsFirstBridges_1to1() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();

    assertEquals(
      List.of(new Bridge(islandCenter, islandEast)),
      hashiSolver.getBridges()
    );

    assertEquals(0, islandCenter.getAdjustedPopulation());
    assertEquals(0, islandEast.getAdjustedPopulation());
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void buildsFirstBridges_2to2() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();

    assertEquals(
      List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast)
      ),
      hashiSolver.getBridges()
    );

    assertEquals(0, islandCenter.getAdjustedPopulation());
    assertEquals(0, islandEast.getAdjustedPopulation());

    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  void buildsFirstBridges_2_Neighbors() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();

    assertThat(
      hashiSolver.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );

    assertEquals(0, islandCenter.getAdjustedPopulation());
    assertEquals(0, islandEast.getAdjustedPopulation());
    assertEquals(0, islandNorth.getAdjustedPopulation());
  }

  @Test
  void bridgeBuilderAdjustConstraintsOnEachBuild() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.buildBridgesFor(islandCenter);

    assertThat(
      hashiSolver.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );

    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.NORTH));
    assertEquals(List.of(0, 1), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(1), islandEast.getConstraints().get(Direction.WEST));
    assertEquals(List.of(1), islandNorth.getConstraints().get(Direction.SOUTH));


  }

  @Test
  void bridgeBuilderThrowsErrorWhenCannotBuild() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMap);

    hashiSolver.buildBridgesFor(islandCenter);
    hashiSolver.buildBridgesFor(islandEast);

    assertThat(
      hashiSolver.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );

    assertThrows(UnsolvableHashiMap.class, () -> hashiSolver.buildBridgesFor(islandNorth));

  }

  private HashiMap singleNeighborEastMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast
      )
    );
  }

  private HashiMap doubleNeighborEastNorthMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth
      )
    );
  }

}