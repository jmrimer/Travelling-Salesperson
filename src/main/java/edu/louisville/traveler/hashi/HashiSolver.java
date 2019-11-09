package edu.louisville.traveler.hashi;

import java.util.List;
import java.util.Map;

public class HashiSolver {
  private HashiMap hashiMap;


  public HashiSolver(HashiMap hashiMap) throws UnsolvableHashiMap {
    this.hashiMap = hashiMap;
    verifyIslandsHaveNeighbors();
    assignConstraints();
  }

  private void verifyIslandsHaveNeighbors() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      if (island.getNeighbors().size() == 0) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  private void assignConstraints() {
    for (Island island : hashiMap.getIslands()) {
      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        if (island.getPopulation() == 1 && island.getNeighbors().size() == 1) {
          island.setConstraint(neighborEntry.getKey(), List.of(1));
        } else if (island.getPopulation() == 1 && island.getNeighbors().size() > 1) {
          island.setConstraint(neighborEntry.getKey(), List.of(0, 1));
        }
      }
    }
  }
}
