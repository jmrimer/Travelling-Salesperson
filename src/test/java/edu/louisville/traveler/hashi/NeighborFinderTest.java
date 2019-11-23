package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class NeighborFinderTest extends BaseHashiTest {
  @Test
  public void numberOfNeighbors() {
    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        island_2_3,
        island_2_4,
        island_4_2,
        island_4_3,
        island_4_4
      )
    );

    assertEquals(2, NeighborFinder.numberOfNeighbors(hashiMap, island_2_3));
    assertEquals(2, NeighborFinder.numberOfNeighbors(hashiMap, island_2_4));
    assertEquals(1, NeighborFinder.numberOfNeighbors(hashiMap, island_4_2));
    assertEquals(3, NeighborFinder.numberOfNeighbors(hashiMap, island_4_3));
    assertEquals(2, NeighborFinder.numberOfNeighbors(hashiMap, island_4_4));
  }

 @Test
  public void findAllNeighbors() {
   HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        island_2_3,
        island_2_4,
        island_4_2,
        island_4_3,
        island_4_4
      )
    );

   Map<Direction, Island> neighborsFor_island_2_3 = new HashMap<>();
   neighborsFor_island_2_3.put(Direction.NORTH, island_2_4);
   neighborsFor_island_2_3.put(Direction.EAST, island_4_3);

    assertEquals(
      neighborsFor_island_2_3,
      NeighborFinder.findNeighbors(hashiMap, island_2_3)
    );

    Map<Direction, Island> neighborsFor_island_4_3 = new HashMap<>();
   neighborsFor_island_4_3.put(Direction.NORTH, island_4_4);
   neighborsFor_island_4_3.put(Direction.SOUTH, island_4_2);
   neighborsFor_island_4_3.put(Direction.WEST, island_2_3);

    assertEquals(
      neighborsFor_island_4_3,
      NeighborFinder.findNeighbors(hashiMap, island_4_3)
    );
  }

  @Test
  void connectsAvailableNeighbors() {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    HashiSolution hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertEquals(islandEast, islandCenter.getNeighbors().get(Direction.EAST));
    assertEquals(islandCenter, islandEast.getNeighbors().get(Direction.WEST));
  }

  @Test
  void doesNotConnectIfBridgeIntersects() {
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

    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertTrue(island_0_1.getNeighbors().isEmpty());
    assertEquals(1, island_4_1.getNeighbors().size());
    assertEquals(island_4_2,island_4_1.getNeighbors().get(Direction.NORTH));
  }

  @Test
  void doesNotConnectIfNoRemainingPopulation() {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);


    islandNorth.decreaseAdjustedPopulation();

    HashiSolution hashiSolution = new HashiSolution(hashiMap);
    NeighborFinder.assignToIslands_AllAvailable(hashiSolution);

    assertEquals(islandEast, islandCenter.getNeighbors().get(Direction.EAST));
    assertNull(islandCenter.getNeighbors().get(Direction.NORTH));
  }
}