package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CertaintyConnector {
  public static List<Bridge> connect(HashiMap hashiMap) {
    List<Bridge> bridges = new ArrayList<>();

    do {
      for (Island island : hashiMap.getIslands()) {
        if (prepareMapCausedError(hashiMap)) return bridges;
        if (buildingBridgesCausedError(bridges, island)) return bridges;
      }
    } while (singleConstraintsExist(hashiMap));

    return bridges;
  }

  private static boolean singleConstraintsExist(HashiMap hashiMap) {
    try {
      ConstraintAssigner.assignConstraints(hashiMap);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      return false;
    }

    for (Island island : hashiMap.getIslands()) {
      for (List<Integer> constraint : island.getConstraints().values()) {
        if (constraint.size() == 1 || !constraint.contains(0)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean buildingBridgesCausedError(List<Bridge> bridges, Island island) {
    try {
      bridges.addAll(buildBridgesFor(island));
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      return true;
    }
    return false;
  }

  private static boolean prepareMapCausedError(HashiMap hashiMap) {
    try {
      ConstraintAssigner.assignConstraints(hashiMap);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      return true;
    }
    return false;
  }

  public static List<Bridge> buildBridgesFor(Island island) throws UnsolvableHashiMap {
    List<Bridge> bridges = new ArrayList<>();

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
        bridges.addAll(buildBridgesForSingleConstraints(island, constraint));
        return bridges;
      }
      if (constraint.getValue().contains(1) && !constraint.getValue().contains(0)) {
        bridges.add(buildBridge(island, neighbor));
      }
    }
    return bridges;
  }

  private static List<Bridge> buildBridgesForSingleConstraints(
    Island island,
    Map.Entry<Direction, List<Integer>> constraintEntry
  ) throws UnsolvableHashiMap {
    List<Bridge> bridges = new ArrayList<>();

    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());
    List<Integer> constraint = constraintEntry.getValue();
    Integer numberOfBridges = constraint.get(0);

    for (int i = 0; i < numberOfBridges; i++) {
      bridges.add(buildBridge(island, neighbor));
    }
    return bridges;
  }

  private static Bridge buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      Bridge bridge = new Bridge(island, neighbor);

      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();

      return bridge;
    } else {
      throw new UnsolvableHashiMap();
    }
  }
}
