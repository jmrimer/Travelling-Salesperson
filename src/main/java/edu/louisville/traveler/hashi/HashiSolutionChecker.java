package edu.louisville.traveler.hashi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HashiSolutionChecker {

  public static boolean allIslandsConnect(HashiMap hashiMap, List<Bridge> bridges) {
    int requiredConnections = hashiMap.getIslands().size();
    Island startingIsland = hashiMap.getIslands().get(0);
    List<Bridge> uniqueBridges =
      bridges
        .stream()
        .distinct()
        .collect(Collectors.toList());

    int actualConnections =
      getIslandsFromBridges(
        connect(startingIsland, uniqueBridges, new ArrayList<>())
      ).size();

    return actualConnections == requiredConnections;
  }

  private static List<Island> getIslandsFromBridges(List<Bridge> bridges) {
    List<Island> connectedIslands = new ArrayList<>();
    bridges.stream().forEach(bridge -> {
      connectedIslands.add(bridge.getIsland1());
      connectedIslands.add(bridge.getIsland2());
    });

    return connectedIslands.stream().distinct().collect(Collectors.toList());
  }

  private static List<Bridge> connect(
    Island island,
    List<Bridge> uniqueBridges,
    List<Bridge> connectedBridges
  ) {
    List<Bridge> remainingBridges = new ArrayList<>(uniqueBridges);
    List<Bridge> bridgesFromIsland =
      uniqueBridges
        .stream()
        .filter(
          bridge -> bridge.contains(island))
        .collect(Collectors.toList());

    if (bridgesFromIsland.size() == 0) {
      return connectedBridges;
    }

    connectedBridges.addAll(bridgesFromIsland);
    remainingBridges.removeAll(bridgesFromIsland);
    for (Island connectedIsland : connectedIslands(island, bridgesFromIsland)) {
      connect(connectedIsland, remainingBridges, connectedBridges);
    }

    return connectedBridges;
  }

  @NotNull
  private static List<Island> connectedIslands(Island island, List<Bridge> bridgesFromIsland) {
    List<Island> connectedIslands = new ArrayList<>();
    for (Bridge connectedBridge : bridgesFromIsland) {
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

  public static boolean puzzleSolved(HashiMap hashiMap, List<Bridge> bridges) {
    return allBridgesBuilt(hashiMap, bridges) && allIslandsConnect(hashiMap, bridges);
  }
}
