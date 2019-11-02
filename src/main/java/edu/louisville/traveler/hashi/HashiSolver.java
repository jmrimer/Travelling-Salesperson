package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    justEnoughNeighbors(hashiSolution);
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

  private void justEnoughNeighbors(HashiSolution hashiSolution) {
    HashiMap hashiMap = hashiSolution.getHashiMap();
    for (Island island : hashiMap.getIslands()) {
      if (NeighborChecker.numberOfNeighbors(hashiMap, island) == 1) {
        for (int i = 0; i < island.getPopulation(); i++) {
          hashiSolution.addBridge(island, NeighborChecker.onlyNeighborOf(hashiMap, island));
        }
      }
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
}
