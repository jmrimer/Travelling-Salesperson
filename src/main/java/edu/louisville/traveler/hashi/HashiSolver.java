package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public void solve() throws UnsolvableHashiMap {
    connectAllCertainBridges();

    if (puzzleSolved(hashiMap, bridges)) {
      isSolvable = true;
      return;
    }

    connectViaDepthFirstSearch();

    isSolvable = puzzleSolved(hashiMap, bridges);
  }

  private void checkFailuresAndUpdateMap() throws UnsolvableHashiMap {
    checkForInstantFailure();
    adjustMap();
  }

  private void adjustMap() throws UnsolvableHashiMap {
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

  private void connectViaDepthFirstSearch() {
    List<Island> remainingIslands =
      hashiMap.getIslands()
        .stream()
        .filter(island -> island.getAdjustedPopulation() > 0)
        .collect(Collectors.toList());
    List<Coordinates> coordinates = new ArrayList<>();
    for (Island island : remainingIslands) {
      coordinates.add(island.getCoordinates());
    }

    for (Coordinates coords : coordinates) {
      Island island = getIslandFrom(coords);
      connectByTrialAndError(island);

      if (puzzleSolved(hashiMap, bridges)) {
        isSolvable = true;
        return;
      }
    }
  }

  private void connectByTrialAndError(Island island) {
    for (Map.Entry<Direction, List<Integer>> constraint : island.getConstraints().entrySet()) {
      List<Bridge> bridgesBeforeChanges = new ArrayList<>(bridges);
      HashiMap hashiMapBeforeChanges = hashiMap.clone();

      try {
        Island neighbor = island.getNeighbors().get(constraint.getKey());
        buildBridge(island, neighbor);

        checkFailuresAndUpdateMap();
        connectAllCertainBridges();

        if (puzzleSolved(hashiMap, bridges)) {
          this.isSolvable = true;
          return;
        }

        connectByTrialAndError(neighbor);

      } catch (UnsolvableHashiMap unsolvableHashiMap) {
        bridges = new ArrayList<>(bridgesBeforeChanges);
        hashiMap = hashiMapBeforeChanges;
      }
    }
  }

  private void connectAllCertainBridges() throws UnsolvableHashiMap {
    List<Bridge> startingBridges;
    do {
      startingBridges = new ArrayList<>(bridges);

      for (Island island : this.hashiMap.getIslands()) {
        checkFailuresAndUpdateMap();
        buildBridgesFor(island);
        if (checkIfComplete()) return;
      }

    } while (constraintsContinueChanging(startingBridges));
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
    Map.Entry<Direction, List<Integer>> constraint = null;
    for (Map.Entry<Direction, List<Integer>> constraintEntry : island.getConstraints().entrySet()) {
      List<Integer> constraints = constraintEntry.getValue();
      if (constraints.size() == 1) {
        constraint = constraintEntry;
      }
      if (constraints.contains(1) && !constraints.contains(0)) {
        constraint = constraintEntry;
      }
    }
    if (constraint != null) {
      Direction direction = constraint.getKey();
      Island neighbor = island.getNeighbors().get(direction);
      if (constraint.getValue().size() == 1) {
        buildBridgesForSingleConstraints(island, constraint);
        return;
      }
      if (constraint.getValue().contains(1) && !constraint.getValue().contains(0)) {
        buildBridge(island, neighbor);
      }
    }
  }

  private Island getIslandFrom(Coordinates coords) {
    for (Island island : hashiMap.getIslands()) {
      if (island.getCoordinates().equals(coords)) {
        return island;
      }
    }
    return null;
  }

  private void buildBridgesForSingleConstraints(
    Island island,
    Map.Entry<Direction, List<Integer>> constraintEntry
  ) throws UnsolvableHashiMap {
    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());
    List<Integer> constraint = constraintEntry.getValue();
    Integer numberOfBridges = constraint.get(0);

    for (int i = 0; i < numberOfBridges; i++) {
      buildBridge(island, neighbor);
    }
  }

  private void buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      Bridge bridge = new Bridge(island, neighbor);
      bridges.add(bridge);

      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();

      checkFailuresAndUpdateMap();
    } else {
      throw new UnsolvableHashiMap();
    }
  }
}
