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
    return findSolution() != null;
  }

  private HashiSolution findSolution() {
    HashiSolution hashiSolution = null;
//    todo
    return hashiSolution;
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
