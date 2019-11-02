package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NeighborCheckerTest extends BaseHashiTest {
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

    assertEquals(2, NeighborChecker.numberOfNeighbors(hashiMap, island_2_3));
    assertEquals(2, NeighborChecker.numberOfNeighbors(hashiMap, island_2_4));
    assertEquals(1, NeighborChecker.numberOfNeighbors(hashiMap, island_4_2));
    assertEquals(3, NeighborChecker.numberOfNeighbors(hashiMap, island_4_3));
    assertEquals(2, NeighborChecker.numberOfNeighbors(hashiMap, island_4_4));
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
      NeighborChecker.findAllNeighbors(hashiMap, island_2_3)
    );

    Map<Direction, Island> neighborsFor_island_4_3 = new HashMap<>();
   neighborsFor_island_4_3.put(Direction.NORTH, island_4_4);
   neighborsFor_island_4_3.put(Direction.SOUTH, island_4_2);
   neighborsFor_island_4_3.put(Direction.WEST, island_2_3);

    assertEquals(
      neighborsFor_island_4_3,
      NeighborChecker.findAllNeighbors(hashiMap, island_4_3)
    );
  }

}