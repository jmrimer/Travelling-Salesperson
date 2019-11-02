package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashiSolver {
  private HashiMap map;

  public boolean isSolvable() {
    return hasEnoughIslands()
      && allIslandsHaveNeighbors();
  }

  private boolean allIslandsHaveNeighbors() {
    for (Island island : map.getIslands()) {
      if (numberOfNeighbors(island) == 0) {
        return false;
      }
    }
    return true;
  }

  private boolean hasEnoughIslands() {
    return map.getIslands().size() > 1;
  }

  public boolean isCorner(Island island) {
    return numberOfNeighbors(island) == 2;
  }

  public int numberOfNeighbors(Island island) {
    int numberOfNeighbors = 0;
    List<Island> sharedY = new ArrayList<>(map.getIslands());
    sharedY.removeIf(i -> i.getCoordinates().getY() != island.getCoordinates().getY() || i.equals(island));
    List<Island> sharedX = new ArrayList<>(map.getIslands());
    sharedX.removeIf(i -> i.getCoordinates().getX() != island.getCoordinates().getX() || i.equals(island));

    numberOfNeighbors = checkLeft(island, numberOfNeighbors, sharedY);
    numberOfNeighbors = checkRight(island, numberOfNeighbors, sharedY);
    numberOfNeighbors = checkUp(island, numberOfNeighbors, sharedX);
    numberOfNeighbors = checkDown(island, numberOfNeighbors, sharedX);

    return numberOfNeighbors;
  }

  private int checkRight(Island island, int numberOfNeighbors, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() + 1; x < map.getGridSize(); x++) {
      Island neighbor = seekYNeighbor(sharedY, x);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  private int checkLeft(Island island, int numberOfNeighbors, List<Island> sharedY) {
    for (int x = island.getCoordinates().getX() - 1; x > -1; x--) {
      Island neighbor = seekYNeighbor(sharedY, x);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  private int checkUp(Island island, int numberOfNeighbors, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() + 1; y < map.getGridSize(); y++) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  private int checkDown(Island island, int numberOfNeighbors, List<Island> sharedX) {
    for (int y = island.getCoordinates().getY() - 1; y > -1; y--) {
      Island neighbor = seekXNeighbor(sharedX, y);

      if (neighbor != null) {
        numberOfNeighbors++;
        break;
      }
    }
    return numberOfNeighbors;
  }

  private Island seekYNeighbor(List<Island> sharedY, int x) {
    final int compareX = x;
    return sharedY.stream()
      .filter(i -> i.getCoordinates().getX() == compareX)
      .findAny()
      .orElse(null);
  }

  private Island seekXNeighbor(List<Island> sharedX, int y) {
    final int compareY = y;
    return sharedX.stream()
      .filter(i -> i.getCoordinates().getY() == compareY)
      .findAny()
      .orElse(null);
  }
}
