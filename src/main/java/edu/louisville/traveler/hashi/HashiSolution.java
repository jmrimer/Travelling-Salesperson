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
    return canShareAnotherBridge(bridge)
      && hasAvailability(bridge.getIsland1())
      && hasAvailability(bridge.getIsland2());
  }

  private boolean canShareAnotherBridge(Bridge bridge) {
    return Collections.frequency(bridges, bridge) < 2;
  }

  private boolean hasAvailability(Island island) {
    List<Bridge> bridgesWithIsland = new ArrayList<>(bridges);
    return bridgesWithIsland.stream()
      .filter(bridge1 -> bridge1.contains(island)).count() < island.getPopulation();
  }
}
