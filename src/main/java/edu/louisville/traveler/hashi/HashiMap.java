package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HashiMap {
  private List<Island> islands;
  private int gridSize;
  private int bridgesRequired = 0;

  public HashiMap(int gridSize, List<Island> islands) {
    this.islands = new ArrayList<>(islands);
    this.gridSize = gridSize;
    calculateBridgeCount();
    assignNeighbors();
  }

  private void assignNeighbors() {
    for (Island island : islands) {
      Map<Direction, Island> neighbors = NeighborChecker.findAllNeighbors(this, island);
      island.setNeighbors(neighbors);
    }
  }

  public void add(Island island) {
    this.islands.add(island);
  }

  public int getBridgesRequired() {
    calculateBridgeCount();
    return bridgesRequired;
  }

  private void calculateBridgeCount() {
    bridgesRequired = 0;
    for (Island island: islands) {
      bridgesRequired += island.getPopulation();
    }
    bridgesRequired /= 2;
  }
}
