package edu.louisville.traveler.hashi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstraintAssigner {
  private static List<Integer> constraint_1 = List.of(1);
  private static List<Integer> constraint_2 = List.of(2);
  private static List<Integer> constraint_0_1 = List.of(0, 1);
  private static List<Integer> constraint_1_2 = List.of(1, 2);
  private static List<Integer> constraint_0_1_2 = List.of(0, 1, 2);

  public static void assignConstraints(HashiSolution hashiSolution) throws UnsolvableHashiMap {
    assignConstraints(hashiSolution.getHashiMap());
  }

  public static void assignConstraints(HashiMap hashiMap) throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      if (clearAllConstraintsIfCompleted(island)) continue;

      if (neighborsCannotSupportPopulation(island)) {
        throw new UnsolvableHashiMap();
      }

      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        if (clearDirectionConstraintsIfNeighborCompleted(island, neighborEntry)) continue;

        switch (island.getAdjustedPopulation()) {
          case 1:
            singlePopulationIsland(island, neighborEntry);
            break;
          case 2:
            doublePopulationIsland(island, neighborEntry);
            break;
          case 3:
            triplePopulationIsland(island, neighborEntry);
            break;
          case 4:
            quadruplePopulationIsland(island, neighborEntry);
            break;
          case 5:
            quintuplePopulationIsland(island, neighborEntry);
            break;
          case 6:
            sextuplePopulationIsland(island, neighborEntry);
            break;
          case 7:
            septuplePopulationIsland(island, neighborEntry);
            break;
          case 8:
            octuplePopulationIsland(island, neighborEntry);
        }
      }
    }
  }

  private static boolean neighborsCannotSupportPopulation(Island island) {
    int availableBridgeCapacity = 0;
    for (Island neighbor : island.getNeighbors().values()) {
      availableBridgeCapacity += neighbor.getAdjustedPopulation();
    }
    return availableBridgeCapacity < island.getAdjustedPopulation();
  }

  private static boolean clearDirectionConstraintsIfNeighborCompleted(Island island, Map.Entry<Direction, Island> neighborEntry) {
    if (neighborEntry.getValue().getAdjustedPopulation() == 0) {
      island.getConstraints().remove(neighborEntry.getKey());
      return true;
    }
    return false;
  }

  private static boolean clearAllConstraintsIfCompleted(Island island) {
    if (island.getAdjustedPopulation() == 0) {
      island.getConstraints().clear();
      return true;
    }
    return false;
  }

  private static void doublePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws
    UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (availableNeighborCount(island)) {
      case 1:
        if (neighbor.getAdjustedPopulation() >= 2) {
          island.setConstraint(direction, constraint_2);
        } else {
          throw new UnsolvableHashiMap();
        }
        break;
      case 2:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_1);
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
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
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
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
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
    }
  }

  private static void triplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (availableNeighborCount(island)) {
      case 2:
        if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
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
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        }
        break;
      case 4:
        if (neighbor.getAdjustedPopulation() == 1) {
          island.setConstraint(direction, constraint_0_1);
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
    }

  }

  private static void quadruplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (availableNeighborCount(island)) {
      case 2:
        if (oneNeighborIsSinglePopulation(island)) {
          throw new UnsolvableHashiMap();
        } else {
          island.setConstraint(direction, constraint_2);
        }
        break;
      case 3:
        if (allNeighborsAreSinglePopulation(island)) {
          throw new UnsolvableHashiMap();
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_1);
          } else {
            island.setConstraint(direction, constraint_2);
          }
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
        break;
      case 4:
        if (allNeighborsAreSinglePopulation(island)) {
          island.setConstraint(direction, constraint_1);
        } else if (threeNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        }
        break;
    }

  }

  private static void quintuplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) throws UnsolvableHashiMap {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (availableNeighborCount(island)) {
      case 3:
        if (twoNeighborsAreSinglePopulation(island)) {
          throw new UnsolvableHashiMap();
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_1);
          } else {
            island.setConstraint(direction, constraint_2);
          }
        } else {
          island.setConstraint(direction, constraint_1_2);
        }
        break;
      case 4:
        if (threeNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_1);
          } else {
            island.setConstraint(direction, constraint_2);
          }
        } else if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_0_1_2);
          }
        }
        break;
    }
  }

  private static void sextuplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    switch (availableNeighborCount(island)) {
      case 3:
        island.setConstraint(direction, constraint_2);
        break;
      case 4:
        if (twoNeighborsAreSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_1);
          } else {
            island.setConstraint(direction, constraint_2);
          }
        } else if (oneNeighborIsSinglePopulation(island)) {
          if (neighbor.getAdjustedPopulation() == 1) {
            island.setConstraint(direction, constraint_0_1);
          } else {
            island.setConstraint(direction, constraint_1_2);
          }
        } else {
          island.setConstraint(direction, constraint_0_1_2);
        }
    }
  }

  private static void septuplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    Island neighbor = neighborEntry.getValue();
    Direction direction = neighborEntry.getKey();

    if (oneNeighborIsSinglePopulation(island)) {
      if (neighbor.getAdjustedPopulation() == 1) {
        island.setConstraint(direction, constraint_1);
      } else {
        island.setConstraint(direction, constraint_2);
      }
    } else {
      island.setConstraint(direction, constraint_1_2);
    }
  }

  private static void octuplePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    Direction direction = neighborEntry.getKey();
    island.setConstraint(direction, constraint_2);
  }

  private static int getSinglePopulationNeighborCount(Island island) {
    Map<Direction, Island> neighbors = new HashMap<>(island.getNeighbors());
    neighbors.values().removeIf(neighbor -> neighbor.getAdjustedPopulation() > 1);
    return neighbors.size();
  }

  private static boolean threeNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 3;
  }

  private static boolean twoNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 2;
  }

  private static boolean oneNeighborIsSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == 1;
  }

  private static boolean allNeighborsAreSinglePopulation(Island island) {
    return getSinglePopulationNeighborCount(island) == availableNeighborCount(island);
  }

  private static void singlePopulationIsland(Island island, Map.Entry<Direction, Island> neighborEntry) {
    if (availableNeighborCount(island) == 1) {
      island.setConstraint(neighborEntry.getKey(), constraint_1);
    } else if (availableNeighborCount(island) > 1) {
      island.setConstraint(neighborEntry.getKey(), constraint_0_1);
    }
  }

//  private static void setAllConstraints(Island island, List<Integer> constraint) {
//    HashMap<Direction, Island> availableNeighbors = new HashMap<>();
//    for (Map.Entry<Direction, Island> neighbor : island.getNeighbors().entrySet()) {
//      if (neighbor.getValue().getAdjustedPopulation() > 0 && noBridgeCrosses(island, neighbor.getValue(), hashiMap)) {
//        availableNeighbors.put(neighbor.getKey(), neighbor.getValue());
//      }
//    }
//
//    island.getConstraints().clear();
//    for (Map.Entry<Direction, Island> availableNeighbor : availableNeighbors.entrySet()) {
//      island.getConstraints().put(availableNeighbor.getKey(), constraint);
//    }
//  }

  private static int availableNeighborCount(Island island) {
    return (int) island.getNeighbors().values()
      .stream()
      .filter(neighbor -> neighbor.getAdjustedPopulation() > 0)
      .count();
  }
}
