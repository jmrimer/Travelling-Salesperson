package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HashiMap {
  private List<Island> islands;
  private int gridSize;

  public HashiMap(int squareGridSize, List<Island> islands) {
    this.islands = new ArrayList<>(islands);
    this.gridSize = squareGridSize;
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
}
