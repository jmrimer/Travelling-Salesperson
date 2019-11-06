package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class HashiSolverTest extends BaseHashiTest {
  @Test
  public void isSolvable() {
    hashiSolver = new HashiSolver(hashiMapSolvable_2Island);
    assertTrue(hashiSolver.isSolvable());

    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  public void technique_JustEnoughNeighbors_1() {
    hashiSolver = new HashiSolver(hashiMapSolvable_2Island);
    assertEquals(
      List.of(new Bridge(islandBottomRight, islandTopRight_Pop1)),
      hashiSolver.findSolution().getBridges()
    );

    islandTopRight_Pop1.setPopulation(2);
    islandBottomRight.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMapSolvable_2Island);
    assertEquals(
      List.of(
        new Bridge(islandBottomRight, islandTopRight_Pop1),
        new Bridge(islandTopRight_Pop1, islandBottomRight)
      ),
      hashiSolver.findSolution().getBridges()
    );
  }

  @Test
  public void technique_JustEnoughNeighbors_2Neighbors() {
    islandTopLeft.setPopulation(2);
    islandBottomLeft.setPopulation(4);
    islandBottomRight.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMapSolvable_3Island);
    assertEquals(
      List.of(
        new Bridge(islandTopLeft, islandBottomLeft),
        new Bridge(islandTopLeft, islandBottomLeft),
        new Bridge(islandBottomLeft, islandBottomRight),
        new Bridge(islandBottomLeft, islandBottomRight)
      ),
      hashiSolver.findSolution().getBridges()
    );
  }

  @Test
  public void technique_JustEnoughNeighbors_3Neighbors() {
    island_3_6_4.setPopulation(6);
    island_3_2_1.setPopulation(2);
    island_1_6_3.setPopulation(2);
    island_6_6_2.setPopulation(2);
    hashiSolver = new HashiSolver(hashiMapSolvable_4Island_3Neighbor);
    List<Bridge> bridges = hashiSolver.findSolution().getBridges();

    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_3_6_4, island_6_6_2)
      )
    );
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_3_6_4, island_3_2_1)
      )
    );
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_3_6_4, island_1_6_3)
      )
    );
  }

  @Test
  public void technique_JustEnoughNeighbors_4Neighbors() {
    island_3_2_1.setPopulation(2);
    island_5_1_3.setPopulation(2);
    island_5_5_1.setPopulation(2);
    island_6_2_3.setPopulation(2);
    island_5_2_8.setPopulation(8);

    hashiSolver = new HashiSolver(hashiMapSolvable_5Island_4Neighbor);
    List<Bridge> bridges = hashiSolver.findSolution().getBridges();
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_5_2_8, island_3_2_1)
      )
    );
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_5_2_8, island_5_1_3)
      )
    );
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_5_2_8, island_5_5_1)
      )
    );
    assertEquals(
      2,
      Collections.frequency(
        bridges,
        new Bridge(island_5_2_8, island_6_2_3)
      )
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

    hashiMap7x7Easy.getIslands().remove(island_5_1_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }
}