package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.List;

public class NeighborChecker {
  static int checkDown(Island island, int numberOfNeighbors, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() - 1; y > -1; y--) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  public static Island seekXNeighbor(List<Island> sharedX, int y) {
    final int compareY = y;
    return sharedX.stream()
      .filter(i -> i.getCoordinates().getY() == compareY)
      .findAny()
      .orElse(null);
  }

  static int checkLeft(Island island, int numberOfNeighbors, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() - 1; x > -1; x--) {
      Island neighbor = seekYNeighbor(sharedY, x);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  static Island seekYNeighbor(List<Island> sharedY, int x) {
    final int compareX = x;
    return sharedY.stream()
      .filter(i -> i.getCoordinates().getX() == compareX)
      .findAny()
      .orElse(null);
  }

  public static int numberOfNeighbors(HashiMap hashiMap, Island island) {
    int numberOfNeighbors = 0;
    List<Island> sharedY = new ArrayList<>(hashiMap.getIslands());
    sharedY.removeIf(i -> i.getCoordinates().getY() != island.getCoordinates().getY() || i.equals(island));
    List<Island> sharedX = new ArrayList<>(hashiMap.getIslands());
    sharedX.removeIf(i -> i.getCoordinates().getX() != island.getCoordinates().getX() || i.equals(island));

    numberOfNeighbors = checkLeft(island, numberOfNeighbors, sharedY);
    numberOfNeighbors = checkRight(hashiMap, island, numberOfNeighbors, sharedY);
    numberOfNeighbors = checkUp(hashiMap, island, numberOfNeighbors, sharedX);
    numberOfNeighbors = checkDown(island, numberOfNeighbors, sharedX);

    return numberOfNeighbors;
  }

  public static int checkRight(HashiMap hashiMap, Island island, int numberOfNeighbors, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() + 1; x < hashiMap.getGridSize(); x++) {
      Island neighbor = seekYNeighbor(sharedY, x);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  public static int checkUp(HashiMap hashiMap, Island island, int numberOfNeighbors, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() + 1; y < hashiMap.getGridSize(); y++) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }
}
