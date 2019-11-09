package edu.louisville.traveler.hashi;

import org.junit.Test;

import static org.junit.Assert.*;

public class NeighborCheckerTest extends BaseHashiTest {
  @Test
  public void numberOfNeighbors() {
    assertEquals(2, NeighborChecker.numberOfNeighbors(hashiMap7x7Easy, island_0_0_3));
    assertEquals(1, NeighborChecker.numberOfNeighbors(hashiMap7x7Easy, island_0_3_2));
    assertEquals(3, NeighborChecker.numberOfNeighbors(hashiMap7x7Easy, islandEast));

    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    hashiMap7x7Empty.getIslands().add(islandTopRight);
    assertEquals(0, NeighborChecker.numberOfNeighbors(hashiMap7x7Empty, islandTopRight));
  }

}