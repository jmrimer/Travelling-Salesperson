package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintAssignerSadPathTest extends BaseHashiTest {
  @Test
  void population_2_Neighbor_1_LowPopulation() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);

    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  public void roadsPreventNeighbors() {
    island_0_1.setPopulation(2);
    island_2_0.setPopulation(2);
    island_2_2.setPopulation(4);
    island_4_1.setPopulation(3);
    island_4_2.setPopulation(3);

    HashiMap hashiMap = new HashiMap(7, List.of(
      island_0_1,
      island_2_0,
      island_2_2,
      island_4_1,
      island_4_2
    ));

    List<Bridge> bridges = List.of(
      new Bridge(island_2_0, island_2_2),
      new Bridge(island_2_0, island_2_2),
      new Bridge(island_2_2, island_4_2),
      new Bridge(island_2_2, island_4_2)
    );

    HashiSolution hashiSolution = new HashiSolution(hashiMap);
    hashiSolution.setBridges(bridges);
    Map<Direction, Island> expectedNeighbor = new HashMap<>();
    expectedNeighbor.put(Direction.NORTH, island_4_2);

    ConstraintAssigner.assignConstraints(hashiSolution);

    assertEquals(expectedNeighbor, island_4_1.getNeighbors());
  }

}