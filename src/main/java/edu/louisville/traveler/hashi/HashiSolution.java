package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class HashiSolution {
  private HashiMap hashiMap;
  private List<Bridge> bridges = new ArrayList<>();
  private boolean isComplete = false;

  public HashiSolution(HashiMap hashiMap) {
    this.hashiMap = hashiMap;
  }

  public void addBridge(Island island, Island neighbor) {
    Bridge bridge = new Bridge(island, neighbor);
    if (isAllowableBridgeAddition(bridge)) {
      this.bridges.add(bridge);
    }
  }

  private boolean isAllowableBridgeAddition(Bridge bridge) {
    return lessThanTwoBridges(bridge)
      && populationRemainingOn(bridge.getIsland1())
      && populationRemainingOn(bridge.getIsland2());
  }

  private boolean lessThanTwoBridges(Bridge bridge) {
    return Collections.frequency(bridges, bridge) < 2;
  }

  private boolean populationRemainingOn(Island island) {
    List<Bridge> bridgesWithIsland = new ArrayList<>(bridges);
    long islandBridgeCount = bridgesWithIsland.stream()
      .filter(bridge1 -> bridge1.contains(island))
      .count();
    return islandBridgeCount < island.getPopulation();
  }
}
