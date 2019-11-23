package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class HashiSolverTest extends BaseHashiTest {
  HashiSolver hashiSolver;



  @Test
  void connects_5_Neighbors_to_ComplexMap() {
    island_2_3.setPopulation(1);
    island_3_2.setPopulation(2);
    island_3_3.setPopulation(4);
    island_3_4.setPopulation(1);
    island_4_2.setPopulation(1);
    island_5_3.setPopulation(1);
    hashiMap = new HashiMap(
      7,
      List.of(island_2_3,
        island_3_2,
        island_3_3,
        island_3_4,
        island_4_2,
        island_5_3
      )
    );

    assertThat(
      CertaintyConnector.connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(island_3_3, island_2_3),
        new Bridge(island_3_3, island_3_2),
        new Bridge(island_3_3, island_3_4),
        new Bridge(island_3_3, island_5_3),
        new Bridge(island_3_2, island_4_2)
      ).toArray())
    );
  }

  @Test
  void connectsOnlyCertainNeighborsInComplexMap() {

  }
}
