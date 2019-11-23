package edu.louisville.traveler.hashi;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NeighborFinder {
  public static Island seekVerticalNeighbor(List<Island> sharedX, int y) {
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

  public static Map<Direction, Island> findNeighbors(HashiMap hashiMap, Island island) {
    Map<Direction, Island> neighbors = new HashMap<>();
    List<Island> sharedY = findIslandsOnSameRow(hashiMap, island);
    List<Island> sharedX = findIslandsOnSameColumn(hashiMap, island);

    neighbors.put(Direction.NORTH, getNeighborNorth(hashiMap, island, sharedX));
    neighbors.put(Direction.EAST, getNeighborEast(hashiMap, island, sharedY));
    neighbors.put(Direction.SOUTH, getNeighborSouth(island, sharedX));
    neighbors.put(Direction.WEST, getNeighborWest(island, sharedY));

    neighbors.entrySet().removeIf(entry -> entry.getValue() == null);
    return neighbors;
  }

  public static int numberOfNeighbors(HashiMap hashiMap, Island island) {
    return findNeighbors(hashiMap, island).size();
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
  private static List<Island> findIslandsOnSameRow(HashiMap hashiMap, Island island) {
    List<Island> sharedY = new ArrayList<>(hashiMap.getIslands());
    sharedY.removeIf(i -> i.getCoordinates().getY() != island.getCoordinates().getY() || i.equals(island));
    return sharedY;
  }

  private static Island getNeighborWest(Island island, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() - 1; x > -1; x--) {
      Island neighbor = seekYNeighbor(sharedY, x);
      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  private static Island getNeighborEast(HashiMap hashiMap, Island island, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() + 1; x < hashiMap.getGridSize(); x++) {
      Island neighbor = seekYNeighbor(sharedY, x);
      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  public static Island getNeighborNorth(HashiMap hashiMap, Island island, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() + 1; y < hashiMap.getGridSize(); y++) {
      Island neighbor = seekVerticalNeighbor(sharedX, y);

      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  static Island getNeighborSouth(Island island, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() - 1; y > -1; y--) {
      Island neighbor = seekVerticalNeighbor(sharedX, y);

      if (neighbor != null) {
        return neighbor;
      }
    }
    return null;
  }

  public static void assignToIslands_AllAvailable(HashiSolution hashiSolution) {
    HashiMap hashiMap = hashiSolution.getHashiMap();
    List<Island> islands = hashiMap.getIslands();
    List<Bridge> bridges = hashiSolution.getBridges();

    for (Island island : islands) {
      Map<Direction, Island> neighbors = island.getNeighbors();
      List<Island> sharedY = findIslandsOnSameRow(hashiMap, island);
      List<Island> sharedX = findIslandsOnSameColumn(hashiMap, island);

      neighbors.put(Direction.NORTH, getAvailableNeighborNorth(hashiMap, island, sharedX, bridges));
      neighbors.put(Direction.EAST, getAvailableNeighborEast(hashiMap, island, sharedY, bridges));
      neighbors.put(Direction.SOUTH, getAvailableNeighborSouth(island, sharedX, bridges));
      neighbors.put(Direction.WEST, getAvailableNeighborWest(island, sharedY, bridges));

      neighbors.entrySet().removeIf(entry -> entry.getValue() == null);
    }
  }

  private static Island getAvailableNeighborNorth(
    HashiMap hashiMap,
    Island island,
    List<Island> sharedX,
    List<Bridge> bridges
  ) {
    Island neighbor = getNeighborNorth(hashiMap, island, sharedX);
    return verifyAvailability(island, bridges, neighbor);
  }

  private static Island getAvailableNeighborEast(HashiMap hashiMap, Island island, List<Island> sharedY, List<Bridge> bridges) {
    Island neighbor = getNeighborEast(hashiMap, island, sharedY);
    return verifyAvailability(island, bridges, neighbor);
  }

  private static Island getAvailableNeighborSouth(Island island, List<Island> sharedX, List<Bridge> bridges) {
    Island neighbor = getNeighborSouth(island, sharedX);
    return verifyAvailability(island, bridges, neighbor);
  }

  private static Island getAvailableNeighborWest(Island island, List<Island> sharedY, List<Bridge> bridges) {
    Island neighbor = getNeighborWest(island, sharedY);
    return verifyAvailability(island, bridges, neighbor);
  }

  private static boolean noIntersection(List<Bridge> bridges, Island island, Island neighbor) {
    for (Bridge bridge : bridges) {
      Bridge trialBridge = new Bridge(island, neighbor);
      if (trialBridge.intersects(bridge)) {
        return false;
      }
    }
    return true;
  }

  private static Island verifyAvailability(Island island, List<Bridge> bridges, Island neighbor) {
    if (neighbor != null && neighbor.getAdjustedPopulation() > 0 && noIntersection(bridges, island, neighbor)) {
      return neighbor;
    }
    return null;
  }
}
