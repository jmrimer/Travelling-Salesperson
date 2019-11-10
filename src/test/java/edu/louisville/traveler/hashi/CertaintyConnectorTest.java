package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.louisville.traveler.hashi.CertaintyConnector.connect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertaintyConnectorTest extends BaseHashiTest {
  @Test
  void returnsEmpty_No_CertainNeighbors() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(0);
    islandEast.setPopulation(1);

    assertTrue(connect(hashiMap).isEmpty());

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
    assertTrue(connect(hashiMap).isEmpty());
  }

  @Test
  void connects_1_CertainNeighbor() {
    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(1);
    islandEast.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast)
      ).toArray())
    );

    hashiMap = singleNeighborEastMap();
    islandCenter.setPopulation(2);
    islandEast.setPopulation(2);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast)
      ).toArray())
    );
  }

  @Test
  void connects_2_CertainNeighbors() {
    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );

    hashiMap = doubleNeighborEastNorthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth)
      ).toArray())
    );
  }

  @Test
  void connects_3_CertainNeighbors() {
    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(3);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest)
      ).toArray())
    );

    hashiMap = tripleNeighborEastNorthWestMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest)
      ).toArray())
    );
  }

  @Test
  void connects_4_CertainNeighbors() {
    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(4);
    islandEast.setPopulation(1);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(5);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(1);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(6);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(1);
    islandSouth.setPopulation(1);

    assertThat(
      connect(hashiMap),
      containsInAnyOrder(List.of(
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandEast),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandNorth),
        new Bridge(islandCenter, islandWest),
        new Bridge(islandCenter, islandSouth)
      ).toArray())
    );

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(7);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(1);

    assertThat(
      connect(hashiMap),
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

    hashiMap = quadrupleNeighborEastNorthWestSouthMap();
    islandCenter.setPopulation(8);
    islandEast.setPopulation(2);
    islandNorth.setPopulation(2);
    islandWest.setPopulation(2);
    islandSouth.setPopulation(2);

    assertThat(
      connect(hashiMap),
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

  @Test
  void connects_5_CertainNeighbors_ComplexMap() {
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
      connect(hashiMap),
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