package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashiSolver {
  private HashiMap hashiMap;

  public boolean isSolvable() {
    return hasEnoughIslands()
      && allIslandsHaveNeighbors()
      && allIslandsHaveEnoughNeighborsToSupportPopulation()
      && hasSolution();
  }

  private boolean hasSolution() {
    return findSolution().isComplete();
  }

  public HashiSolution findSolution() {
    HashiSolution hashiSolution = new HashiSolution(hashiMap);
    try {
      justEnoughNeighbors(hashiSolution);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      unsolvableHashiMap.printStackTrace();
    }
    hashiSolution.setComplete(checkCompleteness(hashiSolution));
    return hashiSolution;
  }

  private boolean checkCompleteness(HashiSolution hashiSolution) {
    for (Island island : hashiSolution.getHashiMap().getIslands()) {
      int bridgeCount = 0;
      for (Bridge bridge : hashiSolution.getBridges()) {
        if (bridge.contains(island)) {
          bridgeCount++;
        }
      }
      if (bridgeCount != island.getPopulation()) {
        return false;
      }
    }
    return true;
  }

  private void justEnoughNeighbors(HashiSolution hashiSolution) throws UnsolvableHashiMap {
    HashiMap hashiMap = hashiSolution.getHashiMap();
    for (Island island : hashiMap.getIslands()) {
      switch (island.neighborCount()) {
        case 1:
          connectAllBridges(hashiSolution, island, island.onlyNeighbor());
        case 2:
          if (island.getPopulation() == 4) {
            connectAllBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() == 3) {
            connectSingleBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() > 4) {
            throw new UnsolvableHashiMap();
          }
        case 3:
          if (island.getPopulation() == 6) {
            connectAllBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() == 5) {
            connectSingleBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() > 6) {
            throw new UnsolvableHashiMap();
          }
        case 4:
          if (island.getPopulation() == 8) {
            connectAllBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() == 7) {
            connectSingleBridgesToAllNeighbors(hashiSolution, island);
          } else if (island.getPopulation() > 8) {
            throw new UnsolvableHashiMap();
          }
      }
    }
  }

  private void connectSingleBridgesToAllNeighbors(HashiSolution hashiSolution, Island island) {
    for (Island neighbor : island.getNeighbors().values()) {
      hashiSolution.addBridge(island, neighbor);
    }
  }

  private void connectAllBridgesToAllNeighbors(HashiSolution hashiSolution, Island island) {
    for (Island neighbor : island.getNeighbors().values()) {
      for (int i = 0; i < 2; i++) {
        hashiSolution.addBridge(island, neighbor);
      }
    }
  }

  private void connectAllBridges(HashiSolution hashiSolution, Island island, Island neighbor) {
    for (int i = 0; i < island.getPopulation(); i++) {
      hashiSolution.addBridge(island, neighbor);
    }
  }

  private boolean allIslandsHaveEnoughNeighborsToSupportPopulation() {
    return hashiMap.getIslands()
      .stream()
      .noneMatch(this::hasTooFewNeighbors);
  }

  private boolean hasTooFewNeighbors(Island island) {
    return NeighborChecker.numberOfNeighbors(hashiMap, island) < (island.getPopulation() + 1) / 2;
  }

  private boolean allIslandsHaveNeighbors() {
    for (Island island : hashiMap.getIslands()) {
      if (NeighborChecker.numberOfNeighbors(hashiMap, island) == 0) {
        return false;
      }
    }
    return true;
  }

  private boolean hasEnoughIslands() {
    return hashiMap.getIslands().size() > 1;
  }

  public List<Bridge> connectSingleConstraints(Island island) {
    List<Bridge> bridges = new ArrayList<>();
    System.out.println(island.getConstraints());
    for (Map.Entry<Direction, List<Integer>> constraint : island.getConstraints().entrySet()) {
      if (constraint.getValue().size() == 1) {
        bridges.add(new Bridge(island, island.getNeighbors().get(constraint.getKey())));
      }
    }
    island.getConstraints().entrySet().removeIf(entry -> entry.getValue().size() == 1);
    return bridges;
  }
}
