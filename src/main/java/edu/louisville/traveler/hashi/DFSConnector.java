package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DFSConnector {
  public static void connect(HashiSolution hashiSolution) {
    HashiMap hashiMap = hashiSolution.getHashiMap();

    if (assigningConstraintThrowsError(hashiMap)) return;

    List<Constraint> untestedConstraints = hashiMap.getAllConstraints();
    Iterator<Constraint> constraintIterator = hashiMap.getAllConstraints().iterator();
    while (constraintIterator.hasNext()) {
      HashiMap hashiMapClone = hashiMap.clone();
      Constraint constraint = constraintIterator.next();
      constraintIterator.remove();

      connectByTrialAndError(constraint, untestedConstraints, hashiSolution);

      if (HashiSolutionChecker.puzzleSolved(
        hashiSolution.getHashiMap(),
        hashiSolution.getBridges())
      ) {
        return;
      }

      hashiSolution.setHashiMap(hashiMapClone);
      if (assigningConstraintThrowsError(hashiMap)) return;
    }
  }

  private static boolean assigningConstraintThrowsError(HashiMap hashiMap) {
    try {
      ConstraintAssigner.assignConstraints(hashiMap);
    } catch (UnsolvableHashiMap unsolvableHashiMap) {
      unsolvableHashiMap.printStackTrace();
      return true;
    }
    return false;
  }

  private static void connectByTrialAndError(
    Constraint constraint,
    List<Constraint> untestedConstraints,
    HashiSolution hashiSolution
  ) {
    Island startingIsland = constraint.getStartingIsland();
    Island neighbor = startingIsland.getNeighbors().get(constraint.getDirectionToNeighbor());
    List<Integer> possibleConnections = constraint.getPossibleConnections();
    Direction direction = constraint.getDirectionToNeighbor();
    int bridgesToBuild = possibleConnections.get(0);

    HashiMap mutatingHashiMap = hashiSolution.getHashiMap();
    HashiMap hashiMapClone = mutatingHashiMap.clone();
    Bridge bridge = new Bridge(startingIsland, neighbor);
    for (Integer connection : possibleConnections) {
      switch (connection) {
        case 0:
          startingIsland.getConstraints().remove(direction);

          List<Bridge> addBridges = CertaintyConnector.connect(mutatingHashiMap);
          List<Bridge> bridgesAdded = new ArrayList<>();
          for (Bridge addBridge : addBridges) {
            try {
              bridgesAdded.add(addBridge);
              hashiSolution.addBridge(addBridge);
            } catch (UnsolvableHashiMap unsolvableHashiMap) {
              unsolvableHashiMap.printStackTrace();
              hashiSolution.removeBridges(bridgesAdded);
              hashiSolution.setHashiMap(hashiMapClone);
              break;
            }
          }
          if (HashiSolutionChecker.puzzleSolved(mutatingHashiMap, hashiSolution.getBridges())) {
            return;
          }

          break;
        case 1:
          try {
            hashiSolution.addBridge(bridge);
          } catch (UnsolvableHashiMap unsolvableHashiMap) {
            unsolvableHashiMap.printStackTrace();
            hashiSolution.removeBridge(bridge);
            hashiSolution.setHashiMap(hashiMapClone);
            continue;
          }
          break;
        case 2:
          try {
            hashiSolution.addBridge(bridge);
            hashiSolution.addBridge(bridge);

          } catch (UnsolvableHashiMap unsolvableHashiMap) {
            unsolvableHashiMap.printStackTrace();
            hashiSolution.removeBridge(bridge);
            hashiSolution.setHashiMap(hashiMapClone);
            continue;
          }
          break;
      }
      startingIsland.setConstraint(direction, List.of(connection));
    }
  }
//    attempt to traverse branch
//    if fail state, backtrack
//    if allowed state, check certainty
//    if fail state, backtrack
//    if allowed and complete, end
//    if allowed and incomplete, take next available island and recurse
}