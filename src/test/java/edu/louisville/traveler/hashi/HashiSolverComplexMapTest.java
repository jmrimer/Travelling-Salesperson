package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class HashiSolverComplexMapTest extends BaseHashiTest {
  HashiMap hashiMap;
  HashiSolver hashiSolver;

  @Test
  void Solves_Complex_4_Island() throws UnsolvableHashiMap {
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(2);
    island_4_2.setPopulation(2);
    island_4_3.setPopulation(2);
    hashiMap = new HashiMap(
      7,
      List.of(
        island_3_2,
        island_3_3,
        island_4_2,
        island_4_3
      )
    );

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    hashiSolver.getBridges().forEach(System.out::println);
    assertThat(
      hashiSolver.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(island_3_2, island_4_2),
        new Bridge(island_4_2, island_4_3),
        new Bridge(island_4_3, island_3_3),
        new Bridge(island_3_3, island_3_2)

      ).toArray())
    );
    hashiSolver.getHashiMap().getIslands().forEach(
      island -> assertEquals(0, island.getAdjustedPopulation(), "Adjusted Pop fail for " + island)
    );

    assertTrue(HashiSolutionChecker.allBridgesBuilt(hashiSolver.getHashiMap(), hashiSolver.getBridges()));
    assertTrue(HashiSolutionChecker.allIslandsConnect(hashiSolver.getHashiMap(), hashiSolver.getBridges()));
    assertTrue(hashiSolver.isSolvable());
  }


  @Test
  void Fails_Simple_6_Island() throws UnsolvableHashiMap {
    island_2_3.setPopulation(1);
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(5);
    island_3_4.setPopulation(1);
    island_4_2.setPopulation(3);
    island_4_3.setPopulation(1);
    hashiMap = new HashiMap(
      7,
      List.of(
        island_2_3,
        island_3_2,
        island_3_3,
        island_3_4,
        island_4_2,
        island_4_3
      )
    );

    hashiSolver = new HashiSolver(hashiMap);
    assertThrows(UnsolvableHashiMap.class, () -> hashiSolver.solve());
  }

  @Test
  void Solves_Simple_5_Island() throws UnsolvableHashiMap {
    island_2_3.setPopulation(1);
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(4);
    island_3_4.setPopulation(1);
    island_4_2.setPopulation(1);
    island_5_3.setPopulation(1);
    hashiMap = new HashiMap(
      7,
      List.of(
        island_2_3,
        island_3_2,
        island_3_3,
        island_3_4,
        island_4_2,
        island_5_3
      )
    );

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    assertTrue(hashiSolver.isSolvable());
  }

  @Disabled
  @Test
  void Solves_Feasible_6_Island() throws UnsolvableHashiMap {
    island_2_3.setPopulation(1);
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(4);
    island_3_4.setPopulation(1);
    island_4_2.setPopulation(2);
    island_4_3.setPopulation(2);
    hashiMap = new HashiMap(
      7,
      List.of(
        island_2_3,
        island_3_2,
        island_3_3,
        island_3_4,
        island_4_2,
        island_4_3
      )
    );

    hashiSolver = new HashiSolver(hashiMap);
    hashiSolver.solve();
    assertTrue(hashiSolver.isSolvable());
  }
}