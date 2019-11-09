package edu.louisville.traveler.hashi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashiSolver {
  private HashiMap hashiMap;


  public HashiSolver(HashiMap hashiMap) throws UnsolvableHashiMap {
    this.hashiMap = hashiMap;
    verifyIslandsHaveNeighbors();
    verifyNeighborsMeetPopulationCapacity();
    assignConstraints();
  }

  private void verifyNeighborsMeetPopulationCapacity() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      int totalNeighboringCapacity = 0;

      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        totalNeighboringCapacity += Math.min(neighborEntry.getValue().getPopulation(), 2);
      }
      if (totalNeighboringCapacity < island.getPopulation()) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  private void verifyIslandsHaveNeighbors() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      if (island.getNeighbors().size() == 0) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  private void assignConstraints() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        switch (island.getPopulation()) {
          case 1:
            singlePopulationIsland(island, neighborEntry);
            break;
          case 2:
            doublePopulationIsland(island, neighborEntry);
            break;
        }
      }
    }
  }

  private void doublePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (island.getNeighbors().size()) {
      case 1:
        if (neighbor.getPopulation() >= 2) {
          island.setConstraint(direction, List.of(2));
        } else {
          throw new UnsolvableHashiMap();
        }
        break;
      case 2:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, List.of(1));
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(1, 2));
          }
        } else {
          island.setConstraint(direction, List.of(0, 1, 2));
        }
        break;
      case 3:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, List.of(0, 1));
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(0, 1, 2));
          }
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(0, 1, 2));
          }
        } else {
          island.setConstraint(direction, List.of(0, 1, 2));
        }
      case 4:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, List.of(0, 1));
        } else if (threeNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(0, 1, 2));
          }
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(0, 1, 2));
          }
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, List.of(0, 1));
          } else {
            island.setConstraint(direction, List.of(0, 1, 2));
          }
        } else {
          island.setConstraint(direction, List.of(0, 1, 2));
        }
    }
  }

  private boolean threeNeighborsAreSinglePopulation(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getPopulation() > 1);
    return neighbors.size() == 3;
  }

  private boolean onlyOneNeighborIsSinglePopulation(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getPopulation() > 1);
    return neighbors.size() == 1;
  }

  private boolean twoNeighborsAreSinglePopulation(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getPopulation() > 1);
    return neighbors.size() == 2;
  }

  private boolean allNeighborsAreSinglePopulation(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getPopulation() == 1);
    return neighbors.isEmpty();
  }

  private boolean singleNeighbor(Island island) {
    return island.getNeighbors().size() == 1;
  }

  private void singlePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    if (singleNeighbor(island)) {
      island.setConstraint(neighborEntry.getKey(), List.of(1));
    } else if (island.getNeighbors().size() > 1) {
      island.setConstraint(neighborEntry.getKey(), List.of(0, 1));
    }
  }
}
