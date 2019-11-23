package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConstraintAssignerHappyPathTest  extends BaseHashiTest {
  private List<Integer> constraint_1 = List.of(1);
  private List<Integer> constraint_2 = List.of(2);
  private List<Integer> constraint_0_1 = List.of(0, 1);
  private List<Integer> constraint_1_2 = List.of(1, 2);
  private List<Integer> constraint_0_1_2 = List.of(0, 1, 2);

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
    islandEast.decreaseAdjustedPopulation();
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

    islandCenter.decreaseAdjustedPopulation();
    islandEast.decreaseAdjustedPopulation();
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    ConstraintAssigner.assignConstraints(hashiSolution);
    assertTrue(islandEast.getConstraints().isEmpty());
    assertTrue(islandCenter.getConstraints().isEmpty());
  }

  @Test
  void population_1_Neighbor_1() throws UnsolvableHashiMap {
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
  void population_1_Neighbor_2() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.EAST);
    checkConstraint_0_1(Direction.NORTH);
  }

  @Test
  void population_1_Neighbor_3() throws UnsolvableHashiMap {
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
  void population_1_Neighbor_4() throws UnsolvableHashiMap {
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
  void population_2_Neighbor_1() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.EAST);
  }

  @Test
  void population_2_Neighbor_2_SingleConstraint() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.EAST);
    checkConstraint_1(Direction.NORTH);
  }

  @Test
  void population_2_Neighbor_2_MultiConstraint() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1(Direction.NORTH);
    checkConstraint_1_2(Direction.EAST);
  }

  @Test
  void population_2_Neighbor_2_MaxConstraint() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_0_1_2(Direction.NORTH);
    checkConstraint_0_1_2(Direction.EAST);
  }

  @Test
  void population_2_Neighbor_2_AdjustPopulation() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandCenter.setAdjustedPopulation(1);
    islandEast.setAdjustedPopulation(0);
    islandNorth.setAdjustedPopulation(1);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_1(Direction.NORTH);
    assertEquals(1, islandCenter.getConstraints().size());
  }

  @Test
  void population_2_Neighbor_3_MultiConstraint() throws UnsolvableHashiMap {
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
  }

  @Test
  void population_2_Neighbor_3_MaxConstraint() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
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
  void population_2_Neighbor_4() throws UnsolvableHashiMap {
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
  void population_3_Neighbor_2() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();

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
  void population_3_Neighbor_3() throws UnsolvableHashiMap {
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
  void population_3_Neighbor_4() throws UnsolvableHashiMap {
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
  void population_4_Neighbor_2() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    ConstraintAssigner.assignConstraints(hashiMap);
    checkConstraint_2(Direction.NORTH);
    checkConstraint_2(Direction.EAST);
  }

  @Test
  void population_4_Neighbor_3() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
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
  void population_4_Neighbor_4() throws UnsolvableHashiMap {
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
  void population_5_Neighbor_3() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
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
  void population_5_Neighbor_4() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
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
  void population_6_Neighbor_3() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
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
  void population_6_Neighbor_4() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

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
  void population_7_Neighbor_4() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
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
  void population_8_Neighbor_4() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
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
}