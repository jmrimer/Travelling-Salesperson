package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
public class HashiSolver {
  private HashiMap hashiMap;
  private List<Bridge> bridges;
  private boolean isSolvable = false;


  public HashiSolver(HashiMap hashiMap) throws UnsolvableHashiMap {
    this.hashiMap = hashiMap;
    this.bridges = new ArrayList<>();
    verifyIslandsHaveNeighbors();
    verifyNeighborsMeetPopulationCapacity();
    ConstraintAssigner.assignConstraints(hashiMap);
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

  public void solve() throws UnsolvableHashiMap {
    for (Island island : this.hashiMap.getIslands()) {
      buildBridgesFor(island);
      if (puzzleComplete()) {
        this.isSolvable = true;
        return;
      }
    }
  }

  private boolean puzzleComplete() {
    for (Island island : this.hashiMap.getIslands()) {
      if (island.getAdjustedPopulation() > 0) {
        return false;
      }
    }
    return bridges.size() == hashiMap.getBridgesRequired();
  }

  public void buildBridgesFor(Island island) throws UnsolvableHashiMap {
    Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator = island.getConstraints().entrySet().iterator();

    while (constraintIterator.hasNext()) {
      Map.Entry<Direction, List<Integer>> constraintEntry = constraintIterator.next();

      List<Integer> constraints = constraintEntry.getValue();
      Direction direction = constraintEntry.getKey();
      Island neighbor = island.getNeighbors().get(direction);

      if (constraints.size() == 1) {
        buildBridgesForSingleConstraints(island, constraintEntry, constraintIterator);
        continue;
      }

      if (constraints.contains(1) && !constraints.contains(0)) {
        buildBridge(island, neighbor);
      }
    }
  }

  private void buildBridgesForSingleConstraints(Island island, Map.Entry<Direction, List<Integer>> constraintEntry, Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator) throws UnsolvableHashiMap {
    List<Integer> constraint = constraintEntry.getValue();
    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());

    for (int i = 0; i < constraint.get(0); i++) {
      buildBridge(island, neighbor);
    }
  }

  private void buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      bridges.add(new Bridge(island, neighbor));
      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();
      ConstraintAssigner.assignConstraints(hashiMap);
    } else {
      throw new UnsolvableHashiMap();
    }
  }
}
