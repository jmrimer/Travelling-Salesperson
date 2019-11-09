package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashiSolverTest extends BaseHashiTest{
  Island islandCenter;
  Island islandNorth;
  Island islandEast;
  Island islandSouth;
  Island islandWest;
  HashiMap hashiMap;
  HashiSolver hashiSolver;

  @BeforeEach
  public void setup() {
    islandCenter = new Island(new Coordinates(4, 4), 0);
    islandNorth = new Island(new Coordinates(4, 6), 0);
    islandEast = new Island(new Coordinates(6, 4), 0);
    islandSouth = new Island(new Coordinates(4, 2), 0);
    islandWest = new Island(new Coordinates(2, 4), 0);
  }

  @Test
  void constructionErrsWhenAnyIslandWithoutNeighbors() {
    hashiMap = new HashiMap(7, List.of(islandCenter));
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionErrsWhenNeighborCapacityNotEnough() {
    hashiMap = singleNeighborEastMap();

    islandCenter.setPopulation(2);
    islandEast.setPopulation(1);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));

    islandCenter.setPopulation(3);
    islandEast.setPopulation(8);
    assertThrows(UnsolvableHashiMap.class, () -> new HashiSolver(hashiMap));
  }

  @Test
  void constructionErrsOnMapsWithFailedConstraints() {

  }

  private HashiMap singleNeighborEastMap() {
    return new HashiMap(
      7,
      List.of(
        islandCenter,
        islandEast
      )
    );
  }
}