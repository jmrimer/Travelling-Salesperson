package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConstraintAssignerSadPathTest extends BaseHashiTest {
  @Test
  void population_2_Neighbor_1_LowPopulation() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_3_Neighbor_2_LowPopulation() {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_4_Neighbor_2_LowPopulation() {
    hashiMap = doubleNeighborEastNorthMap();

    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_4_Neighbor_3_LowPopulation() {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_5_Neighbor_2_LowPopulation() {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_5_Neighbor_3_LowPopulation() {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_5_Neighbor_4_LowPopulation() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_6_Neighbor_3_LowPopulation() {
    hashiMap = tripleNeighborEastNorthWestMap();

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_6_Neighbor_4_LowPopulation() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();

    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_7_Neighbor_4_LowPopulation() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }

  @Test
  void population_8_Neighbor_4_LowPopulation() {
    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertThrows(UnsolvableHashiMap.class, () -> ConstraintAssigner.assignConstraints(hashiSolution));
  }
}