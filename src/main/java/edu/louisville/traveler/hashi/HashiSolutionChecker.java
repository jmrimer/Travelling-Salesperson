package edu.louisville.traveler.hashi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HashiSolutionChecker {

  public static boolean allIslandsConnect(HashiMap hashiMap, List<Bridge> bridges) {
    List<Bridge> uniqueBridges = bridges.stream().distinct().collect(Collectors.toList());
    Island island = hashiMap.getIslands().get(0);

    List<Bridge> connectedBridges = new ArrayList<>(connect(island, uniqueBridges, new ArrayList<>()));
    return connectedBridges.size() == hashiMap.getIslands().size() - 1;
  }

  private static List<Bridge> connect(
    Island island,
    List<Bridge> uniqueBridges,
    List<Bridge> connectedBridges
  ) {
    List<Bridge> remainingBridges = new ArrayList<>(uniqueBridges);
    List<Bridge> bridgesThatIncludeIsland =
      uniqueBridges
        .stream()
        .filter(
          bridge -> bridge.contains(island))
        .collect(Collectors.toList());

    if (bridgesThatIncludeIsland.size() == 0) {
      return connectedBridges;
    }

    connectedBridges.addAll(bridgesThatIncludeIsland);
    remainingBridges.removeAll(bridgesThatIncludeIsland);

    List<Island> connectedIslands = getConnectedIslands(island, bridgesThatIncludeIsland);
    for (Island connectedIsland : connectedIslands) {
      connect(connectedIsland, remainingBridges, connectedBridges);
    }

    return connectedBridges;
  }

  @NotNull
  private static List<Island> getConnectedIslands(Island island, List<Bridge> bridgesThatIncludeIsland) {
    List<Island> connectedIslands = new ArrayList<>();
    for (Bridge connectedBridge : bridgesThatIncludeIsland) {
      if (connectedBridge.getIsland1().equals(island)) {
        connectedIslands.add(connectedBridge.getIsland2());
      } else {
        connectedIslands.add(connectedBridge.getIsland1());
      }
    }
    return connectedIslands;
  }

  public static boolean allBridgesBuilt(HashiMap hashiMap, List<Bridge> bridges) {
    for (Island island : hashiMap.getIslands()) {
      if (island.getAdjustedPopulation() > 0) {
        return false;
      }
    }
    return bridges.size() == hashiMap.getBridgesRequired();
  }
}
