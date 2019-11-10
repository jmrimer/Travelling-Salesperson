package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static edu.louisville.traveler.hashi.HashiSolutionChecker.allBridgesBuilt;
import static edu.louisville.traveler.hashi.HashiSolutionChecker.allIslandsConnect;

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
        verifyIslandsHaveNeighbors();
        verifyNeighborsMeetPopulationCapacity();

        buildBridgesFor(island);
        if (allBridgesBuilt(hashiMap, bridges)) {
          this.isSolvable = true;
          return;
        }
      }
    } while (constraintsContinueChanging(startingBridges));
//    todo start DFS
    List<Island> remainingIslands = new ArrayList<>(hashiMap.getIslands());
    remainingIslands.removeIf(island -> island.getAdjustedPopulation() > 0);
    for (Island island : remainingIslands) {
      HashiMap resetMap = new HashiMap(hashiMap.getGridSize(), hashiMap.getIslands());

      if (allBridgesBuilt(hashiMap, bridges) && allIslandsConnect(hashiMap, bridges)) {
        this.isSolvable = true;
        return;
      }
    }
//    for each island
//    #1 choose one bridge to build
//    run [easy] constraints check [as previously done] until no change
//    if no error, run #1: choose bridge and build
//    if error, reset map to previous state [#backtrack]
//    choose different bridge
//    do until out of options for "highest level" island (i.e., tried every possible combination of every bridge option for island)
//    if puzzle complete, break loop
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

  private void buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      bridges.add(new Bridge(island, neighbor));
      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();

      adjustNeighbors();
      ConstraintAssigner.assignConstraints(hashiMap);
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
