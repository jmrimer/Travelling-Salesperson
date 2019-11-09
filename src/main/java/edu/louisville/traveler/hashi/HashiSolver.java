package edu.louisville.traveler.hashi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashiSolver {
  private HashiMap hashiMap;
  private List<Integer> constraint_0 = List.of(0);
  private List<Integer> constraint_1 = List.of(1);
  private List<Integer> constraint_2 = List.of(2);
  private List<Integer> constraint_0_1 = List.of(0, 1);
  private List<Integer> constraint_1_2 = List.of(1, 2);
  private List<Integer> constraint_0_1_2 = List.of(0, 1, 2);

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
          case 3:
            triplePopulationIsland(island, neighborEntry);
            break;

        }
      }
    }
  }

  private void triplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (island.getNeighbors().size()) {
      case 1:
        throw new UnsolvableHashiMap();
      case 2:
        if (allNeighborsAreSinglePopulation(island)) {
          throw new UnsolvableHashiMap();
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_1);
          } else {
            island.setConstraint(direction, constraint_2);
          }
        } else {
          island.setConstraint(direction, constraint_1_2);
        }
        break;
      case 3:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_1);
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        }
        break;
      case 4:
        if (neighbor.getPopulation() == 1) {
          island.setConstraint(direction, constraint_0_1);
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
    }

  }

  private void doublePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws
    UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (island.getNeighbors().size()) {
      case 1:
        if (neighbor.getPopulation() >= 2) {
          island.setConstraint(direction, constraint_2);
        } else {
          throw new UnsolvableHashiMap();
        }
        break;
      case 2:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_1);
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
        break;
      case 3:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_0_1);
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
      case 4:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_0_1);
        } else if (threeNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (onlyOneNeighborIsSinglePopulation(island)) {
          if (neighbor.getPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
    }
  }

  private int getSinglePopulationNeighborCount(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getPopulation() > 1);
    return neighbors.size();
  }

  private boolean threeNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 3;
  }

  private boolean twoNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 2;
  }

  private boolean onlyOneNeighborIsSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 1;
  }

  private boolean allNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == island.getNeighbors().size();
  }

  private boolean singleNeighbor(Island island) {
    return island.getNeighbors().size() == 1;
  }

  private void singlePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    if (singleNeighbor(island)) {
      island.setConstraint(neighborEntry.getKey(), constraint_1);
    } else if (island.getNeighbors().size() > 1) {
      island.setConstraint(neighborEntry.getKey(), constraint_0_1);
    }
  }
}
