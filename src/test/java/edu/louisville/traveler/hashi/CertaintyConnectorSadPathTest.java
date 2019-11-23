package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CertaintyConnectorSadPathTest extends BaseHashiTest {
  @Test
  void secondCertaintyIntersectionThrowsError() {
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

    assertThrows(UnsolvableHashiMap.class, () -> CertaintyConnector.connect(hashiSolution));
  }
}