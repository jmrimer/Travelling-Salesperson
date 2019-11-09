package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashiSolverTest {
  Island islandCenter;
  Island islandNorth;
  Island islandEast;
  Island islandSouth;
  Island islandWest;
  HashiMap hashiMap;
  HashiSolver hashiSolver;
  private List<Integer> constraint_0 = List.of(0);
  private List<Integer> constraint_1 = List.of(1);
  private List<Integer> constraint_2 = List.of(2);
  private List<Integer> constraint_0_1 = List.of(0, 1);
  private List<Integer> constraint_1_2 = List.of(1, 2);
  private List<Integer> constraint_0_1_2 = List.of(0, 1, 2);

  @BeforeEach
  public void setup() {
    islandCenter = new Island(new Coordinates(4, 4), 0);
    islandNorth = new Island(new Coordinates(4, 6), 0);
    islandEast = new Island(new Coordinates(6, 4), 0);
    islandSouth = new Island(new Coordinates(4, 2), 0);
    islandWest = new Island(new Coordinates(2, 4), 0);
  }

  @Test
  void constructorErrsWhenAnyIslandWithoutNeighbors() {
    hashiMap = new HashiMap(7, List.of(islandCenter));
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionAssignsConstraints_Root1_Single() throws UnsolvableHashiMap {
    singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);

    assertEquals(
      constraint_1,
      islandCenter.getConstraints().get(Direction.EAST)
    );
  }

  @Test
  void constructionAssignsConstraints_Root1_Double() throws UnsolvableHashiMap {
    doubleNeighborEastNorthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
  }

  @Test
  void constructionAssignsConstraints_Root1_Triple() throws UnsolvableHashiMap {
    tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root1_Quadruple() throws UnsolvableHashiMap {
    quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    hashiSolver = new HashiSolver(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
  }

  private void checkConstraint_0_1(Direction direction) {
    assertEquals(
      constraint_0_1,
      islandCenter.getConstraints().get(direction)
    );
  }

  private void singleNeighborEastMap() {
    hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast
      )
    );
  }

  private void doubleNeighborEastNorthMap() {
    hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth
      )
    );
  }

  private void tripleNeighborEastNorthWestMap() {
    hashiMap = new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast,
        islandNorth,
        islandWest
      )
    );
  }

  private void quadrupleNeighborEastNorthWestSouthMap() {
    hashiMap = new HashiMap(
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