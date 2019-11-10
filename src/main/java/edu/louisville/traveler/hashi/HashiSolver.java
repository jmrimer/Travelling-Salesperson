package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static edu.louisville.traveler.hashi.HashiSolutionChecker.*;

@Data
public class HashiSolver {
  private HashiMap hashiMap;
  private List<Bridge> bridges;
  private boolean isSolvable = false;


  public HashiSolver(HashiMap hashiMap) throws UnsolvableHashiMap {
    this.hashiMap = hashiMap;
    this.bridges = new ArrayList<>();
    checkFailuresAndUpdateMap();
  }

  private void checkFailuresAndUpdateMap() throws UnsolvableHashiMap {
    checkForInstantFailure();
    adjustMap();
  }

  private void adjustMap() throws UnsolvableHashiMap {
    adjustNeighbors();
    ConstraintAssigner.assignConstraints(hashiMap);
  }

  private void checkForInstantFailure() throws UnsolvableHashiMap {
    verifyIslandsHaveNeighbors();
    verifyNeighborsMeetPopulationCapacity();
  }

  private void verifyNeighborsMeetPopulationCapacity() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      int totalNeighboringCapacity = 0;

      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        totalNeighboringCapacity += Math.min(neighborEntry.getValue().getAdjustedPopulation(), 2);
      }

      if (totalNeighboringCapacity < island.getAdjustedPopulation()) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  private void verifyIslandsHaveNeighbors() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      if (island.getNeighbors().isEmpty() && island.getAdjustedPopulation() > 0) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  public void solve() throws UnsolvableHashiMap {
    List<Bridge> startingBridges;
    do {
      startingBridges = new ArrayList<>(bridges);

      for (Island island : this.hashiMap.getIslands()) {
        checkFailuresAndUpdateMap();
        buildBridgesFor(island);
        if (checkIfComplete()) return;
      }
    } while (constraintsContinueChanging(startingBridges));

//    todo start DFS
    for (Island island : hashiMap.getIslands()) {
      List<Bridge> bridgesBeforeChanges = new ArrayList<>(bridges);
      Island islandBeforeChanges = island.clone();

      for (Map.Entry<Direction, List<Integer>> constraint : island.getConstraints().entrySet()) {
        Island neighbor = island.getNeighbors().get(constraint.getKey());
        if (neighbor == null) {
          continue;
        }

        try {
          buildBridge(island, neighbor);

          checkFailuresAndUpdateMap();

          connect(neighbor);
        } catch (UnsolvableHashiMap unsolvableHashiMap) {
          bridges = new ArrayList<>(bridgesBeforeChanges);
          island = islandBeforeChanges;
        }
      }
    }

    if (puzzleSolved(hashiMap, bridges)) {
      isSolvable = true;
    }
  }

  private void connect(Island island) {
    List<Bridge> bridgesBeforeChanges = new ArrayList<>(bridges);
    Island islandBeforeChanges = island.clone();
//    if (island.getAdjustedPopulation() == 0 || island.getConstraints().size() == 0) {
//      return;
//    }

    try {
      for (Map.Entry<Direction, List<Integer>> constraint : island.getConstraints().entrySet()) {
        Island neighbor = island.getNeighbors().get(constraint.getKey());

        buildBridge(island, neighbor);

        checkFailuresAndUpdateMap();

        checkForInstantFailure();
        adjustMap();

        connect(neighbor);
//        connect constraint
//        if puzzle not broken
//          grab neighbor
//          attempt same

      }
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
//        backtrack
      bridges = new ArrayList<>(bridgesBeforeChanges);
      island = islandBeforeChanges;
    }
  }

  private boolean checkIfComplete() {
    if (puzzleSolved(hashiMap, bridges)) {
      this.isSolvable = true;
      return true;
    }
    return false;
  }

  private boolean constraintsContinueChanging(List<Bridge> startingBridges) {
    return !startingBridges.equals(bridges);
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

  private Bridge buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      Bridge bridge = new Bridge(island, neighbor);
      bridges.add(bridge);

      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();

      checkFailuresAndUpdateMap();
      return bridge;
    } else {
      throw new UnsolvableHashiMap();
    }
  }

  private void adjustNeighbors() {
    for (Island island : hashiMap.getIslands()) {
      island.getNeighbors().values().removeIf(neighbor -> neighbor.getAdjustedPopulation() == 0);
    }
  }
}
