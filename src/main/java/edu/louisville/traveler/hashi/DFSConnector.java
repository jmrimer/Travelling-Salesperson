package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DFSConnector {
  public static void connect(HashiSolution hashiSolution) {
    HashiMap hashiMap = hashiSolution.getHashiMap();
    List<Bridge> bridges = new ArrayList<>();
    Island island = hashiMap.getIslands().get(0);
    connectByTrialAndError(island, hashiSolution);

  }

  private static void connectByTrialAndError(
    Island root,
    HashiSolution hashiSolution
  ) {


    List<Bridge> bridges = new ArrayList<>();

    for (Map.Entry<Direction, List<Integer>> constraint : root.getConstraints().entrySet()) {
      Island neighbor = root.getNeighbors().get(constraint.getKey());
      Bridge bridge = new Bridge(root, neighbor);

      try {
        bridges.add(bridge);
        hashiSolution.addBridge(bridge);
      } catch (UnsolvableHashiMap unsolvableHashiMap) {
        unsolvableHashiMap.printStackTrace();
        bridges.remove(bridge);
        hashiSolution.removeBridge(bridge);
      }

      CertaintyConnector.connect(hashiSolution.getHashiMap()).forEach(
        bridge1 -> {
          try {
            bridges.add(bridge1);
            hashiSolution.addBridge(bridge1);
          } catch (UnsolvableHashiMap unsolvableHashiMap) {
            unsolvableHashiMap.printStackTrace();
          }
        }
      );

      if (HashiSolutionChecker.puzzleSolved(hashiSolution.getHashiMap(), hashiSolution.getBridges())) {
        return;
      }

      connectByTrialAndError(neighbor, hashiSolution);
    }
//    attempt to traverse branch
//    if fail state, backtrack
//    if allowed state, check certainty
//    if fail state, backtrack
//    if allowed and complete, end
//    if allowed and incomplete, take next available island and recurse
  }
}
