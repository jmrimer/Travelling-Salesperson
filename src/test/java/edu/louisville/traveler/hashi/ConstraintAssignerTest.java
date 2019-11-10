package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConstraintAssignerTest {
  Island islandCenter;
  Island islandNorth;
  Island islandEast;
  Island islandSouth;
  Island islandWest;
  HashiMap hashiMap;
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
  void removeConstraintsWhenAdjustedPopulation0() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    assertEquals(
      constraint_1,
      islandCenter.getConstraints().get(Direction.EAST)
    );

    islandCenter.decreaseAdjustedPopulation();
    ConstraintAssigner.assignConstraints(hashiMap);
    assertTrue(islandCenter.getConstraints().isEmpty());
  }

  @Test
  void neighborWithAdjustPopulation0ClearsConstraintsForDirection() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    assertEquals(
      constraint_1,
      islandCenter.getConstraints().get(Direction.EAST)
    );

    islandEast.decreaseAdjustedPopulation();
    ConstraintAssigner.assignConstraints(hashiMap);
    assertTrue(islandEast.getConstraints().isEmpty());
    assertTrue(islandCenter.getConstraints().isEmpty());
  }

  @Test
  void constructionAssignsConstraints_Root1_Single() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    assertEquals(
      constraint_1,
      islandCenter.getConstraints().get(Direction.EAST)
    );
  }

  @Test
  void constructionAssignsConstraints_Root1_DoubleNeighbor() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
  }

  @Test
  void constructionAssignsConstraints_Root1_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root1_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    ConstraintAssigner.assignConstraints(hashiMap);

    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root2_Single() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);

    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandEast.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);

    checkConstraint_2(Direction.EAST);
  }

  @Test
  void constructionAssignsConstraints_Root2_DoubleNeighbor() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.EAST);
    checkConstraint_1(Direction.NORTH);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_1_2(Direction.EAST);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.EAST);

    islandCenter.setAdjustedPopulation(1);
    islandEast.setAdjustedPopulation(0);
    islandNorth.setAdjustedPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.NORTH);
    assertEquals(1, islandCenter.getConstraints().size());
  }

  @Test
  void constructionAssignsConstraints_Root2_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root2_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root3_DoubleNeighbor() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.NORTH);
    checkConstraint_2(Direction.EAST);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_1_2(Direction.EAST);
  }

  @Test
  void constructionAssignsConstraints_Root3_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.EAST);
    checkConstraint_1(Direction.NORTH);
    checkConstraint_1(Direction.WEST);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root3_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root4_DoubleNeighbor() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_2(Direction.EAST);
  }

  @Test
  void constructionAssignsConstraints_Root4_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_1(Direction.NORTH);
    checkConstraint_1(Direction.WEST);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root4_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.EAST);
    checkConstraint_1(Direction.NORTH);
    checkConstraint_1(Direction.WEST);
    checkConstraint_1(Direction.SOUTH);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root5_DoubleNeighbor()  {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionAssignsConstraints_Root5_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_1(Direction.WEST);

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_1_2(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root5_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(5);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_1(Direction.NORTH);
    checkConstraint_1(Direction.WEST);
    checkConstraint_1(Direction.SOUTH);

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_0_1(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root6_Triple() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_2(Direction.WEST);
  }

  @Test
  void constructionAssignsConstraints_Root6_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_1(Direction.WEST);
    checkConstraint_1(Direction.SOUTH);

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_1_2(Direction.WEST);
    checkConstraint_0_1(Direction.SOUTH);

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.EAST);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.WEST);
    checkConstraint_0_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root7_Quadruple() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();


    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_2(Direction.WEST);
    checkConstraint_1(Direction.SOUTH);

    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1_2(Direction.EAST);
    checkConstraint_1_2(Direction.NORTH);
    checkConstraint_1_2(Direction.WEST);
    checkConstraint_1_2(Direction.SOUTH);
  }

  @Test
  void constructionAssignsConstraints_Root8_Quadruple() throws UnsolvableHashiMap {
    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_2(Direction.WEST);
    checkConstraint_2(Direction.SOUTH);
  }

  private void checkConstraint_1(Direction direction) {
    assertEquals(
      constraint_1,
      islandCenter.getConstraints().get(direction)
    );
  }

  private void checkConstraint_2(Direction direction) {
    assertEquals(
      constraint_2,
      islandCenter.getConstraints().get(direction)
    );
  }

  private void checkConstraint_0_1(Direction direction) {
    assertEquals(
      constraint_0_1,
      islandCenter.getConstraints().get(direction)
    );
  }

  private void checkConstraint_1_2(Direction direction) {
    assertEquals(
      constraint_1_2,
      islandCenter.getConstraints().get(direction)
    );
  }

  private void checkConstraint_0_1_2(Direction direction) {
    assertEquals(
      constraint_0_1_2,
      islandCenter.getConstraints().get(direction)
    );
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

  private HashiMap tripleNeighborEastNorthWestMap() {
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

  private HashiMap quadrupleNeighborEastNorthWestSouthMap() {
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