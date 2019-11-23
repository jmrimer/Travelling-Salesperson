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

  public static void connect(HashiSolution hashiSolution) throws UnsolvableHashiMap {
    List<Bridge> bridges = hashiSolution.getBridges();
    HashiMap hashiMap = hashiSolution.getHashiMap();

    for (Island island : hashiMap.getIslands()) {
      NeighborFinder.assignToIslands_AllAvailable(hashiSolution);
      ConstraintAssigner.assignConstraints(hashiMap);
      bridges.addAll(buildBridgesFor(island));
    }
  }

  private static List<Bridge> buildBridgesFor(Island island) throws UnsolvableHashiMap {
    Map.Entry<Direction, List<Integer>> constraint = extractMandatoryConstraint(island);
    return exists(constraint) ? buildMandatoryBridges(island, constraint) : new ArrayList<>();
  }

  private static List<Bridge> buildMandatoryBridges(
    Island island, Map.Entry<Direction,
    List<Integer>> constraint
  ) throws UnsolvableHashiMap {
    List<Bridge> bridges = new ArrayList<>();

    Direction direction = constraint.getKey();
    Island neighbor = island.getNeighbors().get(direction);
    Integer numberOfBridges = constraint.getValue().get(0);

    if (isSingleConstraint(constraint)) {
      bridges.addAll(
        buildBridges(island, neighbor, numberOfBridges)
      );
    } else if (requiresAtLeastOneBridge(constraint)) {
      bridges.add(buildBridge(island, neighbor));
    }

    return bridges;
  }

  private static Map.Entry<Direction, List<Integer>> extractMandatoryConstraint(Island island) {
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
    return constraint;
  }

  private static List<Bridge> buildBridges(
    Island island,
    Island neighbor,
    int numberOfBridges
  ) throws UnsolvableHashiMap {
    List<Bridge> bridges = new ArrayList<>();

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

  private static boolean requiresAtLeastOneBridge(Map.Entry<Direction, List<Integer>> constraint) {
    return constraint.getValue().contains(1) && !constraint.getValue().contains(0);
  }

  private static boolean isSingleConstraint(Map.Entry<Direction, List<Integer>> constraint) {
    return constraint.getValue().size() == 1;
  }

  private static boolean exists(Map.Entry<Direction, List<Integer>> constraint) {
    return constraint != null;
  }
}
