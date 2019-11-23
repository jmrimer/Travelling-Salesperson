package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertaintyConnectorHappyPathTest extends BaseHashiTest {
  @Test
  void returnsEmpty_No_CertainNeighbors() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(0);
    islandEast.setPopulation(1);

    assertTrue(CertaintyConnector.connect(hashiMap).isEmpty());

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
    assertTrue(CertaintyConnector.connect(hashiMap).isEmpty());
  }

  @Test
  void connects_1_Neighbor_to_Population_1() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast)
      ).toArray())
    );
  }

  @Test
  void connects_1_Neighbor_to_Population_2() throws UnsolvableHashiMap {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast)
      ).toArray())
    );
  }

  @Test
  void connects_2_Neighbors_to_Population_3() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );
  }

  @Test
  void connects_2_Neighbors_to_Population_4() throws UnsolvableHashiMap {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );
  }

  @Test
  void connects_3_Neighbors_to_Population_3() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest)
      ).toArray())
    );
  }

  @Test
  void connects_3_Neighbors_to_Population_4() throws UnsolvableHashiMap {
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest)
      ).toArray())
    );
  }

  @Test
  void connects_4_Neighbors_to_Population_4() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );
  }

  @Test
  void connects_4_Neighbors_to_Population_5() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );
  }

  @Test
  void connects_4_Neighbors_to_Population_6() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);
    List<Bridge> bridges = hashiSolution.getBridges();

    assertEquals(6, bridges.size());
    assertThat(
      bridges,
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );
  }

  @Test
  void connects_4_Neighbors_to_Population_7() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);
    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);
    List<Bridge> bridges = hashiSolution.getBridges();

    assertEquals(7, bridges.size());
    assertThat(
      bridges,
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );
  }

  @Test
  void connects_4_Neighbors_to_Population_8() throws UnsolvableHashiMap {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);

    hashiSolution = new HashiSolution(hashiMap);

    CertaintyConnector.connect(hashiSolution);

    assertThat(
      hashiSolution.getBridges(),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );
  }
}