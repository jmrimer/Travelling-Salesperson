package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HashiSolverTest extends BaseHashiTest {
  @Test
  public void isSolvable() {
    hashiSolver = new HashiSolver(hashiMapSolvable2Island);
    assertTrue(hashiSolver.isSolvable());

    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  public void technique_JustEnoughNeighbors() {
    hashiSolver = new HashiSolver(hashiMapSolvable2Island);
    assertEquals(
      List.of(new Bridge(islandBottomRight, islandTopRight_Pop1)),
      hashiSolver.findSolution().getBridges()
    );

    islandTopRight_Pop1.setPopulation(2);
    islandBottomRight.setPopulation(2);
    assertEquals(
      List.of(
        new Bridge(islandBottomRight, islandTopRight_Pop1),
        new Bridge(islandTopRight_Pop1, islandBottomRight)
      ),
      hashiSolver.findSolution().getBridges()
    );
  }

  @Test
  public void noSolution_TooFewIslands() {
    hashiSolver = new HashiSolver(hashiMapUnsolvableSingleIsland);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_AnyIslandHasNoNeighbors() {
    hashiSolver = new HashiSolver(hashiMap_Unsolvable_IslandWithoutNeighbor);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_6_4_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_TooFewNeighborsForPopulation() {
    hashiSolver = new HashiSolver(hashiMap_Unsolvable_IslandWithTooFewNeighbors);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_5_2_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }
}