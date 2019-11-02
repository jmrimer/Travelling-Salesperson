package edu.louisville.traveler.hashi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NeighborChecker {
  public static Island seekXNeighbor(List<Island> sharedX, int y) {
    final int compareY = y;
    return sharedX.stream()
      .filter(i -> i.getCoordinates().getY() == compareY)
      .findAny()
      .orElse(null);
  }

  static Island seekYNeighbor(List<Island> sharedY, int x) {
    final int compareX = x;
    return sharedY.stream()
      .filter(i -> i.getCoordinates().getX() == compareX)
      .findAny()
      .orElse(null);
  }

  public static List<Island> findAllNeighbors(HashiMap hashiMap, Island island) {
    List<Island> neighbors = new ArrayList<>();
    List<Island> sharedY = findIslandOnSameRow(hashiMap, island);
    List<Island> sharedX = findIslandsOnSameColumn(hashiMap, island);

    neighbors.add(getLeftNeighbor(island, sharedY));
    neighbors.add(getRightNeighbor(hashiMap, island, sharedY));
    neighbors.add(getUpNeighbor(hashiMap, island, sharedX));
    neighbors.add(getDownNeighbor(island, sharedX));

    neighbors.removeIf(Objects::isNull);
    return neighbors;
  }

  public static int numberOfNeighbors(HashiMap hashiMap, Island island) {
    return findAllNeighbors(hashiMap, island).size();
  }

  @NotNull
  private static List<Island> findIslandsOnSameColumn(HashiMap hashiMap, Island island) {
    List<Island> sharedX = new ArrayList<>(hashiMap.getIslands());
    sharedX.removeIf(
      isle -> isle.getCoordinates().getX() != island.getCoordinates().getX() || isle.equals(island)
    );
    return sharedX;
  }

  @NotNull
  private static List<Island> findIslandOnSameRow(HashiMap hashiMap, Island island) {
    List<Island> sharedY = new ArrayList<>(hashiMap.getIslands());
    sharedY.removeIf(i -> i.getCoordinates().getY() != island.getCoordinates().getY() || i.equals(island));
    return sharedY;
  }

  private static Island getLeftNeighbor(Island island, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() - 1; x > -1; x--) {
      Island neighbor = seekYNeighbor(sharedY, x);
      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  private static Island getRightNeighbor(HashiMap hashiMap, Island island, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() + 1; x < hashiMap.getGridSize(); x++) {
      Island neighbor = seekYNeighbor(sharedY, x);
      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  public static Island getUpNeighbor(HashiMap hashiMap, Island island, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() + 1; y < hashiMap.getGridSize(); y++) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  static Island getDownNeighbor(Island island, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() - 1; y > -1; y--) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  public static Island onlyNeighborOf(HashiMap hashiMap, Island island) {
    return findAllNeighbors(hashiMap, island).get(0);
  }
}
