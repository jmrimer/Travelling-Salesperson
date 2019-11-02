package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HashiSolverTest extends BaseHashiTest {
  @Test
  public void isSolvable() {
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  public void technique_JustEnoughNeighbor() {
    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    Island islandBottomRight = new Island(new Coordinates(6, 0), 1);
    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomRight);
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    assertEquals(
      List.of(new Bridge(islandBottomRight, islandTopRight)),
      hashiSolver.findSolution().getBridges()
    );


  }

  @Test
  public void noSolution_TooFewIslands() {
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    hashiMap7x7Empty.add(islandTopRight);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_AnyIslandHasNoNeighbors() {
    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    Island islandBottomLeft = new Island(new Coordinates(0, 0), 1);
    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomLeft);
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_6_4_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_TooFewNeighborsForPopulation() {
    Island islandTopRight = new Island(new Coordinates(6, 6), 3);
    Island islandBottomRight = new Island(new Coordinates(6, 0), 1);
    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomRight);
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_5_2_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }
}