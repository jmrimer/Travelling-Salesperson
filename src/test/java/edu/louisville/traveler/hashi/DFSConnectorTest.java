package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.louisville.traveler.hashi.DFSConnector.connect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class DFSConnectorTest extends BaseHashiTest{
  @Test
  void connects_4Island_Complex() {
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
    HashiSolution hashiSolution = new HashiSolution(hashiMap);

    connect(hashiSolution);
    List<Bridge> bridges = hashiSolution.getBridges();
    assertEquals(4, bridges.size());
    assertThat(
      bridges,
      containsInAnyOrder(List.of(
        new Bridge(island_3_2, island_4_2),
        new Bridge(island_4_2, island_4_3),
        new Bridge(island_4_3, island_3_3),
        new Bridge(island_3_3, island_3_2)
      ).toArray())
    );
  }
}