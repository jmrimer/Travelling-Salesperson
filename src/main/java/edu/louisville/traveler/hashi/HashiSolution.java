package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HashiSolution {
  private HashiMap hashiMap;
  private List<Bridge> bridges;

  public HashiSolution(HashiMap hashiMap) {
    this.hashiMap = hashiMap;
    this.bridges = new ArrayList<>();
  }


  public void updateMap() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      island.setAdjustedPopulation(island.getPopulation());
    }

    for (Bridge bridge : bridges) {
      bridge.getIsland1().decreaseAdjustedPopulation();
      bridge.getIsland2().decreaseAdjustedPopulation();
    }

    ConstraintAssigner.assignConstraints(hashiMap);
  }

  public void addBridge(Bridge bridge) throws UnsolvableHashiMap {
    this.bridges.add(bridge);
    this.updateMap();
  }

  public boolean isSolvedByAddingBridges(List<Bridge> bridges) throws UnsolvableHashiMap {
    List<Bridge> checkBridges = new ArrayList<>(this.bridges);
    checkBridges.addAll(bridges);
    this.updateMap();
    return HashiSolutionChecker.puzzleSolved(this.hashiMap, checkBridges);
  }

  public void removeBridge(Bridge bridge) {
    this.bridges.remove(bridge);
  }
}
